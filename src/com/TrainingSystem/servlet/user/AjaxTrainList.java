package com.TrainingSystem.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TrainingSystem.service.leader.TrainManage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class AjaxTrainList
 */
@WebServlet("/AjaxTrainList")
public class AjaxTrainList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTrainList() {
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
		String userClass = (String) session.getAttribute("userClass");
		PrintWriter out = response.getWriter();
		
		if (userClass == "" || userClass == null) {
			out.write("false");
			return;
		}
		
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		JSONObject jsonObj = new JSONObject(true);
		ArrayList<Map<String, String>>	lmap = new ArrayList<Map<String, String>>();
		int num = 0;
		
		if (userClass.equals("s"))
		{
			String groupID = (String) session.getAttribute("groupID");
			lmap = TrainManage.selectTrainByIDOther(groupID, page, limit);
			num = TrainManage.totalnumOther(groupID);

		}
		else if (userClass.equals("a")) {
			lmap = TrainManage.selectTrainAll(page, limit);
			num = TrainManage.totalnumAll();
		}
		else {
			lmap = null;
		}
		
		if(lmap == null || lmap.isEmpty())
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
			jsonObj.put("count", num);
			jsonObj.put("data", lmap);
		}
		
		String jsonStr = JSON.toJSONString(jsonObj);
		
		response.setContentType("application/json;charset=utf-8");
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
