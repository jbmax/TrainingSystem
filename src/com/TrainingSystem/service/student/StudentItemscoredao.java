package com.TrainingSystem.service.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TrainingSystem.entity.StudentItemscore;
import com.TrainingSystem.util.Dbconn;

public class StudentItemscoredao {
	public static ArrayList<StudentItemscore> selectscoresByID(String Student_ID)
	{
		ArrayList<StudentItemscore> list = new ArrayList<StudentItemscore>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT * FROM StudentItemscore WHERE SID = ? ORDER BY date";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, Student_ID);
			rs = ps.executeQuery();
			 while(rs.next()) {
				 StudentItemscore s = new StudentItemscore(
						 	rs.getString("SID"),
						 	rs.getString("date"),
						 	rs.getString("Item1Score"),
						 	rs.getString("Item2Score"),
						 	rs.getString("Item3Score"),
						 	rs.getString("Item4Score")						 
						 );	 
				 
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
}
