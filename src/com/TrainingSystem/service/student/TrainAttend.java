package com.TrainingSystem.service.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.TrainingSystem.util.Dbconn;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class TrainAttend {
	public static  String selectAttendByID(String Student_ID)
	{
		String jsonStr = null;
		
		//声明结果集
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		
		String sql1 = "";
		String sql2 = "";
		
		JSONObject jsonObj = new JSONObject(true);
		
		try {
			sql1 = "SELECT DISTINCT TrainInfo.Train_Date date\n" + 
					"FROM TrainInfo, StudentInfo, LeaderInfo\n" + 
					"WHERE StudentInfo.Group_ID = LeaderInfo.Group_ID AND "
					+ "LeaderInfo.Leader_ID =  TrainInfo.Leader_ID AND " +
					"StudentInfo.Student_ID = ? ORDER BY date";
			
			ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, Student_ID);
			rs1 = ps1.executeQuery();
			
			while(rs1.next()) {
				 String date = rs1.getString("date");
				//不能用putAll,会导致乱序
				 jsonObj.put(date, 0);
			}
			//对日期按从小到大进行排序
//			Comparator<String> c = new Comparator<String>() {
//				@Override
//				public int compare(String o1, String o2) {
//					// TODO Auto-generated method stub
//					return (o1.compareTo(o2));
//				}
//			};
//			dateList.sort(c);
//			
//			for( String d : dateList) {
//				 m1.put(d, 0);
//			}
			
			sql2 = "SELECT DISTINCT TrainInfo.Train_Date date\n" +
					"FROM StudentInfo, StudentGrade, TrainInfo\n" + 
					"WHERE StudentInfo.Student_ID = StudentGrade.Student_ID AND\n" + 
					"StudentGrade.Train_ID = TrainInfo.Train_ID AND \n" + 
					"TrainInfo.Train_State = 2 AND StudentInfo.Student_ID = ?";
			
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, Student_ID);
			rs2 = ps2.executeQuery();
			
			while(rs2.next()) {
				 String date = rs2.getString("date");
				 //不能用putAll,会导致乱序
				 jsonObj.put(date, 1);
			}

			jsonStr = JSON.toJSONString(jsonObj);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs1, ps1, conn);
			Dbconn.closeall(rs2, ps2, conn);
		}
		
		return jsonStr;
	}
	
}
