package com.TrainingSystem.service.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TrainingSystem.util.Dbconn;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HealthBMI {
	public static  String selectHealthByID(String Student_ID)
	{
		String jsonStr = null;
		
		//声明结果集
		ResultSet rs = null;
		
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		JSONObject jsonObj = new JSONObject(true);
		
		try {
			sql = "SELECT hh.Student_ID SID, hh.Health_Date Date, "
					+ "hh.Health_Weight Weight, hh.Health_Height Height,\n" + 
	"FORMAT((10000 * hh.Health_Weight / (hh.Health_Height * hh.Health_Height)), 2) BMI\n" + 
					"FROM HealthInfo hh\n" + 
					"WHERE hh.Student_ID = ?\n" + 
					"ORDER BY hh.Health_Date";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, Student_ID);
			rs = ps.executeQuery();
			 while(rs.next()) {
				 String date = rs.getString("Date");
				 String weight = rs.getString("Weight");
				 String bmi = rs.getString("BMI");
				 ArrayList<Float> health_list = new ArrayList<Float>();
				 health_list.add(Float.parseFloat(weight));
				 health_list.add(Float.parseFloat(bmi));
				 jsonObj.put(date, health_list);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				Dbconn.closeall(rs, ps, conn);
			}
		
		jsonStr = JSON.toJSONString(jsonObj);
		
		return jsonStr;
	}
}
