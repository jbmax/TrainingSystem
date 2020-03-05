package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.TrainingSystem.util.Dbconn;

public class Groupscore {
	public static int totalnumVal(String groupID)
	{
		int count = 0;
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "SELECT count(*) num " +
					"FROM StudentInfo, StudentGrade, TrainInfo, LeaderInfo\n" + 
					"WHERE StudentInfo.Student_ID = StudentGrade.Student_ID AND\n" + 
					"				StudentGrade.Train_ID = TrainInfo.Train_ID AND \n" + 
					"				TrainInfo.Train_State = 2 AND \n" + 
					"				TrainInfo.Leader_ID = LeaderInfo.Leader_ID AND\n" + 
					"				LeaderInfo.Group_ID = ? AND StudentInfo.Is_Del = 0 \n" +
					" AND TrainInfo.Is_Del = 0 ";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupID);
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

	public static int searchnum(String groupID, String SName)
	{
		int count = 0;
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "SELECT count(*) num " + 
					"FROM StudentInfo, StudentGrade, TrainInfo, LeaderInfo\n" + 
					"WHERE StudentInfo.Student_ID = StudentGrade.Student_ID AND\n" + 
					"				StudentGrade.Train_ID = TrainInfo.Train_ID AND \n" + 
					"				TrainInfo.Train_State = 2 AND \n" + 
					"				TrainInfo.Leader_ID = LeaderInfo.Leader_ID AND\n" + 
					"				LeaderInfo.Group_ID = ?  AND StudentInfo.Is_Del = 0\n" + 
					" AND StudentInfo.Student_Name LIKE ?" + 
					" AND TrainInfo.Is_Del = 0 ";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupID);
			ps.setString(2, "%"+SName+"%");
			
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
	
	public static ArrayList<Map<String, String>> selectscoresByID(String groupID, int page, int limit)
	{
		ArrayList<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT StudentInfo.Student_ID SID, StudentInfo.Student_Name SName,  \n" + 
					"				StudentGrade.Item1_Grade Run3k, StudentGrade.Item2_Grade Snake, \n" + 
					"				StudentGrade.Item3_Grade Situp, StudentGrade.Item4_Grade Pullup, \n" + 
					"				TrainInfo.Train_Date Date\n" + 
					"FROM StudentInfo, StudentGrade, TrainInfo, LeaderInfo\n" + 
					"WHERE StudentInfo.Student_ID = StudentGrade.Student_ID AND\n" + 
					"				StudentGrade.Train_ID = TrainInfo.Train_ID AND \n" + 
					"				TrainInfo.Train_State = 2 AND \n" + 
					"				TrainInfo.Leader_ID = LeaderInfo.Leader_ID AND\n" + 
					"				LeaderInfo.Group_ID = ?  AND StudentInfo.Is_Del = 0 \n" + 
					" AND TrainInfo.Is_Del = 0 " +
					"ORDER BY TrainInfo.Train_Date DESC LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupID);
			ps.setInt(2, limit*(page-1));
			ps.setInt(3, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> mapObj = new HashMap<String, String>();
				 mapObj.put("SID", rs.getString("SID"));
				 mapObj.put("SName", rs.getString("SName"));
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
	
	public static ArrayList<Map<String, String>> selectscoresByName(String groupID, String SName, int page, int limit)
	{
		ArrayList<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT StudentInfo.Student_ID SID, StudentInfo.Student_Name SName,  \n" + 
					"				StudentGrade.Item1_Grade Run3k, StudentGrade.Item2_Grade Snake, \n" + 
					"				StudentGrade.Item3_Grade Situp, StudentGrade.Item4_Grade Pullup, \n" + 
					"				TrainInfo.Train_Date Date\n" + 
					"FROM StudentInfo, StudentGrade, TrainInfo, LeaderInfo\n" + 
					"WHERE StudentInfo.Student_ID = StudentGrade.Student_ID AND\n" + 
					"				StudentGrade.Train_ID = TrainInfo.Train_ID AND \n" + 
					"				TrainInfo.Train_State = '2' AND \n" + 
					"				TrainInfo.Leader_ID = LeaderInfo.Leader_ID AND\n" + 
					"				LeaderInfo.Group_ID = ? " +
					" AND TrainInfo.Is_Del = 0 AND StudentInfo.Is_Del = 0 AND " +
					"StudentInfo.Student_Name LIKE ? "
					+ "ORDER BY TrainInfo.Train_Date DESC LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupID);
			ps.setString(2, "%" + SName + "%");
			ps.setInt(3, limit*(page-1));
			ps.setInt(4, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> mapObj = new HashMap<String, String>();
				 mapObj.put("SID", rs.getString("SID"));
				 mapObj.put("SName", rs.getString("SName"));
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
