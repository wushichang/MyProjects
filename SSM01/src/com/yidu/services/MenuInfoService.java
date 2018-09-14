package com.yidu.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yidu.bean.Child;
import com.yidu.bean.FirstCode;
import com.yidu.bean.MenuInfo;
import com.yidu.bean.UserInfo;
import com.yidu.dao.MenuInfoInter;

@Service
public class MenuInfoService {
	@Autowired
	MenuInfoInter menuInfoInter;
	public HashMap<String,List<MenuInfo>> selectMenuInfosByUserId(UserInfo user){
		//申明菜单信息映射
		HashMap<String,List<MenuInfo>> map=new HashMap<String,List<MenuInfo>>();
		//根据用户编号的到菜单信息集合
		List<MenuInfo> menus=menuInfoInter.selectMenuInfosByUserId(user);
		//申明二级菜单集合
		List<MenuInfo> secondMenu=null;
		//申明一级菜单编号
		String menuId="";
		//申明菜单映射
		String key="";
		for (MenuInfo menuInfo : menus) {
			if("root".equals(menuInfo.getPid())){
				//得到一级菜单标题
				key=menuInfo.getTitle();
				//重置二级菜单集合
				secondMenu=new ArrayList<MenuInfo>();
				//得到一级菜单编号
				menuId=menuInfo.getMenuId();
				//将菜单装入映射
				map.put(key, secondMenu);
			}else if(menuId.equals(menuInfo.getPid())){
				//得到二级菜单信息
				secondMenu.add(menuInfo);
			}
		}
		return map;
	}
	
	public List<FirstCode> selectFirstCodeByRoleId(int roleId){
		//查询所有菜单
		List<MenuInfo> list=menuInfoInter.selectAll();
		//查询某个角色对应的菜单
		List<MenuInfo> list2=menuInfoInter.selectMenuInfosByRoleId(roleId);
		//申明树型一级对象集合
		List<FirstCode> tree=new ArrayList<FirstCode>();
		//申明树形子集的集合
		List<Child> children=null;
		//申明树型一级对象
		FirstCode firstCode=null;
		//申明一级菜单编号
		String menuId="";
		for (MenuInfo menuInfo : list) {
			//得到一级菜单信息
			if("root".equals(menuInfo.getPid())){
				//得到一级菜单编号
				menuId=menuInfo.getMenuId();
				//清空树形子集空间
				children=new ArrayList<Child>();
				firstCode=new FirstCode(menuInfo.getMenuId(), menuInfo.getTitle(), children);
				tree.add(firstCode);
			}else if(menuId.equals(menuInfo.getPid())){
				//申明树形子集布尔值
				boolean checked=false;
				for (MenuInfo menuInfo2 : list2) {
					if(menuInfo.getMenuId().equals(menuInfo2.getMenuId())){
						checked=true;
						break;
					}
				}
				children.add(new Child(menuInfo.getMenuId(), menuInfo.getTitle(), checked));
			}
		}
		return tree;
	}
}
