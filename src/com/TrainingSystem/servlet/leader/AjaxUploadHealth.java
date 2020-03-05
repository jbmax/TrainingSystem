package com.TrainingSystem.servlet.leader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.TrainingSystem.service.UploadDownload;

/**
 * Servlet implementation class AjaxUploadScore
 */
@WebServlet("/AjaxUploadHealth")
public class AjaxUploadHealth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUploadHealth() {
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
		//String groupID = (String) session.getAttribute("groupID");
		String userID = (String) session.getAttribute("userID");
		
		boolean isMultipart= ServletFileUpload.isMultipartContent(request);  //enctype属性是否是multipart/form-data
		try {
            if (isMultipart){
            	String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
            	//System.out.println(savePath);
                FileItemFactory factory=new DiskFileItemFactory();  //工厂实例
                ServletFileUpload upload=new ServletFileUpload(factory);  //ServletFileUpload实例依赖于FileItemFactory工厂
                ArrayList<FileItem> itemList=(ArrayList<FileItem>) upload.parseRequest(request);  //解析表单字段，封装成一个FileItem实例的集合
                Iterator<FileItem> iterator=itemList.iterator();  //迭代器
                while (iterator.hasNext()){
                    FileItem fileItem=iterator.next();  //依次解析每一个FileItem实例，即表单字段
                    //文件表单字段
                    String fileUpName=fileItem.getName();  //用户上传的文件名
                    File file=new File(savePath +"/"+ userID + "-" + fileUpName);  //要保存到的文件
                    if (!file.exists()){
                        file.createNewFile();  //一开始肯定是没有的，所以先创建出来
                    }
                    fileItem.write(file);  //写入，保存到目标文件
                    if (UploadDownload.readExcelhealth(file) > 0) {
                    	response.getWriter().write("true");
                    }
                    else {
                    	response.getWriter().write("false");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
