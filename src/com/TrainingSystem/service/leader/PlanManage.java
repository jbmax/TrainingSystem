package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.TrainingSystem.util.Dbconn;

public class PlanManage {	
	public static int totalnumVal()
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
					"FROM PlanInfo pi\n"
					+ "WHERE pi.Is_Del = 0";
			
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
	
	public static ArrayList<Map<String, String>> selectPlan(int page, int limit)
	{
		ArrayList<Map<String, String>> lmap = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT pi.Plan_ID PID, pi.Plan_StartDate SDate, pi.Plan_EndDate EDate, \n" + 
					"		CASE WHEN pi.Plan_State = 0 THEN '未开始'\n" + 
					"		 WHEN pi.Plan_State = 1 THEN '正在进行'\n" + 
					"		 WHEN pi.Plan_State = 2 THEN '已结束'\n" + 
					"		 WHEN pi.Plan_State = 3 THEN '已撤销'\n" + 
					"		ELSE '未知'\n" + 
					"		END\n" + 
					"		AS State\n" + 
					"FROM PlanInfo pi\n"
					+ "WHERE pi.Is_Del = 0 \n" + 
					"ORDER BY pi.Plan_ID DESC LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
		
			ps.setInt(1, limit*(page-1));
			ps.setInt(2, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> hmap = new HashMap<String, String>();
				 hmap.put("PID", rs.getString("PID"));
				 hmap.put("SDate", rs.getString("SDate"));
				 hmap.put("EDate", rs.getString("EDate"));
				 hmap.put("State", rs.getString("State"));
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
	
	public static int updatePlan(String pid, String sdate, String edate) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "UPDATE Planinfo SET PlanInfo.Plan_StartDate = ?, \n"
					+ "PlanInfo.Plan_EndDate = ?\n"
					+ "WHERE PlanInfo.Plan_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, sdate);
			ps.setString(2, edate);
			ps.setInt(3, Integer.parseInt(pid));
			
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
	
	public static int insertPlan(String sdate, String edate) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "INSERT INTO PlanInfo VALUES(?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, null);
			ps.setString(2, sdate);
			ps.setString(3, edate);
			ps.setInt(4, 1);
			ps.setInt(5, 0);
			
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
	
	public static int deletePlan(String pid) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "UPDATE Planinfo SET PlanInfo.Plan_State = 3, PlanInfo.Is_Del = 1\n"
					+ "WHERE PlanInfo.Plan_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(pid));
			
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
