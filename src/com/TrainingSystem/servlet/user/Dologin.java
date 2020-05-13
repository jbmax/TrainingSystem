package com.TrainingSystem.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TrainingSystem.entity.Admininfo;
import com.TrainingSystem.entity.Groupinfo;
import com.TrainingSystem.entity.Leaderinfo;
import com.TrainingSystem.entity.Studentinfo;
import com.TrainingSystem.service.Admindao;
import com.TrainingSystem.service.Groupdao;
import com.TrainingSystem.service.Leaderdao;
import com.TrainingSystem.service.Studentdao;

/**
 * Servlet implementation class Dologin
 */
@WebServlet("/Dologin")
public class Dologin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dologin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		// 访问数据库获取用户信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userClass = request.getParameter("userClass");
		
		PrintWriter out = response.getWriter();
		
		//学员登录
		if (userClass != null && userClass.equals("s")) {
			try {		
				Studentinfo si = Studentdao.verifyPass(username, password);
				if (si != null) {
					HttpSession session = request.getSession();
					session.setAttribute("userID", username);
					session.setAttribute("userName", si.getStudent_Name());
					session.setAttribute("userClass", "s");
					// 设置请求对象
					session.setAttribute("userPassword", si.getStudent_Password());
					session.setAttribute("userGender", si.getStudent_Gender());
					session.setAttribute("userBirthday", si.getStudent_Birthday());
					session.setAttribute("groupID", si.getGroup_ID());
					Groupinfo g = Groupdao.selectbyid(si.getGroup_ID());
					session.setAttribute("groupName", g.getGroup_Name());
					out.write("true");
				}
				else {
					out.write("false");
				}
			} catch (Exception e) {
				out.write("false");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}  
		//队领导登录
		if (userClass != null && userClass.equals("l")) {
			try {
				Leaderinfo li = Leaderdao.verifyPass(username, password);
//				 getServletContext().log("日志日志顶呱呱3");
//				 String p = Leaderdao.selectbyID(username).getLeader_Password();
//				 getServletContext().log(p);
//				 if (!p.equals(password))
//					 getServletContext().log("not equal");
				if (li != null) {
					HttpSession session = request.getSession();
					session.setAttribute("userID", username);
					session.setAttribute("userName", li.getLeader_Name());
					session.setAttribute("userPassword", li.getLeader_Password());
					session.setAttribute("userClass", "l");
					session.setAttribute("groupID", li.getGroup_ID());
					Groupinfo g = Groupdao.selectbyid(li.getGroup_ID());
					session.setAttribute("groupName", g.getGroup_Name());
					session.setAttribute("groupDate", g.getGroup_Date());
					
					out.write("true");
				}
				else {
					out.write("false");
				}
			}catch (Exception e) {
				out.write("false");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		} 
		//管理员登录 
		if (userClass != null && userClass.equals("a")) {
			try {
				Admininfo a = Admindao.verifyPass(username, password);
//				 getServletContext().log("日志日志顶呱呱4");
//				 String p = Admindao.selectbyID(username).getAdmin_Password();
//				 getServletContext().log(p);
//				 if (!p.equals(password))
//					 getServletContext().log("not equal");
				if (a != null) {
					HttpSession session = request.getSession();
					session.setAttribute("userID", username);
					session.setAttribute("userName", a.getAdmin_Name());
					session.setAttribute("userPassword", a.getAdmin_Password());
					session.setAttribute("userClass", "a");
					
					out.write("true");
				}
				else {
					out.write("false");
				}
			}catch (Exception e) {
				out.write("false");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}

}
