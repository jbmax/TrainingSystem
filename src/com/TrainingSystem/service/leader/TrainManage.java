package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.TrainingSystem.util.Dbconn;

public class TrainManage {
	public static int totalnumAll()
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
					"FROM TrainInfo ti\n" + 
					"WHERE ti.Is_Del = 0 AND ti.Train_State != 3";
			
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
					"FROM GroupInfo gi, LeaderInfo li, TrainInfo ti, GroundInfo gr\n" + 
					"WHERE gi.Group_ID = li.Group_ID AND\n" + 
					"				ti.Leader_ID = li.Leader_ID AND \n" + 
					"				ti.Ground_ID = gr.Ground_ID AND \n" + 
					"				gi.Group_ID = ?" +
					" AND ti.Is_Del = 0 ";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(groupID));
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
	
	public static int totalnumOther(String groupID)
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
					"FROM GroupInfo gi, LeaderInfo li, TrainInfo ti, GroundInfo gr\n" + 
					"WHERE gi.Group_ID = li.Group_ID AND\n" + 
					"				ti.Leader_ID = li.Leader_ID AND \n" + 
					"				ti.Ground_ID = gr.Ground_ID AND \n" + 
					"				gi.Group_ID = ?" +
					" AND ti.Is_Del = 0 AND ti.Train_State != 3";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(groupID));
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

	public static ArrayList<Map<String, String>> selectTrainByIDOther(String groupID, int page, int limit)
	{
		ArrayList<Map<String, String>> lmap = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "\n" + 
					"	\n" + 
					"	SELECT ti.Train_ID TID, ti.Plan_ID PID, ti.Train_Date Date, gr.Ground_Name GName, \n" + 
					"					li.Leader_Name LName, ti.Train_Oper Oper, ti.Train_State State\n" + 
					"	FROM GroupInfo gi, LeaderInfo li, TrainInfo ti, GroundInfo gr\n" + 
					"	WHERE gi.Group_ID = li.Group_ID AND\n" + 
					"				ti.Leader_ID = li.Leader_ID AND \n" + 
					"				ti.Ground_ID = gr.Ground_ID AND \n" + 
					"				gi.Group_ID = ?" + 
					" AND ti.Is_Del = 0  AND ti.Train_State != 3 " + 
					"	ORDER BY ti.Train_ID DESC LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(groupID));
			ps.setInt(2, limit*(page-1));
			ps.setInt(3, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> hmap = new HashMap<String, String>();
				 hmap.put("TID", rs.getString("TID"));
				 hmap.put("PID", rs.getString("PID"));
				 hmap.put("Date", rs.getString("Date"));
				 hmap.put("GName", rs.getString("GName"));
				 hmap.put("LName", rs.getString("LName"));
				 hmap.put("Oper", rs.getString("Oper"));
				 String State = rs.getString("State");
				 String State_String = "未知";
				 switch (State) {
				 case "0": 
					 State_String = "未开始";
					 break;
				 case "1":
					 State_String = "正在进行";
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

	public static ArrayList<Map<String, String>> selectTrainAll(int page, int limit)
	{
		ArrayList<Map<String, String>> lmap = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "\n" + 
					"	\n" + 
					"	SELECT ti.Train_ID TID, ti.Plan_ID PID, ti.Train_Date Date, gr.Ground_Name GName, \n" + 
					"					li.Leader_Name LName, ti.Train_Oper Oper, ti.Train_State State\n" + 
					"	FROM GroupInfo gi, LeaderInfo li, TrainInfo ti, GroundInfo gr\n" + 
					"	WHERE gi.Group_ID = li.Group_ID AND\n" + 
					"				ti.Leader_ID = li.Leader_ID AND \n" + 
					"				ti.Ground_ID = gr.Ground_ID AND \n" + 
					" ti.Is_Del = 0  AND ti.Train_State != 3 " + 
					"	ORDER BY ti.Train_ID DESC LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, limit*(page-1));
			ps.setInt(2, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> hmap = new HashMap<String, String>();
				 hmap.put("TID", rs.getString("TID"));
				 hmap.put("PID", rs.getString("PID"));
				 hmap.put("Date", rs.getString("Date"));
				 hmap.put("GName", rs.getString("GName"));
				 hmap.put("LName", rs.getString("LName"));
				 hmap.put("Oper", rs.getString("Oper"));
				 String State = rs.getString("State");
				 String State_String = "未知";
				 switch (State) {
				 case "0": 
					 State_String = "未开始";
					 break;
				 case "1":
					 State_String = "正在进行";
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
	
	public static ArrayList<Map<String, String>> selectTrainByID(String groupID, int page, int limit)
	{
		ArrayList<Map<String, String>> lmap = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "\n" + 
					"	\n" + 
					"	SELECT ti.Train_ID TID, ti.Plan_ID PID, ti.Train_Date Date, gr.Ground_Name GName, \n" + 
					"					li.Leader_Name LName, ti.Train_Oper Oper, ti.Train_State State\n" + 
					"	FROM GroupInfo gi, LeaderInfo li, TrainInfo ti, GroundInfo gr\n" + 
					"	WHERE gi.Group_ID = li.Group_ID AND\n" + 
					"				ti.Leader_ID = li.Leader_ID AND \n" + 
					"				ti.Ground_ID = gr.Ground_ID AND \n" + 
					"				gi.Group_ID = ?" + 
					" AND ti.Is_Del = 0 " + 
					"	ORDER BY ti.Train_ID DESC LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(groupID));
			ps.setInt(2, limit*(page-1));
			ps.setInt(3, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> hmap = new HashMap<String, String>();
				 hmap.put("TID", rs.getString("TID"));
				 hmap.put("PID", rs.getString("PID"));
				 hmap.put("Date", rs.getString("Date"));
				 hmap.put("GName", rs.getString("GName"));
				 hmap.put("LName", rs.getString("LName"));
				 hmap.put("Oper", rs.getString("Oper"));
				 String State = rs.getString("State");
				 String State_String = "未知";
				 switch (State) {
				 case "0": 
					 State_String = "未开始";
					 break;
				 case "1":
					 State_String = "正在进行";
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
	
	public static int ChangeState(String tid, String state) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "UPDATE TrainInfo SET TrainInfo.Train_State = ?\n" + 
					"	WHERE TrainInfo.Train_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, state);
			ps.setInt(2, Integer.parseInt(tid));
			
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
	
	public static int insertTrain(String pid, String oper, String date, 
			String ground, String lid) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		int grid = GroundManage.selectGroundbyNum(ground);
		if (grid == 0) {
			return 0;
		}
		
		try {
			sql = "INSERT INTO TrainInfo VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, null);
			ps.setInt(2, Integer.parseInt(pid));
			ps.setInt(3, Integer.parseInt(lid));
			ps.setString(4, oper);
			ps.setInt(5, grid);
			ps.setString(6, date);
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			
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
