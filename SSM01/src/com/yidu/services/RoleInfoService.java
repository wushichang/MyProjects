package com.yidu.services;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yidu.dao.RoleInfoInter;



@Service
public class RoleInfoService {
	@Autowired
	RoleInfoInter roleInfoInter;
	
	public int selectRoleIdByUserId(int userId){
		return roleInfoInter.selectRoleIdByUserId(userId);
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean deleteFromRoleMenu(int roleId){
		return roleInfoInter.deleteFromRoleMenu(roleId)>0;
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean insertIntoRoleMenu(HashMap<String,Object> map){
		return roleInfoInter.insertIntoRoleMenu(map)>0;
	}
	
}
