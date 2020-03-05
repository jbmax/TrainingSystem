package com.TrainingSystem.entity;

public class Admininfo {
	private String Admin_ID;
	private String Admin_Name;
	private String Admin_Password;
	private int Is_Del;
	
	public Admininfo(String gGroup_ID, String gGroup_Name,
			String gGroup_Date)
	{
		super();
		Admin_ID = gGroup_ID;
		Admin_Name = gGroup_Name;
		Admin_Password = gGroup_Date;
	}
	
	public int getIs_Del() {
		return Is_Del;
	}

	public void setIs_Del(int is_Del) {
		Is_Del = is_Del;
	}



	public String getAdmin_ID() {
		return Admin_ID;
	}
	public void setAdmin_ID(String admin_ID) {
		Admin_ID = admin_ID;
	}
	public String getAdmin_Name() {
		return Admin_Name;
	}
	public void setAdmin_Name(String admin_Name) {
		Admin_Name = admin_Name;
	}
	public String getAdmin_Password() {
		return Admin_Password;
	}
	public void setAdmin_Password(String admin_Password) {
		Admin_Password = admin_Password;
	}
	
	
}
