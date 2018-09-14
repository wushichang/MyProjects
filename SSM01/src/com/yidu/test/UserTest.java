package com.yidu.test;


import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yidu.bean.Child;
import com.yidu.bean.FirstCode;
import com.yidu.bean.MenuInfo;
import com.yidu.bean.RoleInfo;
import com.yidu.bean.UserInfo;
import com.yidu.controller.RoleInfoController;
import com.yidu.dao.MenuInfoInter;
import com.yidu.dao.RoleInfoInter;
import com.yidu.dao.UserInfoInter;
import com.yidu.services.MenuInfoService;
import com.yidu.services.RoleInfoService;

public class UserTest {
	@Test
	public void login() {
		UserInfo user=new UserInfo();
		user.setUserName("lzy");
		user.setUserPass("12345");
		System.out.println(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(UserInfoInter.class, "userInfoInter").login(user));
	}
	
	@Test
	public void selectMenuInfosByUserId() {
		UserInfo user=new UserInfo();
		user.setUserName("lzy");
		user.setUserPass("12345");
		List<MenuInfo> menus=new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(MenuInfoInter.class, "menuInfoInter").selectMenuInfosByUserId(user);
		for (MenuInfo menuInfo : menus) {
			System.out.println(menuInfo.getTitle());
		}
	}
	
	@Test
	public void MenuInfosByUserId() {
		UserInfo user=new UserInfo();
		user.setUserName("lzy");
		user.setUserPass("12345");
		HashMap<String, List<MenuInfo>> map=new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(MenuInfoService.class, "menuInfoService").selectMenuInfosByUserId(user);
		Set<String> set=map.keySet();
		for (String key : set) {
			List<MenuInfo> menus=map.get(key);
			System.out.println("key="+key);
			for (MenuInfo menuInfo : menus) {
				System.out.println(menuInfo.getTitle());
			}
		}
	}
	

	@Test
	public void MenuInfo() {
		List<FirstCode> list=new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(MenuInfoService.class, "menuInfoService").selectFirstCodeByRoleId(2);
		for (FirstCode firstCode : list) {
			System.out.println(firstCode.getText());
			List<Child> children=firstCode.getChildren();
			for (Child child : children) {
				System.out.println(child.getText()+"\t"+child.isChecked());
			}
		}
	}
	@Test
	public void testRoles(){
		List<RoleInfo> roles=new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(RoleInfoInter.class,"roleInfoInter").roles();
		for (RoleInfo roleInfo : roles) {
			System.out.println(roleInfo.getRoleName());
		}
	}
	
	@Test
	public void selectRoleIdByUserId(){
		System.out.println(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(RoleInfoInter.class,"roleInfoInter").selectRoleIdByUserId(1));
	}
	
	@Test
	public void insertIntoRoleMenu(){
		HashMap<String,Object> map=new HashMap();
		map.put("roleId", 2);
		map.put("menuId", "L0103");
		System.out.println(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(RoleInfoInter.class,"roleInfoInter").insertIntoRoleMenu(map));
	}
	
	@Test
	public void insertIntoRoleMenu1(){
		HashMap<String,Object> map=new HashMap();
		map.put("roleId", 2);
		map.put("menuId", "L0102");
		System.out.println(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(RoleInfoService.class,"roleInfoService").insertIntoRoleMenu(map));
	}
	
	@Test
	public void deleteRoleMenu(){
		System.out.println(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(RoleInfoInter.class,"roleInfoInter").deleteFromRoleMenu(3));
	}
	
	@Test
	public void deleteRoleMenu1(){
		System.out.println(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(RoleInfoService.class,"roleInfoService").deleteFromRoleMenu(3));
	}
	@Test
	public void testControllerRoles(){
		System.out.println(new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(RoleInfoController.class,"roleInfoController").roles());
	}
	
	@Test
	public void testSelectAll(){
		List<MenuInfo> list=new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(MenuInfoInter.class,"menuInfoInter").selectAll();
		for (MenuInfo menuInfo : list) {
			System.out.println(menuInfo.getTitle());
		}
	}
	
	@Test
	public void selectMenuInfosByRoleId(){
		List<MenuInfo> list=new ClassPathXmlApplicationContext("ApplicationContext.xml").getBean(MenuInfoInter.class,"menuInfoInter").selectMenuInfosByRoleId(1);
		for (MenuInfo menuInfo : list) {
			System.out.println(menuInfo.getTitle());
		}
	}
	
	
	@Test
	public void getUserId(){
		String userInfo="UserInfo [userId=0, userName=lzy, userPass=12345, userState=0]";
		System.out.println(userInfo.substring(userInfo.indexOf("=")+1, userInfo.indexOf(",")));
	}
}
