package com.yidu.dao;

import org.springframework.stereotype.Repository;

import com.yidu.bean.UserInfo;
@Repository
public interface UserInfoInter {
	public UserInfo login(UserInfo user);
}
