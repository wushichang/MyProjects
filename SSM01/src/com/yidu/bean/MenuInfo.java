package com.yidu.bean;

import org.springframework.stereotype.Component;

@Component
public class MenuInfo {
	private String menuId;//主键
	private String title;//菜单名称
	private int state;//状态
	private String url;//链接url
	private String pid;//上级菜单id
	private String iconCls;//菜单项图标
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public MenuInfo() {
		super();
	}
	
}
