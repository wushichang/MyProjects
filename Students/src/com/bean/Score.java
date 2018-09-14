package com.bean;
//gitÌá½»²Ù×÷commit and push
//i am come back
public class Score {
	private String subjectName;
	private int score;
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Score(String subjectName, int score) {
		super();
		this.subjectName = subjectName;
		this.score = score;
	}
	
	
}
