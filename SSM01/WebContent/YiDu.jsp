<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到一度</title>
	<link rel="stylesheet" type="text/css" href="EasyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="EasyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="EasyUI/themes/demo.css">
	<script type="text/javascript" src="EasyUI/jquery.min.js"></script>
	<script type="text/javascript" src="EasyUI/jquery.easyui.min.js"></script>
	<style>
		.secondMenu:hover{
			border:1px solid blue;
			font-size:16px;
			cursor:pointer;
		}
	</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;">
		尊近的用户：<b>${userName}</b>!欢迎来到一度首页!
	</div>
	<div data-options="region:'west',split:true,title:'目录'" style="width:180px;">
		<div id="aa" class="easyui-accordion" data-options="fit:true">   
		   	<c:forEach var="menu" items="${menus}">
		   		<!-- 一级菜单 -->
			    <div title="${menu.key}">   
					<ul style="list-style-type:none;padding:0;text-align:center;">
						<c:forEach var="menuInfo" items="${menu.value}">
							<!-- 二级菜单 -->
							<li class="secondMenu" name=" ${menuInfo.url}" onclick="tabs('${menuInfo.title}','${menuInfo.url}')"> ${menuInfo.title}</li>
						</c:forEach>
					</ul>
			    </div>     
		   	</c:forEach>
		</div> 
	</div>
	
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center'">
		<!-- 选项卡 -->
		<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools',pill:true,fit:true"/>
		<!-- 菜右击单 -->
		<div id="mm" class="easyui-menu" style="width:120px;">
			<div data-options="name:'close'">关闭</div>
			<div data-options="name:'closeAll'">关闭所有</div>
			<div data-options="name:'closeLeft'">关闭左边</div>
			<div data-options="name:'closeRight'">关闭右边</div>
			<div data-options="name:'closeOthers'">关闭其他</div>
		</div>
	</div>
	<script>
		//申明选项卡下标
		var tabIndex=-1;
		//添加选项卡
		function tabs(title,url){
			//判断选项卡是否存在
			if(!$('#tt').tabs('exists',title)){
				$('#tt').tabs('add',{
					title: title,
					content: "<iframe src="+url+" style='border:0;width:99%;height:500px'></iframe>",
					closable: true
				});
			}
		}
		//删除右击菜单  所有，右边，左边的方法
		function deleteTabs(maxTab,minTab){
			for (var i =maxTab-1; i >= minTab; i--){
        		$('#tt').tabs('close',i);
			}
		}
		$(function(){
			//选项卡右击
			$('#tt').tabs({
				onContextMenu: function(e, title,index){
					//得到选项卡下标
					tabIndex=index;
				}
			});
			//右击显示菜单
			$(document).bind('contextmenu',function(e){
				e.preventDefault();
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			});
			//选择右击菜单项
			$('#mm').menu({    
			    onClick:function(item){    
			        switch(item.name){
			        case 'close':
			        	//关闭选项卡
			        	$('#tt').tabs('close',tabIndex);
			        	break;
			        case 'closeAll':
			        	//关闭所有 倒着删
			        	var tabs=$('#tt').tabs('tabs');
			        	deleteTabs(tabs.length,0);
			        	break;
			        case 'closeLeft':
			        	//关闭左边
						deleteTabs(tabIndex,0);
			        	break;
			        case 'closeRight':
			        //关闭右边
			        	var tabs=$('#tt').tabs('tabs');
			        	deleteTabs(tabs.length,tabIndex+1);
			       	 break;
			        case 'closeOthers':
			        	//关闭其他
			        	var tabs=$('#tt').tabs('tabs');
			        	for (var i = tabs.length-1; i >=0; i--) {
			        		if(i!=tabIndex){
			        			$('#tt').tabs('close',i);
			        		}
						}
			        	break;
			        }  
			    }    
			});  
		});
	</script>
</body>
</html>