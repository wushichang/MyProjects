package com.yidu.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yidu.bean.UserInfo;
import com.yidu.services.UserService;

@Controller
public class UserInfoController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	public ModelAndView login(UserInfo user,HttpServletResponse response){
		//申明模型视图对象
		ModelAndView mv=new ModelAndView();
		//判断用户是否登录成功
		if(userService.login(user)){
			//将用户信息存入缓存中
			response.addCookie(new Cookie("userInfo", user.toString()));
			//将用户信息传递至首页
			mv.addObject("user", user);
			mv.setViewName("index");
		}else{
			//去登录失败页面
			mv.setViewName("Fail.jsp");
		}
		return mv;
	}
}
