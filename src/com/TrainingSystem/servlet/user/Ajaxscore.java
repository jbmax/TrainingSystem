package com.TrainingSystem.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TrainingSystem.entity.Studentscore;
import com.TrainingSystem.service.student.Studentscoredao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class Ajaxscore
 */
@WebServlet("/Ajaxscore")
public class Ajaxscore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajaxscore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String Student_ID = request.getParameter("Student_ID");
		ArrayList<Studentscore>	list= Studentscoredao.selectscoresByID(Student_ID);
		
		JSONObject jsonObj = new JSONObject();
		
		if(list.isEmpty())
		{
			jsonObj.put("code", 1);
		}
		else
		{
			jsonObj.put("code", 0);
			jsonObj.put("msg", "");
			jsonObj.put("count", list.size());
			jsonObj.put("data", list);
		}
		
		String jsonStr = JSON.toJSONString(jsonObj);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
