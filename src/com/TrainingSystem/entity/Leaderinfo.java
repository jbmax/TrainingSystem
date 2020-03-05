package com.TrainingSystem.entity;

public class Leaderinfo {
	private int Leader_ID;
	private String Leader_Name;
	private String Leader_Password;
	private String Group_ID;
	private int Is_Del;
	
	public Leaderinfo(int sLeader_ID, String sLeader_name, 
			String sLeader_pass, String sGroup_ID)
	{
		Leader_ID = sLeader_ID;
		Leader_Name = sLeader_name;
		Leader_Password = sLeader_pass;
		Group_ID = sGroup_ID;
	}
	
	public int getIs_Del() {
		return Is_Del;
	}

	public void setIs_Del(int is_Del) {
		Is_Del = is_Del;
	}

	public int getLeader_ID() {
		return Leader_ID;
	}
	public void setLeader_ID(int leader_ID) {
		Leader_ID = leader_ID;
	}
	public String getLeader_Name() {
		return Leader_Name;
	}
	public void setLeader_Name(String leader_Name) {
		Leader_Name = leader_Name;
	}
	public String getLeader_Password() {
		return Leader_Password;
	}
	public void setLeader_Password(String leader_Password) {
		Leader_Password = leader_Password;
	}

	public String getGroup_ID() {
		return Group_ID;
	}

	public void setGroup_ID(String group_ID) {
		Group_ID = group_ID;
	}
	
	
}
