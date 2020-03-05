package com.TrainingSystem.entity;

public class StudentItemscore {
	private String SID;
	private String date;
	private String Item1Score;
	private String Item2Score;
	private String Item3Score;
	private String Item4Score;
	
	public StudentItemscore(String sSID, String sdate, 
			String sItem1Score, String sItem2Score, 
			String sItem3Score, String sItem4Score) {
		SID = sSID;
		date = sdate;
		Item1Score = sItem1Score;
		Item2Score = sItem2Score;
		Item3Score = sItem3Score;
		Item4Score = sItem4Score;
	}
	
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getItem1Score() {
		return Item1Score;
	}
	public void setItem1Score(String item1Score) {
		Item1Score = item1Score;
	}
	public String getItem2Score() {
		return Item2Score;
	}
	public void setItem2Score(String item2Score) {
		Item2Score = item2Score;
	}
	public String getItem3Score() {
		return Item3Score;
	}
	public void setItem3Score(String item3Score) {
		Item3Score = item3Score;
	}
	public String getItem4Score() {
		return Item4Score;
	}
	public void setItem4Score(String item4Score) {
		Item4Score = item4Score;
	}
	
	
}
