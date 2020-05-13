package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.TrainingSystem.util.Dbconn;

public class GroupManage {
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
					"FROM GroupInfo"
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

	public static String groupStudentnum(String groupID)
	{
		String count = null;
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "SELECT count(*) num\n" + 
					"FROM GroupInfo gi, StudentInfo si\n" + 
					"WHERE gi.Is_Del = 0 AND si.Is_Del = 0 AND\n" + 
					"		si.Group_ID = gi.Group_ID AND gi.Group_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, groupID);
			rs = ps.executeQuery();
			 while(rs.next()) {
				 count = rs.getString("num");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
			
		return count;
	}

	public static ArrayList<Map<String, String>>  selectGroup(int page, int limit)
	{
		ArrayList<Map<String, String>> lmap = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT Group_ID GID, Group_Name GName, Group_Date Date\n" + 
					"From GroupInfo\n"
					+ "WHERE Is_Del = 0 " + 
					"ORDER BY Group_ID LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, limit*(page-1));
			ps.setInt(2, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> hmap = new HashMap<String, String>();
				 String groupID = rs.getString("GID");
				 hmap.put("GID", groupID);
				 hmap.put("Date", rs.getString("Date"));
				 hmap.put("GName", rs.getString("GName"));
				 hmap.put("Num", groupStudentnum(groupID));
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
	
	public static int insertGroup(String gname, String gdate) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "INSERT INTO GroupInfo VALUES(?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, null);
			ps.setString(2, gname);
			ps.setString(3, gdate);
			ps.setInt(4, 0);
			
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
	
	public static int updateGroup(String gid, String gname, String gdate, int Is_Del) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			if (Is_Del == 1) {
				sql = "UPDATE GroupInfo SET GroupInfo.Is_Del = 1\n"
						+ "WHERE GroupInfo.Group_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(gid));
			}
			else {
				sql = "UPDATE GroupInfo SET GroupInfo.Group_Name = ?, \n"
					+ "GroupInfo.Group_Date = ?, GroupInfo.Is_Del = 0\n"
					+ "WHERE GroupInfo.Group_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, gname);
				ps.setString(2, gdate);
				ps.setInt(3, Integer.parseInt(gid));
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
	
}
