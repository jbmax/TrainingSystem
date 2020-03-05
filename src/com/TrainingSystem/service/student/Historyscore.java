package com.TrainingSystem.service.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TrainingSystem.util.Dbconn;

public class Historyscore {
	public static ArrayList<Integer> getItemMax(String Student_ID)
	{
		ArrayList<Integer> max_list = new ArrayList<Integer>();

		//声明结果集
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;		
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement psitem1 = null;
		PreparedStatement psitem2 = null;
		PreparedStatement psitem3 = null;
		PreparedStatement psitem4 = null;
		
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		
		try {
			sql1 = "SELECT max(sc.Item1Score) maxscore\n" + 
					"FROM studentitem1score sc\n" + 
					"WHERE SID = ?";
			psitem1 = conn.prepareStatement(sql1);
			psitem1.setString(1, Student_ID);

			sql2 = "SELECT max(sc.Item2Score) maxscore\n" + 
					"FROM studentitem2score sc\n" + 
					"WHERE SID = ?";			
			psitem2 = conn.prepareStatement(sql2);
			psitem2.setString(1, Student_ID);

			sql3 = "SELECT max(sc.Item3Score) maxscore\n" + 
					"FROM studentitem3score sc\n" + 
					"WHERE SID = ?";
			psitem3 = conn.prepareStatement(sql3);
			psitem3.setString(1, Student_ID);
			
			sql4 = "SELECT max(sc.Item4Score) maxscore\n" + 
					"FROM studentitem4score sc\n" + 
					"WHERE SID = ?";
			psitem4 = conn.prepareStatement(sql4);
			psitem4.setString(1, Student_ID);
			
			rs1 = psitem1.executeQuery();
			rs2 = psitem2.executeQuery();
			rs3 = psitem3.executeQuery();
			rs4 = psitem4.executeQuery();
			
			 while(rs1.next()) {
			 	max_list.add(Integer.parseInt(rs1.getString("maxscore")));
			 }
			 while(rs2.next()) {
			 	max_list.add(Integer.parseInt(rs2.getString("maxscore")));
			 }
			 while(rs3.next()) {
			 	max_list.add(Integer.parseInt(rs3.getString("maxscore")));
			 }
			 while(rs4.next()) {				 	
			 	max_list.add(Integer.parseInt(rs4.getString("maxscore")));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs1, psitem1, conn);
			Dbconn.closeall(rs2, psitem2, conn);
			Dbconn.closeall(rs3, psitem3, conn);
			Dbconn.closeall(rs4, psitem4, conn);
		}
		
		return max_list;	
	}
	
	public static ArrayList<Integer> getItemLast(String Student_ID)
	{
		ArrayList<Integer> self_list = new ArrayList<Integer>();
		
		//声明结果集
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;		
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement psitem1 = null;
		PreparedStatement psitem2 = null;
		PreparedStatement psitem3 = null;
		PreparedStatement psitem4 = null;
		
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		
		try {
			sql1 = "SELECT tc.Item1Score last\n" + 
					"FROM studentitem1score tc\n" + 
					"WHERE tc.SID = ? AND tc.date IN\n" + 
					"(\n" + 
					"	SELECT max(sc.date) lastdate\n" + 
					"	FROM studentitem1score sc\n" + 
					"	WHERE sc.SID = ?\n" + 
					")";
			psitem1 = conn.prepareStatement(sql1);
			psitem1.setString(1, Student_ID);	
			psitem1.setString(2, Student_ID);	
			
			sql2 = "SELECT tc.Item2Score last\n" + 
					"FROM studentitem2score tc\n" + 
					"WHERE tc.SID = ? AND tc.date IN\n" + 
					"(\n" + 
					"	SELECT max(sc.date) lastdate\n" + 
					"	FROM studentitem2score sc\n" + 
					"	WHERE sc.SID = ?\n" + 
					")";
			psitem2 = conn.prepareStatement(sql2);
			psitem2.setString(1, Student_ID);	
			psitem2.setString(2, Student_ID);

			sql3 = "SELECT tc.Item3Score last\n" + 
					"FROM studentitem3score tc\n" + 
					"WHERE tc.SID = ? AND tc.date IN\n" + 
					"(\n" + 
					"	SELECT max(sc.date) lastdate\n" + 
					"	FROM studentitem3score sc\n" + 
					"	WHERE sc.SID = ?\n" + 
					")";
			psitem3 = conn.prepareStatement(sql3);
			psitem3.setString(1, Student_ID);	
			psitem3.setString(2, Student_ID);
			
			sql4 = "SELECT tc.Item4Score last\n" + 
					"FROM studentitem4score tc\n" + 
					"WHERE tc.SID = ? AND tc.date IN\n" + 
					"(\n" + 
					"	SELECT max(sc.date) lastdate\n" + 
					"	FROM studentitem4score sc\n" + 
					"	WHERE sc.SID = ?\n" + 
					")";
			psitem4 = conn.prepareStatement(sql4);
			psitem4.setString(1, Student_ID);	
			psitem4.setString(2, Student_ID);
			
			rs1 = psitem1.executeQuery();
			rs2 = psitem2.executeQuery();
			rs3 = psitem3.executeQuery();
			rs4 = psitem4.executeQuery();
			
			 while(rs1.next()) {
			 	self_list.add(Integer.parseInt(rs1.getString("last")));
			 }
			 while(rs2.next()) {
				 	self_list.add(Integer.parseInt(rs2.getString("last")));
				 }
			 while(rs3.next()) {
				 self_list.add(Integer.parseInt(rs3.getString("last")));
				 }
			 while(rs4.next()) {				 	
				 self_list.add(Integer.parseInt(rs4.getString("last")));
				 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs1, psitem1, conn);
			Dbconn.closeall(rs2, psitem2, conn);
			Dbconn.closeall(rs3, psitem3, conn);
			Dbconn.closeall(rs4, psitem4, conn);
		}
		
		return self_list;
	}
}
