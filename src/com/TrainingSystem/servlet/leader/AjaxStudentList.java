package com.TrainingSystem.servlet.leader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TrainingSystem.service.leader.StudentManage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class AjaxGroupList
 */
@WebServlet("/AjaxStudentList")
public class AjaxStudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxStudentList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		ArrayList<Map<String, String>>	lmap= StudentManage.selectStudent(page, limit);
		JSONObject jsonObj = new JSONObject(true);
	
		if(lmap.isEmpty())
		{
			jsonObj.put("code", 0);
			jsonObj.put("msg", "");
			jsonObj.put("count", 0);
			jsonObj.put("data", "");
		}
		else
		{
			jsonObj.put("code", 0);
			jsonObj.put("msg", "");
			jsonObj.put("count", StudentManage.totalnum());
			jsonObj.put("data", lmap);
		}
		
		String jsonStr = JSON.toJSONString(jsonObj);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
	}

}
