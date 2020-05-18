package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
			sql = "SELECT pi.Plan_ID PID, pi.Plan_StartDate SDate, pi.Plan_EndDate EDate, Plan_State State\n" + 
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
				 String edate = rs.getString("EDate");
				 hmap.put("EDate", edate);
				 String State = rs.getString("State");
				 String State_String = "未知";
				 switch (State) {
				 case "0": 
					 State_String = "未开始";
					 break;
				 case "1":
					 State_String = "正在进行";
					 String nowdate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
					 if (edate.compareTo(nowdate) < 0)
						 State_String = "已结束";
					 break;
				 case "2": 
					 State_String = "已结束";
					 break;
				 case "3":
					 State_String = "已撤销";
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
	
	public static int updatePlan(String pid, String sdate, String edate) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "UPDATE PlanInfo SET PlanInfo.Plan_StartDate = ?, \n"
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
			sql = "UPDATE PlanInfo SET PlanInfo.Plan_State = 3, PlanInfo.Is_Del = 1\n"
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
