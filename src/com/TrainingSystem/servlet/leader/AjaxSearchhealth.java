package com.TrainingSystem.servlet.leader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TrainingSystem.entity.Healthinfo;
import com.TrainingSystem.service.leader.Grouphealthdao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class AjaxSearchhealth
 */
@WebServlet("/AjaxSearchhealth")
public class AjaxSearchhealth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxSearchhealth() {
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
		String groupID = (String) session.getAttribute("groupID");
		String sname = request.getParameter("sname");
		
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		ArrayList<Healthinfo>	list = new ArrayList<Healthinfo>();
		
		if (sname == "") {
			list = Grouphealthdao.selectscoresByID(groupID, page, limit);
		}else {
			list = Grouphealthdao.selectscoresByName(groupID, sname, page, limit);
		}
		
		JSONObject jsonObj = new JSONObject(true);
		
		if(list.isEmpty())
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
			jsonObj.put("count", Grouphealthdao.searchnum(groupID, sname));
			jsonObj.put("data", list);
		}
		
		String jsonStr = JSON.toJSONString(jsonObj);
		
		response.setContentType("application/json;charset=utf-8");
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
