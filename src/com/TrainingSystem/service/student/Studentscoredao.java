package com.TrainingSystem.service.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TrainingSystem.entity.Studentscore;
import com.TrainingSystem.util.Dbconn;

public class Studentscoredao {
	public static ArrayList<Studentscore> selectscoresByID(String Student_ID)
	{
		ArrayList<Studentscore> list = new ArrayList<Studentscore>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT * FROM studentscore WHERE SID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, Student_ID);
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Studentscore s = new Studentscore(
						 	rs.getString("SID"),
						 	rs.getString("SName"),
						 	rs.getString("Run3k"),
						 	rs.getString("Snake"),
						 	rs.getString("Situp"),
						 	rs.getString("Pullup"),
						 	rs.getString("Date")						 
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
