package com.yidu.bean;

import org.springframework.stereotype.Component;

@Component
public class UserInfo {
	private int userId;//用户编号
	private String userName;//用户名称
	private String userPass;//用户密码
	private int userState;//用户使用状态
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	public UserInfo() {
		super();
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + ", userState="
				+ userState + "]";
	}
	
}
