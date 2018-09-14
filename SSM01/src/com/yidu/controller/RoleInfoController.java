package com.yidu.controller;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yidu.bean.RoleInfo;
import com.yidu.dao.RoleInfoInter;
import com.yidu.services.RoleInfoService;



@Controller
public class RoleInfoController {
	@Autowired
	RoleInfoInter roleInfoInter;
	@Autowired
	RoleInfoService roleInfoService;
	@RequestMapping("/roles")
	public @ResponseBody List<RoleInfo> roles(){
		return roleInfoInter.roles();
	}

	@RequestMapping("/rootUpdate")
	public @ResponseBody String rootUpdate(int roleId,String menuIds){
		System.out.println("roleId="+roleId+"\t"+menuIds);
		boolean result=false;
		String menuIdArray[]=menuIds.split(",");
		if(roleInfoService.deleteFromRoleMenu(roleId)){
			HashMap map=new HashMap();
			map.put("roleId", roleId);
			map.put("menuIds", menuIdArray);
			//orcal批量新增没搞懂，弄一个试试
			map.put("menuId", "L01");
			result=roleInfoService.insertIntoRoleMenu(map);
		};
		String message=result?"成功":"失败";
		System.out.println("message="+message);
		return message;
	}
	
	
}
