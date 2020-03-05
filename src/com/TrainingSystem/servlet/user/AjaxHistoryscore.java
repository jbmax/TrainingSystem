package com.TrainingSystem.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TrainingSystem.service.student.Historyscore;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class AjaxHistoryscore
 */
@WebServlet("/AjaxHistoryscore")
public class AjaxHistoryscore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxHistoryscore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		String Student_ID = (String) session.getAttribute("userID");	
		ArrayList<Integer> self_list = Historyscore.getItemLast(Student_ID);
		ArrayList<Integer> max_list = Historyscore.getItemMax(Student_ID);
		
		JSONObject jsonObj = new JSONObject(true);

		jsonObj.put("self", self_list);
		jsonObj.put("max", max_list);
		
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
