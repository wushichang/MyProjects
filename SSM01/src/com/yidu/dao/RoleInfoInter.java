package com.yidu.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yidu.bean.RoleInfo;

public interface RoleInfoInter{
	public List<RoleInfo> roles();
	public int selectRoleIdByUserId(int userId);
	public int insertIntoRoleMenu(HashMap<String,Object> map);
	public int deleteFromRoleMenu(int roleId);
}
