package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TrainingSystem.entity.Healthinfo;
import com.TrainingSystem.util.Dbconn;

public class Grouphealthdao {
	public static int totalnum(String groupID)
	{
		int count = 0;
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "SELECT count(*) num\n" + 
					"FROM HealthInfo hh, StudentInfo si, StudentAgeCal sa\n" + 
					"WHERE hh.Student_ID = si.Student_ID AND \n" + 
					"		hh.Student_ID = sa.SID AND\n" + 
					"		si.Group_ID = ? AND si.Is_Del = 0 ";
			
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

	public static int searchnum(String groupID, String SID)
	{
		int count = 0;
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "SELECT count(*) num\n" + 
					"FROM HealthInfo hh, StudentInfo si, StudentAgeCal sa\n" + 
					"WHERE hh.Student_ID = si.Student_ID AND \n" + 
					"		hh.Student_ID = sa.SID AND\n" + 
					"		si.Group_ID = ? AND si.Is_Del = 0 AND" + 
					"		si.Student_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupID);
			ps.setString(2, SID);
			
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
	
	public static ArrayList<Healthinfo> selectscoresByID(String groupID, int page, int limit)
	{
		ArrayList<Healthinfo> list = new ArrayList<Healthinfo>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT hh.Student_ID SID, hh.Health_Date Date, hh.Health_Weight Weight, hh.Health_Height Height,\n" + 
					"		FORMAT((10000 * hh.Health_Weight / (hh.Health_Height * hh.Health_Height)), 2) BMI, \n" + 
					"		sa.SAge SAge, sa.SGender SGender, si.Student_Name SName\n" + 
					"FROM HealthInfo hh, StudentInfo si, StudentAgeCal sa\n" + 
					"WHERE hh.Student_ID = si.Student_ID AND \n" + 
					"		hh.Student_ID = sa.SID AND\n" + 
					"		si.Group_ID = ?  AND si.Is_Del = 0 \n" + 
					"ORDER BY hh.Health_Date DESC LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupID);
			ps.setInt(2, limit*(page-1));
			ps.setInt(3, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Healthinfo h = new Healthinfo (
						 	rs.getString("SID"),
						 	rs.getString("SName"),
						 	rs.getString("Date"),
						 	rs.getString("SGender"),
						 	rs.getString("SAge"),
						 	rs.getString("Weight"),
						 	rs.getString("Height"),
						 	rs.getString("BMI")
						 );
				 
				 list.add(h);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
		
		return list;
	}

	public static ArrayList<Healthinfo> selectscoresByName(String groupID, String SID, int page, int limit)
	{
		ArrayList<Healthinfo> list = new ArrayList<Healthinfo>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT hh.Student_ID SID, hh.Health_Date Date, hh.Health_Weight Weight, hh.Health_Height Height,\n" + 
					"		FORMAT((10000 * hh.Health_Weight / (hh.Health_Height * hh.Health_Height)), 2) BMI, \n" + 
					"		sa.SAge SAge, sa.SGender SGender, si.Student_Name SName\n" + 
					"FROM HealthInfo hh, StudentInfo si, StudentAgeCal sa\n" + 
					"WHERE hh.Student_ID = si.Student_ID AND \n" + 
					"		hh.Student_ID = sa.SID AND\n" + 
					"		si.Group_ID = ? AND si.Is_Del = 0 AND " + 
					"		si.Student_ID = ? \n" +
					"ORDER BY hh.Health_Date DESC LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupID);
			ps.setString(2, SID);
			ps.setInt(3, limit*(page-1));
			ps.setInt(4, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Healthinfo h = new Healthinfo (
						 	rs.getString("SID"),
						 	rs.getString("SName"),
						 	rs.getString("Date"),
						 	rs.getString("SGender"),
						 	rs.getString("SAge"),
						 	rs.getString("Weight"),
						 	rs.getString("Height"),
						 	rs.getString("BMI")
						 );
				 
				 list.add(h);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
		
		return list;
	}
}
