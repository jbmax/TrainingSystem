package com.TrainingSystem.service.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TrainingSystem.util.Dbconn;

public class ItemScore {
	public static ArrayList<ArrayList<String>> getItemscoresByID(String Student_ID)
	{
		ArrayList<ArrayList<String>> slist = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> date_list = new ArrayList<String>();
		ArrayList<String> item1_list = new ArrayList<String>();
		ArrayList<String> item2_list = new ArrayList<String>();
		ArrayList<String> item3_list = new ArrayList<String>();
		ArrayList<String> item4_list = new ArrayList<String>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT * FROM StudentItemScore WHERE SID = ? "
					+ "ORDER BY StudentItemScore.date Limit 10";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, Student_ID);
			
			rs = ps.executeQuery();
			
			date_list.add("日期");
			item1_list.add("3000米");
			item2_list.add("蛇形跑");
			item3_list.add("仰卧起坐");
			item4_list.add("引体向上");
			
			 while(rs.next()) {
			 	String date = rs.getString("date");
			 	String item1score = rs.getString("Item1Score");
			 	String item2score = rs.getString("Item2Score");
			 	String item3score = rs.getString("Item3Score");
			 	String item4score = rs.getString("Item4Score");
			 	
				date_list.add(date);
				item1_list.add(item1score);
				item2_list.add(item2score);
				item3_list.add(item3score);
				item4_list.add(item4score);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}

		slist.add(date_list);
		slist.add(item1_list);
		slist.add(item2_list);
		slist.add(item3_list);
		slist.add(item4_list);
		
		return slist;	
	}
}
