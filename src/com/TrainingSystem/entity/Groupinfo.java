package com.TrainingSystem.entity;

public class Groupinfo {
	private String Group_ID;
	private String Group_Name;
	private String Leader1_ID;
	private String Leader2_ID;
	
	public Groupinfo(String gGroup_ID, String gGroup_Name,
			String gGroup_Leader1, String gGroup_Leader2)
	{
		super();
		Group_ID = gGroup_ID;
		Group_Name = gGroup_Name;
		Leader1_ID = gGroup_Leader1;
		Leader2_ID = gGroup_Leader2;
	}

	public String getGroup_ID() {
		return Group_ID;
	}

	public void setGroup_ID(String group_ID) {
		Group_ID = group_ID;
	}

	public String getGroup_Name() {
		return Group_Name;
	}

	public void setGroup_Name(String group_Name) {
		Group_Name = group_Name;
	}

	public String getLeader1_ID() {
		return Leader1_ID;
	}

	public void setLeader1_ID(String leader1_ID) {
		Leader1_ID = leader1_ID;
	}

	public String getLeader2_ID() {
		return Leader2_ID;
	}

	public void setLeader2_ID(String leader2_ID) {
		Leader2_ID = leader2_ID;
	}


}
