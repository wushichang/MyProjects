<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色权限</title>
<link rel="stylesheet" type="text/css" href="EasyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="EasyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="EasyUI/themes/demo.css">
	<script type="text/javascript" src="EasyUI/jquery.min.js"></script>
	<script type="text/javascript" src="EasyUI/jquery.easyui.min.js"></script>
</head>
<body style="margin:0;">
	<table id="dg" class="easyui-datagrid"   
        data-options="url:'roles',singleSelect:true,toolbar: '#tb',onClickCell: onClickCell">   
    <thead>   
        <tr>   
            <th data-options="field:'code',width:80,checkbox:true"></th>   
            <th data-options="field:'roleId',width:200,formatter:function(value,row){
							return row.roleName;
						}">编号</th> 
        </tr>   
    </thead>   
	</table>  
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="update()">Update</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
	</div>
	<div>
		<h1>修改权限</h1>
		<ul id="tt"></ul><br/>  
		<button onclick="updateRoot()">提交</button>
	</div>
	<script type="text/javascript">
		var roleNo=0;
		var rootNodes=null;
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickCell(index, field){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					var ed = $('#dg').datagrid('getEditor', {index:index,field:field});
					if (ed){
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
					}
					editIndex = index;
				} else {
					setTimeout(function(){
						$('#dg').datagrid('selectRow', editIndex);
					},0);
				}
			}
		}
		function onEndEdit(index, row){
			var ed = $(this).datagrid('getEditor', {
				index: index,
				field: 'productid'
			});
			row.productname = $(ed.target).combobox('getText');
		}
		function append(){
			var rootNodes=null;
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
		}
		function accept(){
			if (endEditing()){
				$('#dg').datagrid('acceptChanges');
			}
		}
		function update(){
			var updateRow = $('#dg').datagrid('getSelected');
			if(updateRow!=null){
				roleNo=updateRow.roleId;
				$('#tt').tree({    
				    url:'rootManage?roleId='+roleNo,
				    checkbox:true
				});  
			}else{
				alert("必须选择一行");
			}
		}
		function getChanges(){
			var rows = $('#dg').datagrid('getChanges');
			alert(rows.length+' rows are changed!');
		}
		
		
		function updateRoot(){
			if(roleNo>0){
				var nodes = $('#tt').tree('getChecked', ['checked','indeterminate']);
				var menuIds="";
				for (var i = 0; i < nodes.length; i++) {
					menuIds+=i==0?nodes[0].id:","+nodes[i].id;
				}
				alert("menuId="+menuIds);
				$.ajax({
					url:"rootUpdate",
					type:"post",
					data:"menuIds="+menuIds+"&roleId="+roleNo,
					dataType:"josn",
					success:function(message){
						alert(message);
					}
				})
			}
		}
		
	</script>
</body>
</html>