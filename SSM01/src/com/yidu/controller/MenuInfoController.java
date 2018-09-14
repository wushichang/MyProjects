package com.yidu.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yidu.bean.FirstCode;
import com.yidu.bean.MenuInfo;
import com.yidu.bean.UserInfo;
import com.yidu.services.MenuInfoService;

@Controller
public class MenuInfoController {
	@Autowired
	MenuInfoService menuInfoService;
	@RequestMapping("/index")
	public ModelAndView index(UserInfo user){
		//根据用户编号得到所有菜单信息
		HashMap<String, List<MenuInfo>> map=menuInfoService.selectMenuInfosByUserId(user);
		//申明模型视图对象
		ModelAndView mv=new ModelAndView();
		//将用户名传递至页面
		mv.addObject("userName",user.getUserName());
		//将菜单信息集合传递至页面
		mv.addObject("menus", map);
		//设置视图名
		mv.setViewName("YiDu.jsp");
		return mv;
	}
	
	@RequestMapping("/rootManage")
	public @ResponseBody List<FirstCode> rootManage(HttpServletRequest request,int roleId){
		/*System.out.println("来了");
		//读取缓存中用户信息
		Cookie[] cookies=request.getCookies();
		//申明用户信息
		String userInfo="";
		for (Cookie cookie : cookies) {
			if("userInfo".equals(cookie.getName())){
				userInfo=cookie.getValue();
				break;
			}
		}
		//得到用户的角色编号
		int roleId=Integer.parseInt(userInfo.substring(userInfo.indexOf("=")+1, userInfo.indexOf(",")));*/
		return menuInfoService.selectFirstCodeByRoleId(roleId);
	}
	
}
