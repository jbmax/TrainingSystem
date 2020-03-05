package com.TrainingSystem.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TrainingSystem.util.Dbconn;
import com.TrainingSystem.entity.*;

public class Studentdao {
	public static Studentinfo selectbyid(String id)
	{
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		Studentinfo s = null;
		
		PreparedStatement ps = null;
		
		String sql = "";
		try {
			
			sql = "select * from StudentInfo where Student_ID=?"
					+ " AND Is_Del = 0";
		
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				s = new Studentinfo(
					 	rs.getString("Student_ID"),
					 	rs.getString("Student_Name"),
					 	rs.getString("Student_Password"),
					 	rs.getString("Student_Gender"),
					 	rs.getString("Student_Birthday"),
					 	rs.getString("Group_ID")
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
	
	public static int update(Studentinfo s)
	{
		String sql = "update StudentInfo SET Student_Name=?, Student_Password=?, Student_Gender=?, Student_Birthday=? where Student_ID=?";
		
		Object[] params = {
				s.getStudent_Name(),
				s.getStudent_Password(),
				s.getStudent_Gender(),
				s.getStudent_Birthday(),
				//s.getGroup_ID(),
				s.getStudent_ID()
		};
		
		return Dbconn.exectuIUD(sql, params);
	}
	
	public static Studentinfo verifyPass(String uid, String password) throws Exception
	{
		Studentinfo si = selectbyid(uid);
		
		if (si != null) {
			if (password.equals(si.getStudent_Password()))
			{
				return si;
			}
		}

		return null;
	}
}
