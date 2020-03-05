package com.TrainingSystem.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.TrainingSystem.util.Dbconn;

public class ChangePassdao {
	public static int changePassword(String userClass, String userID, String userPass) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		try {
			if (userClass.equals("s")) {
				sql = "UPDATE StudentInfo SET Student_Password = ?\n" + 
					"WHERE Student_ID = ?";
			}
			else if (userClass.equals("l")){
				sql = "UPDATE LeaderInfo SET Leader_Password = ?\n" + 
						"WHERE Leader_ID = ?";
			}
			else if (userClass.equals("a")){
				sql = "UPDATE AdminInfo SET Admin_Password = ?\n" + 
						"WHERE Admin_ID = ?";
			}
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, userPass);
			ps.setString(2, userID);
			
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
