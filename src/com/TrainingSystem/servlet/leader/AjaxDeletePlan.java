package com.TrainingSystem.servlet.leader;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TrainingSystem.service.leader.PlanManage;

/**
 * Servlet implementation class AjaxDeletePlan
 */
@WebServlet("/AjaxDeletePlan")
public class AjaxDeletePlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxDeletePlan() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		String pid = request.getParameter("PID");
		
		PrintWriter out = response.getWriter();

		String uid = (String) session.getAttribute("userID");
		if (uid == "" || uid == null) {
			out.write("false");
			return;
		}
		if (!((String) session.getAttribute("userClass")).equals("a")) {
			out.write("false");
			return;
		}
		
		try {
			if (PlanManage.deletePlan(pid) == 1) {
				out.write("true");
			}
			else {
				out.write("false");
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

}
