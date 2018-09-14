package com.yidu.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yidu.bean.UserInfo;
import com.yidu.dao.UserInfoInter;

@Service
public class UserService {
	@Autowired
	private UserInfoInter userInfoInter;
	public boolean login(UserInfo user){
		UserInfo userInfo=userInfoInter.login(user);
		return userInfo==null?false:true;
	}
}
