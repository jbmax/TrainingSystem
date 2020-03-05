package com.TrainingSystem.service.leader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.TrainingSystem.util.Dbconn;

public class StudentManage {
	public static int totalnum()
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
					"FROM StudentInfo"
					+ " WHERE Is_Del = 0";
			
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

	public static ArrayList<Map<String, String>>  selectStudent(int page, int limit)
	{
		ArrayList<Map<String, String>> lmap = new ArrayList<Map<String, String>>();
		
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "select si.Student_ID ID, si.Student_Name Name, si.Student_Birthday BDay, "
					+ "si.Student_Gender Gender, gi.Group_Name GName\n"
					+ " from StudentInfo si, GroupInfo gi "
					+ " WHERE si.Is_Del = 0 AND si.Group_ID = gi.Group_ID\n" +
					"ORDER BY si.Student_ID LIMIT ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, limit*(page-1));
			ps.setInt(2, limit);
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Map<String, String> hmap = new HashMap<String, String>();
				 hmap.put("ID", rs.getString("ID"));
				 hmap.put("Name", rs.getString("Name"));
				 hmap.put("Gender", rs.getString("Gender"));
				 hmap.put("BDay", rs.getString("BDay"));
				 hmap.put("GName", rs.getString("GName"));
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
		
	public static int updateStudent(String id, String name, String gender, String bday, int is_del, int pwd) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			if (is_del == 1) {
				sql = "UPDATE StudentInfo SET StudentInfo.Is_del = 1\n"
						+ "WHERE Student_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
			}
			else if (pwd == 1) {
				sql = "UPDATE StudentInfo SET Student_Password = '123456'\n"
						+ "WHERE Student_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
			}
			else {
				sql = "UPDATE StudentInfo SET Student_Name = ?, "
						+ "Student_Gender = ?, Student_Birthday = ?, \n"
					+ "Is_del = 0\n"
					+ "WHERE Student_ID = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, gender);
				ps.setString(3, bday);
				ps.setString(4, id);
			}
			
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
