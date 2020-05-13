package com.TrainingSystem.service;

import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TrainingSystem.entity.Healthinfo;
import com.TrainingSystem.entity.StudentGrade;
import com.TrainingSystem.util.Dbconn;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class UploadDownload {
	public static ArrayList<StudentGrade> selectscoresByID(String groupID)
	{
		ArrayList<StudentGrade> slist = new ArrayList<StudentGrade>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT ss.*, Group_Name SGroup, sa.SAge Age \n" + 
					"FROM StudentScore ss, GroupInfo gi, StudentAgeCal sa\n" + 
					"where ss.GroupID = ? AND \n" + 
					"ss.GroupID = gi.Group_ID AND sa.SID = ss.SID";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(groupID));
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 StudentGrade sg = new StudentGrade(
						 rs.getString("TID"),
						 rs.getString("SID"),
						 rs.getString("SName"),
						 rs.getString("Gender"),
						 rs.getString("Age"),
						 rs.getString("SGroup"),
						 rs.getString("Date"),
						 rs.getString("Run3k"),
						 rs.getString("Snake"),
						 rs.getString("Situp"),
						 rs.getString("Pullup")			 
				 );
				
				 slist.add(sg);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
		
		return slist;
	}
	
	public static int writeExcelscore(String groupID, OutputStream os) {
		
		ArrayList<StudentGrade> slist = selectscoresByID(groupID);
		if (slist == null)
			 return 0;
		
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(os);
            if (book == null) {
            	return 0;
            }
            // 生成名为"体能成绩"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("体能训练成绩", 0);
            
            //ArrayList<StudentGrade> stuList = new ArrayList<StudentGrade>();
            if(slist!=null && !slist.isEmpty()){
                sheet.addCell(new Label(0, 0, "训练编号"));
                sheet.addCell(new Label(1, 0, "学号"));
                sheet.addCell(new Label(2, 0, "姓名"));
                sheet.addCell(new Label(3, 0, "性别"));
                sheet.addCell(new Label(4, 0, "年龄"));
                sheet.addCell(new Label(5, 0, "学员队"));
                sheet.addCell(new Label(6, 0, "时间"));
                sheet.addCell(new Label(7, 0, "3000米"));
                sheet.addCell(new Label(8, 0, "蛇形跑"));
                sheet.addCell(new Label(9, 0, "仰卧起坐"));
                sheet.addCell(new Label(10, 0, "引体向上"));
                for(int i=1; i<slist.size()+1; i++){
                    sheet.addCell(new Label(0, i, slist.get(i-1).getTID()));
                    sheet.addCell(new Label(1, i, slist.get(i-1).getSID()));
                    sheet.addCell(new Label(2, i, slist.get(i-1).getSName()));
                    sheet.addCell(new Label(3, i, slist.get(i-1).getGender()));
                    sheet.addCell(new Label(4, i, slist.get(i-1).getAge()));
                    sheet.addCell(new Label(5, i, slist.get(i-1).getSGroup()));
                    sheet.addCell(new Label(6, i, slist.get(i-1).getDate()));
                    sheet.addCell(new Label(7, i, slist.get(i-1).getRun3k()));
                    sheet.addCell(new Label(8, i, slist.get(i-1).getSnake()));
                    sheet.addCell(new Label(9, i, slist.get(i-1).getSitup()));
                    sheet.addCell(new Label(10, i, slist.get(i-1).getPullup()));
                }
            }
            // 写入数据并关闭文件
            book.write();
            return 1;
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return 0;
    }
	
	public static ArrayList<Healthinfo> selectscoresByIDhealth(String groupID)
	{
		ArrayList<Healthinfo> list = new ArrayList<Healthinfo>();
		//声明结果集
		ResultSet rs = null;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		
		String sql = "";
		
		try {
			sql = "SELECT hh.Student_ID SID, hh.Health_Date Date, hh.Health_Weight Weight, hh.Health_Height Height,\n" + 
					"		FORMAT((10000 * hh.Health_Weight / (hh.Health_Height * hh.Health_Height)), 2) BMI, \n" + 
					"		sa.SAge, sa.SGender, si.Student_Name SName\n" + 
					"FROM HealthInfo hh, StudentInfo si, StudentAgeCal sa\n" + 
					"WHERE hh.Student_ID = si.Student_ID AND \n" + 
					"		hh.Student_ID = sa.SID AND\n" + 
					"		si.Group_ID = ?  AND si.Is_Del = 0 \n" + 
					"ORDER BY si.Student_ID";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(groupID));
			
			rs = ps.executeQuery();
			 while(rs.next()) {
				 Healthinfo h = new Healthinfo (
						 	rs.getString("SID"),
						 	rs.getString("SName"),
						 	rs.getString("Date"),
						 	rs.getString("SGender"),
						 	rs.getString("SAge"),
						 	rs.getString("Weight"),
						 	rs.getString("Height"),
						 	rs.getString("BMI")
						 );
				 
				 list.add(h);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbconn.closeall(rs, ps, conn);
		}
		
		return list;
	}

	public static int writeExcelhealth(String groupID, OutputStream os) {
		
		ArrayList<Healthinfo> slist = selectscoresByIDhealth(groupID);
		if (slist == null)
			 return 0;
		
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(os);
            if (book == null)
            	return 0;
            // 生成名为"体能成绩"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("体型数据", 0);
            
            //ArrayList<StudentGrade> stuList = new ArrayList<StudentGrade>();
            if(slist!=null && !slist.isEmpty()){
                sheet.addCell(new Label(0, 0, "学号"));
                sheet.addCell(new Label(1, 0, "姓名"));
                sheet.addCell(new Label(2, 0, "性别"));
                sheet.addCell(new Label(3, 0, "年龄"));
                sheet.addCell(new Label(4, 0, "时间"));
                sheet.addCell(new Label(5, 0, "身高"));
                sheet.addCell(new Label(6, 0, "体重"));
                sheet.addCell(new Label(7, 0, "BMI"));
                for(int i=1; i<slist.size()+1; i++){
                    sheet.addCell(new Label(0, i, slist.get(i-1).getSID()));
                    sheet.addCell(new Label(1, i, slist.get(i-1).getSName()));
                    sheet.addCell(new Label(2, i, slist.get(i-1).getSGender()));
                    sheet.addCell(new Label(3, i, slist.get(i-1).getSAge()));
                    sheet.addCell(new Label(4, i, slist.get(i-1).getDate()));
                    sheet.addCell(new Label(5, i, slist.get(i-1).getHeight()));
                    sheet.addCell(new Label(6, i, slist.get(i-1).getWeight()));
                    sheet.addCell(new Label(7, i, slist.get(i-1).getBMI()));
                }
            }
            // 写入数据并关闭文件
            book.write();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return 0;
    }

	public static int replaceGrade(String tid, String sid, String run3k, 
			String snake, String situp, String pullup) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "REPLACE StudentGrade VALUES(?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, tid);
			ps.setString(2, sid);
			ps.setString(3, run3k);
			ps.setString(4, snake);
			ps.setString(5, situp);
			ps.setString(6, pullup);
			
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
	
	public static int readExcelscore(File file) {
        Workbook book = null;
        int count = 0;
        try {
            // 打开文件
            book = Workbook.getWorkbook(file);
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int rows=sheet.getRows();
            // 遍历每行每列的单元格
            if (sheet.getCell(0, 0).getContents().equals("训练编号") && 
            	sheet.getCell(1, 0).getContents().equals("学号") && 
            	sheet.getCell(2, 0).getContents().equals("3000米") && 
            	sheet.getCell(3, 0).getContents().equals("蛇形跑") && 
            	sheet.getCell(4, 0).getContents().equals("仰卧起坐") && 
            	sheet.getCell(5, 0).getContents().equals("引体向上")) 
            {
	            for(int i=1;i<rows;i++){
	                String tid = sheet.getCell(0, i).getContents();
	                String sid = sheet.getCell(1, i).getContents();
	                String run3k = sheet.getCell(2, i).getContents();
	                String snake = sheet.getCell(3, i).getContents();
	                String situp = sheet.getCell(4, i).getContents();
	                String pullup = sheet.getCell(5, i).getContents();
	                
	                if (replaceGrade(tid, sid, run3k, snake, situp, pullup) == 1) {
	                	count++;
	                }
	            }
            }
            //System.out.println(count);
        	return count;
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
    	
    	return count;
    }

	public static int replaceHealth(String sid, String date, String height, 
			String weight) throws SQLException
	{
		//声明结果集
		int rs = 0;
		//获取连接对象
		Connection conn = Dbconn.getconn();
		
		PreparedStatement ps = null;
		String sql = "";
		
		try {
			sql = "REPLACE HealthInfo VALUES(?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			ps.setString(2, date);
			ps.setString(3, height);
			ps.setString(4, weight);
			
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
	
	public static int readExcelhealth(File file) {
        Workbook book = null;
        int count = 0;
        try {
            // 打开文件
            book = Workbook.getWorkbook(file);
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int rows=sheet.getRows();
            // 遍历每行每列的单元格
            if (sheet.getCell(0, 0).getContents().equals("学号") && 
                	sheet.getCell(1, 0).getContents().equals("时间") && 
                	sheet.getCell(2, 0).getContents().equals("身高") && 
                	sheet.getCell(3, 0).getContents().equals("体重"))
                {
	            for(int i=1;i<rows;i++){
	                String sid = sheet.getCell(0, i).getContents();
	                String date = sheet.getCell(1, i).getContents();
	                String height = sheet.getCell(2, i).getContents();
	                String weight = sheet.getCell(3, i).getContents();
	                
	                if (replaceHealth(sid, date, height, weight) == 1) {
	                	count++;
	                }
	            }
	        }
        	//System.out.println(count);
        	return count;
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
    	
    	return count;
    }
}
