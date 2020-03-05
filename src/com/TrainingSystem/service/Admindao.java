package com.TrainingSystem.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TrainingSystem.entity.Admininfo;
import com.TrainingSystem.util.Dbconn;

public class Admindao {
	public static Admininfo selectbyID(String Admin_ID)
	{
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		Admininfo a = null;
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			
			sql = "select * from Admininfo where Admin_ID=?"
					+ " AND Is_Del = 0";
		
			ps = conn.prepareStatement(sql);
			ps.setString(1, Admin_ID);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				a = new Admininfo(
						rs.getString("Admin_ID"),
						rs.getString("Admin_Name"),
						rs.getString("Admin_Password")
						);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
		
		return a;
		
	}
	
	public static Admininfo verifyPass(String Leader_ID, String password)
	{
		Admininfo a = selectbyID(Leader_ID);
		
		if (a != null) {
			if (password.equals(a.getAdmin_Password()))
			{
				return a;
			}
		}
		
		return null;
	}
}
