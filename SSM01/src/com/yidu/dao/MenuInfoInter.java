package com.yidu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yidu.bean.MenuInfo;
import com.yidu.bean.UserInfo;
@Repository
public interface MenuInfoInter {
	public List<MenuInfo> selectMenuInfosByUserId(UserInfo user);
	public List<MenuInfo> selectAll();
	public List<MenuInfo> selectMenuInfosByRoleId(int roleId);
}
