package com.TrainingSystem.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TrainingSystem.entity.Studentinfo;
import com.TrainingSystem.service.Studentdao;

/**
 * Servlet implementation class Userinfo
 */
@WebServlet("/Updateinfo")
public class Updateinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updateinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String Student_ID = request.getParameter("userID");
		String Student_Name = request.getParameter("userName");
		String Student_Password = request.getParameter("userPassword");
		String Student_Gender = request.getParameter("userGender");
		String Student_Birthday = request.getParameter("userBirthday");
		String Group_ID = request.getParameter("userGroup");
		
		//创建用户实体
		Studentinfo s = new Studentinfo(Student_ID, Student_Name, Student_Password, Student_Gender, Student_Birthday, Group_ID);
		
		//更新数据库的用户表中
		int count = Studentdao.update(s);
		
		//成功或失败
		if (count > 0) {
			request.getRequestDispatcher("student/pages/user-info.jsp").forward(request, response);
		}
		else {
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('更新失败')");
			out.write("location.href='student/pages/user-info.jsp'");
			out.write("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
