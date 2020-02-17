package com.TrainingSystem.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TrainingSystem.util.Dbconn;
import com.TrainingSystem.entity.*;

public class Groupdao {
	public static Groupinfo selectbyid(String id)
	{
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		Groupinfo s = null;
		
		PreparedStatement ps = null;
		
		String sql = "";
		try {
			
			sql = "select * from GroupInfo where Group_ID=?";
		
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				s = new Groupinfo(
					 	rs.getString("Group_ID"),
					 	rs.getString("Group_Name"),
					 	rs.getString("Leader1_ID"),
					 	rs.getString("Leader2_ID")
					 );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
		
		return s;
	}
}
