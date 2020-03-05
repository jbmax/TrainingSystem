package com.TrainingSystem.entity;

public class GroundInfo {
	private int Ground_ID;
	private String Ground_Name;
	private int Ground_State;
	private int Is_Del;
	
	public GroundInfo(int sGround_ID, String sGround_Name,
			int iGround_State)
	{
		super();
		Ground_ID = sGround_ID;
		Ground_Name = sGround_Name;
		Ground_State = iGround_State;
	}

	public int getGround_ID() {
		return Ground_ID;
	}

	public void setGround_ID(int ground_ID) {
		Ground_ID = ground_ID;
	}

	public String getGround_Name() {
		return Ground_Name;
	}

	public void setGround_Name(String ground_Name) {
		Ground_Name = ground_Name;
	}

	public int getGround_State() {
		return Ground_State;
	}

	public void setGround_State(int ground_State) {
		Ground_State = ground_State;
	}

	public int getIs_Del() {
		return Is_Del;
	}

	public void setIs_Del(int is_Del) {
		Is_Del = is_Del;
	}
	

}
