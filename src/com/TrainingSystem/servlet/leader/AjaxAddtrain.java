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

import com.TrainingSystem.service.leader.TrainManage;

/**
 * Servlet implementation class AjaxAddtrain
 */
@WebServlet("/AjaxAddtrain")
public class AjaxAddtrain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxAddtrain() {
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
		String lid = (String) session.getAttribute("userID");
		String pid = request.getParameter("ID");
		String date = request.getParameter("edate");
		String ground = request.getParameter("ground");
		String oper = request.getParameter("oper");
		
		PrintWriter out = response.getWriter();

		try {
			if (TrainManage.insertTrain(pid, oper, date, ground, lid) == 1) {
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
