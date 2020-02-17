package com.TrainingSystem.entity;

public class Studentscore {
	private String SID;
	private String SName;
	private String Run3k;
	private String Snake;
	private String Situp;
	private String Pullup;
	private String Date;
	
	public Studentscore(String sSID, String sSName,
			String sRun3k, String sSnake, String sSitup, 
			String sPullup, String sDate) {
		super();
		SID = sSID;
		SName = sSName;
		Run3k = sRun3k;
		Snake = sSnake;
		Situp = sSitup;
		Pullup = sPullup;
		Date = sDate;
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
	
	
}
