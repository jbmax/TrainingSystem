package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.TrainingSystem.util.Dbconn;

public class GroundManage {
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
					"FROM GroundInfo"
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

	public static ArrayList<String> selectGroundAval()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT Ground_Name GRName\n" + 
					"From GroundInfo\n"
					+ "WHERE GroundInfo.Ground_State = 1\n"
					+ " AND Is_Del = 0 " + 
					"ORDER BY Ground_Name";
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 String s = rs.getString("GRName");
				 list.add(s);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}

		return list;	
	}
	
	public static ArrayList<String> selectGroundAll()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT Ground_Name GRName\n" + 
					"From GroundInfo\n" +
					" AND Is_Del = 0 " +
					"ORDER BY Ground_Name";
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 String s = rs.getString("GRName");
				 list.add(s);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}

		return list;	
	}
	
	public static int selectGroundbyNum(String numStr)
	{
		int num = Integer.parseInt(numStr);
		if (num<1 || num>totalnum()) {
			return 0;
		}
		
		ArrayList<Integer> glist = new ArrayList<Integer>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT Ground_ID GRID\n" + 
					"From GroundInfo\n" + 
					"WHERE GroundInfo.Ground_State = 1\n" + 
					" AND Is_Del = 0 " +
					"ORDER BY Ground_Name";
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 int g = rs.getInt("GRID");
				 glist.add(g);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}

		return glist.get(num-1);
	}

	public static ArrayList<Map<String, String>>  selectGround(int page, int limit)
	{
		ArrayList<Map<String, String>> lmap = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT Ground_ID GRID, Ground_Name GName, Ground_State State\n" + 
					"From GroundInfo gi\n" + 
					"WHERE Is_Del = 0\n" + 
					"ORDER BY Ground_ID LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, limit*(page-1));
			ps.setInt(2, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> hmap = new HashMap<String, String>();
				 hmap.put("GRID", rs.getString("GRID"));
				 hmap.put("GName", rs.getString("GName"));
				 String State = rs.getString("State");
				 String State_String = "未知";
				 switch (State) {
				 case "0": 
					 State_String = "不可进行训练";
					 break;
				 case "1":
					 State_String = "可进行训练";
					 break;
				 default:
					 State_String = "未知";
				 }
				 hmap.put("State", State_String);
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
	
	public static int insertGround(String gname, String state) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "INSERT INTO GroundInfo VALUES(?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, null);
			ps.setString(2, gname);
			ps.setInt(3, Integer.parseInt(state));
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
	
	public static int updateGround(String gid, String gname, String state, int Is_Del) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			if (Is_Del == 1) {
				sql = "UPDATE GroundInfo SET GroundInfo.Is_Del = 1\n"
						+ "WHERE GroundInfo.Ground_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(gid));
			}
			else {
				sql = "UPDATE GroundInfo SET GroundInfo.Ground_Name = ?, \n"
					+ "GroundInfo.Ground_State = ?, GroundInfo.Is_Del = 0\n"
					+ "WHERE GroundInfo.Ground_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, gname);
				ps.setInt(2, Integer.parseInt(state));
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