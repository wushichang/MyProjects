package com.yidu.bean;

public class Child {
	private String id;   //节点ID，对加载远程数据很重要
    private String text; //显示节点文本 
    private boolean checked; //表示该节点是否被选中
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Child() {
		super();
	}
	public Child(String id, String text, boolean checked) {
		super();
		this.id = id;
		this.text = text;
		this.checked = checked;
	}
    
    
}