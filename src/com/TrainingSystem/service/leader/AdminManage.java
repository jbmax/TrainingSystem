package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.TrainingSystem.util.Dbconn;

public class AdminManage {
	public static int totalnum()
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
					"FROM AdminInfo"
					+ " WHERE Is_Del = 0";
			
			ps = conn.prepareStatement(sql);
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

	public static ArrayList<Map<String, String>>  selectAdmin(int page, int limit)
	{
		ArrayList<Map<String, String>> lmap = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT Admin_ID ID, Admin_Name Name\n" + 
					"From AdminInfo\n"
					+ "WHERE Is_Del = 0 " + 
					"ORDER BY Admin_ID LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, limit*(page-1));
			ps.setInt(2, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> hmap = new HashMap<String, String>();
				 hmap.put("ID", rs.getString("ID"));
				 hmap.put("Name", rs.getString("Name"));
				 lmap.add(hmap);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}

		return lmap;	
	}
		
	public static int updateAdmin(String id, String name, int Is_Del, int pwd) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			if (Is_Del == 1) {
				sql = "UPDATE AdminInfo SET AdminInfo.Is_Del = 1\n"
						+ "WHERE Admin_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
			}
			else if (pwd == 1) {
				sql = "UPDATE AdminInfo SET AdminInfo.Admin_Password = '123456'\n"
						+ "WHERE Admin_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
			}
			else {
				sql = "UPDATE AdminInfo SET Admin_Name = ?, \n"
					+ "Is_Del = 0\n"
					+ "WHERE AdminInfo.Admin_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, id);
			}
			
			rs = ps.executeUpdate();
			
			if (rs > 0)
				return 1;
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}
		
		return 0;
	}
	public static int addAdmin(String id, String name) throws SQLException
	{
		//声明结果集
		int rs = 0;
		
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			
			sql = "INSERT INTO AdminInfo VALUES(?, ?, '123456', 0)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			
			rs = ps.executeUpdate();
			
			if (rs > 0)
				return 1;
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}
		
		return 0;
	}
}
