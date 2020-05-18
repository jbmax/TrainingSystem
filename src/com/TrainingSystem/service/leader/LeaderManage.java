package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.TrainingSystem.util.Dbconn;

public class LeaderManage {
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
					"From LeaderInfo li, GroupInfo gi\n"
					+ "WHERE li.Is_Del = 0 AND li.Group_ID = gi.Group_ID ";
			
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

	public static ArrayList<Map<String, String>>  selectLeader(int page, int limit)
	{
		ArrayList<Map<String, String>> lmap = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT li.Leader_ID ID, li.Leader_Name Name, "
					+ "gi.Group_Name GName\n" + 
					"From LeaderInfo li, GroupInfo gi\n"
					+ "WHERE li.Is_Del = 0 AND li.Group_ID = gi.Group_ID " + 
					"ORDER BY li.Leader_ID LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, limit*(page-1));
			ps.setInt(2, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> hmap = new HashMap<String, String>();
				 hmap.put("ID", rs.getString("ID"));
				 hmap.put("Name", rs.getString("Name"));
				 hmap.put("GName", rs.getString("GName"));
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
		
	public static int updateLeader(String id, String name, String groupname, int Is_Del, int pwd) throws SQLException
	{
		//声明结果集
		int rs = 0;
		ResultSet rs2 = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		String sql = "";
		
		try {
			if (Is_Del == 1) {
				sql = "UPDATE LeaderInfo SET LeaderInfo.Is_Del = 1\n"
						+ "WHERE Leader_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(id));
			}
			else if (pwd == 1) {
				sql = "UPDATE LeaderInfo SET LeaderInfo.Leader_Password = '123456'\n"
						+ "WHERE Leader_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(id));
			}
			else {
				int group = 0;
				sql = "SELECT Group_ID FROM GroupInfo WHERE Group_Name = ?";
				ps2 = conn.prepareStatement(sql);
				ps2.setString(1, groupname);
				rs2 = ps2.executeQuery();
				while(rs2.next()) {
					group = rs2.getInt("Group_ID");
				}
				
				if (group == 0) {
					return 0;
				}
				
				sql = "UPDATE LeaderInfo SET Leader_Name = ?, \n"
					+ "Group_ID = ?, Is_Del = 0\n"
					+ "WHERE LeaderInfo.Leader_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, group);
				ps.setInt(3, Integer.parseInt(id));
			}
			
			rs = ps.executeUpdate();
			
			if (rs > 0)
				return 1;
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs2, ps2, conn);
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}
		
		return 0;
	}

	public static int addLeader(String name, String groupname) throws SQLException
	{
		//声明结果集
		int rs = 0;
		ResultSet rs2 = null;
		
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		String sql = "";
		
		try {
			int group = 0;
			sql = "SELECT Group_ID FROM GroupInfo WHERE Group_Name = ?";
			ps2 = conn.prepareStatement(sql);
			ps2.setString(1, groupname);
			rs2 = ps2.executeQuery();
			while(rs2.next()) {
				group = rs2.getInt("Group_ID");
			}
			
			if (group == 0) {
				return 0;
			}
				
			sql = "INSERT INTO LeaderInfo VALUES(0, ?, ?, '123456', 0)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, group);
			
			rs = ps.executeUpdate();
			
			if (rs > 0)
				return 1;
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs2, ps2, conn);
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}
		
		return 0;
	}
}
