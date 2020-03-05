package com.TrainingSystem.entity;

public class Healthinfo {
	private String SID;
	private String SName;
	private String Date;
	private String SGender;
	private String SAge;
	private String Weight;
	private String Height;
	private String BMI;
	
	public Healthinfo(String sSID, String sSName, String sDate,
			String sSGender, String sSAge, String sWeight, 
			String sHeight, String sBMI) {
		SID = sSID;
		SName = sSName;
		Date = sDate;
		SGender = sSGender;
		SAge = sSAge;
		Weight = sWeight;
		Height = sHeight;
		BMI = sBMI;
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

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getSGender() {
		return SGender;
	}

	public void setSGender(String sGender) {
		SGender = sGender;
	}

	public String getSAge() {
		return SAge;
	}

	public void setSAge(String sAge) {
		SAge = sAge;
	}

	public String getWeight() {
		return Weight;
	}

	public void setWeight(String weight) {
		Weight = weight;
	}

	public String getHeight() {
		return Height;
	}

	public void setHeight(String height) {
		Height = height;
	}

	public String getBMI() {
		return BMI;
	}

	public void setBMI(String bMI) {
		BMI = bMI;
	}
}
