package com.TrainingSystem.service.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.TrainingSystem.util.Dbconn;

public class Studentscoredao {
	public static int totalnum(String sid)
	{
		int count = 0;
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "SELECT count(*) num \n" +
					"FROM StudentInfo, StudentGrade, TrainInfo\n" + 
					"WHERE StudentInfo.Student_ID = StudentGrade.Student_ID AND\n" + 
					"				StudentGrade.Train_ID = TrainInfo.Train_ID AND \n" + 
					"				TrainInfo.Train_State = 2 AND \n" + 
					"				StudentInfo.Student_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			 while(rs.next()) {
				 count = rs.getInt("num");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
			
		return count;
	}

	
	public static ArrayList<Map<String, String>> selectscoresByID(String Student_ID, int page, int limit)
	{
		ArrayList<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT StudentInfo.Student_ID SID, TrainInfo.Train_Date Date, \n" + 
					"				StudentGrade.Item1_Grade Run3k, StudentGrade.Item2_Grade Snake, \n" + 
					"				StudentGrade.Item3_Grade Situp, StudentGrade.Item4_Grade Pullup\n" + 
					"FROM StudentInfo, StudentGrade, TrainInfo\n" + 
					"WHERE StudentInfo.Student_ID = StudentGrade.Student_ID AND\n" + 
					"				StudentGrade.Train_ID = TrainInfo.Train_ID AND \n" + 
					"				TrainInfo.Train_State = 2 AND \n" + 
					"				StudentInfo.Student_ID = ?\n" + 
					"ORDER BY Date DESC LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, Student_ID);
			ps.setInt(2, limit*(page-1));
			ps.setInt(3, limit);
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> mapObj = new HashMap<String, String>();
				 mapObj.put("Run3k", rs.getString("Run3k"));
				 mapObj.put("Snake", rs.getString("Snake"));
				 mapObj.put("Situp", rs.getString("Situp"));
				 mapObj.put("Pullup", rs.getString("Pullup"));
				 mapObj.put("Date", rs.getString("Date"));
				
				 mapList.add(mapObj);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
		
		return mapList;	
	}
}
