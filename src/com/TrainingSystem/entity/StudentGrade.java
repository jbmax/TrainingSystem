package com.TrainingSystem.entity;

public class StudentGrade {
	private String TID;
	private String SID;
	private String SName;
	private String Gender;
	private String Age;
	private String Run3k;
	private String Snake;
	private String Situp;
	private String Pullup;
	private String Date;
	private String SGroup;
	
	public StudentGrade(String tid, String sid, String name, String gender, String age, 
			String group, String date, String item1, String item2, String item3, String item4) {
		TID = tid;
		SID = sid;
		SName = name;
		Gender = gender;
		Age = age;
		SGroup = group;
		Date = date;
		Run3k = item1;
		Snake = item2;
		Situp = item3;
		Pullup = item4;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	public String getSName() {
		return SName;
	}

	public void setSName(String sName) {
		SName = sName;
	}

	public String getRun3k() {
		return Run3k;
	}

	public void setRun3k(String run3k) {
		Run3k = run3k;
	}

	public String getSnake() {
		return Snake;
	}

	public void setSnake(String snake) {
		Snake = snake;
	}

	public String getSitup() {
		return Situp;
	}

	public void setSitup(String situp) {
		Situp = situp;
	}

	public String getPullup() {
		return Pullup;
	}

	public void setPullup(String pullup) {
		Pullup = pullup;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getSGroup() {
		return SGroup;
	}

	public void setGroup(String group) {
		SGroup = group;
	}
	
}
