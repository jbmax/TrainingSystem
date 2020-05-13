package com.TrainingSystem.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TrainingSystem.entity.Leaderinfo;
import com.TrainingSystem.util.Dbconn;

public class Leaderdao {
	public static Leaderinfo selectbyID(String Leader_ID)
	{
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		Leaderinfo l = null;
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			
			sql = "select * from LeaderInfo where Leader_ID=?"
					+ " AND Is_Del = 0";
		
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(Leader_ID));
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				l = new Leaderinfo(
						rs.getInt("Leader_ID"),
						rs.getString("Leader_Name"),
						rs.getString("Leader_Password"),
						rs.getString("Group_ID"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
		
		return l;
		
	}
	
	public static Leaderinfo verifyPass(String Leader_ID, String password)
	{
		Leaderinfo li = selectbyID(Leader_ID);
		
		if (li != null) {
			if (password.equals(li.getLeader_Password()))
			{
				return li;
			}
		}
			
		return null;
	}
}
