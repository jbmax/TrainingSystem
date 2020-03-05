package com.TrainingSystem.entity;

public class Groupinfo {
	private int Group_ID;
	private String Group_Name;
	private String Group_Date;
	private int Is_Del;
	
	public Groupinfo(int gGroup_ID, String gGroup_Name,
			String gGroup_Date)
	{
		super();
		Group_ID = gGroup_ID;
		Group_Name = gGroup_Name;
		Group_Date = gGroup_Date;
	}

	public int getIs_Del() {
		return Is_Del;
	}

	public void setIs_Del(int is_Del) {
		Is_Del = is_Del;
	}

	public int getGroup_ID() {
		return Group_ID;
	}

	public void setGroup_ID(int group_ID) {
		Group_ID = group_ID;
	}

	public String getGroup_Name() {
		return Group_Name;
	}

	public void setGroup_Name(String group_Name) {
		Group_Name = group_Name;
	}

	public String getGroup_Date() {
		return Group_Date;
	}

	public void setGroup_Date(String group_Date) {
		Group_Date = group_Date;
	}
}
