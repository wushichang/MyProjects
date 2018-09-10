package com.bean;

import java.util.List;

public class StudentBean {
	private String stuId;
	private String stuName;
	private String stuSex;
	private int stuAge;
	private String stuEmail;
	private String stuAddress;
	private List<Score> scores;
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	public String getStuEmail() {
		return stuEmail;
	}
	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}
	public String getStuAddress() {
		return stuAddress;
	}
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
	public StudentBean() {
		super();
	}
	
	public StudentBean(String stuName) {
		super();
		this.stuName = stuName;
	}
	public StudentBean(String stuId, String stuName, String stuSex, int stuAge, String stuEmail, String stuAddress,
			List<Score> scores) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.stuSex = stuSex;
		this.stuAge = stuAge;
		this.stuEmail = stuEmail;
		this.stuAddress = stuAddress;
		this.scores = scores;
	}
	public StudentBean(String stuName, String stuSex, int stuAge, String stuEmail, String stuAddress,
			List<Score> scores) {
		super();
		this.stuName = stuName;
		this.stuSex = stuSex;
		this.stuAge = stuAge;
		this.stuEmail = stuEmail;
		this.stuAddress = stuAddress;
		this.scores = scores;
	}
	
}
