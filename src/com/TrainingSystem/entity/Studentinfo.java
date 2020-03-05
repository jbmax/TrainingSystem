package com.TrainingSystem.entity;

public class Studentinfo {
	private String Student_ID;
	private String Student_Name;
	private String Student_Password;
	private String Student_Gender;
	private String Student_Birthday;
	private int Is_Del;
	
	public int getIs_Del() {
		return Is_Del;
	}

	public void setIs_Del(int is_Del) {
		Is_Del = is_Del;
	}

	public String getGroup_ID() {
		return Group_ID;
	}

	public void setGroup_ID(String group_ID) {
		Group_ID = group_ID;
	}
	private String Group_ID;
	
	public Studentinfo(String sStudent_ID, String sStudent_Name,
			String sStudent_Password, String sStudent_Gender,
			String sStudent_Birthday, String sGroup_ID) {
		super();
		Student_ID = sStudent_ID;
		Student_Name = sStudent_Name;
		Student_Password = sStudent_Password;
		Student_Gender = sStudent_Gender;
		Student_Birthday = sStudent_Birthday;
		Group_ID = sGroup_ID;
	}
	
	public String getStudent_ID() {
		return Student_ID;
	}
	public void setStudent_ID(String student_ID) {
		Student_ID = student_ID;
	}
	public String getStudent_Name() {
		return Student_Name;
	}
	public void setStudent_Name(String student_Name) {
		Student_Name = student_Name;
	}
	public String getStudent_Password() {
		return Student_Password;
	}
	public void setStudent_Password(String student_Password) {
		Student_Password = student_Password;
	}
	public String getStudent_Gender() {
		return Student_Gender;
	}
	public void setStudent_Gender(String student_Gender) {
		Student_Gender = student_Gender;
	}
	public String getStudent_Birthday() {
		return Student_Birthday;
	}
	public void setStudent_Birthday(String student_Birthday) {
		Student_Birthday = student_Birthday;
	}
}
