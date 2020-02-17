package com.TrainingSystem.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TrainingSystem.entity.Groupinfo;
import com.TrainingSystem.entity.Studentinfo;
import com.TrainingSystem.service.Groupdao;
import com.TrainingSystem.service.Studentdao;

/**
 * Servlet implementation class Getinfo
 */
@WebServlet("/Getinfo")
public class Getinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Getinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 访问数据库获取用户信息
		String Student_ID = request.getParameter("userID");
		
		Studentinfo s = Studentdao.selectbyid(Student_ID);
		// 设置请求对象
		request.setAttribute("userinfo", s);
//		request.setAttribute("userID", s.getStudent_ID());
//		request.setAttribute("userName", s.getStudent_Name());
//		request.setAttribute("userPassword", s.getStudent_Password());
//		request.setAttribute("userGender", s.getStudent_Gender());
//		request.setAttribute("userClass", s.getStudent_Class());
//		request.setAttribute("userBirthday", s.getStudent_Birthday());
		
		Groupinfo g = Groupdao.selectbyid(s.getGroup_ID());
		request.setAttribute("groupinfo", g);
		
		request.getRequestDispatcher("student/pages/user-info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
