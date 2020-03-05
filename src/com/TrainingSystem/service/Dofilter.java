package com.TrainingSystem.service;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class Dofilter
 */
@WebFilter(filterName = "/Dofilter", urlPatterns = {"/student/pages/*", "/student",  "/student/", 
		"/leader/pages/*", "/leader", "/leader/", "/admin/pages/*", "/admin", "/admin/"})

public class Dofilter implements Filter {

//	private String regex = "{0,}login{0,}";
//	private Pattern excepUrlPattern = Pattern.compile(regex);
//	
//	private String regexStu = "{0,}student{0,}";
//	private Pattern excepUrlPatternStu = Pattern.compile(regexStu);
//	
//	private String regexLea = "{0,}leader{0,}";
//	private Pattern excepUrlPatternLea = Pattern.compile(regexLea);
	
    /**
     * Default constructor. 
     */
    public Dofilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String servletPath = req.getServletPath();
        String userClass = (String)req.getSession().getAttribute("userClass");
        Object userID = req.getSession().getAttribute("userID");
        
        String redirectStu = "/TrainingSystem/student/login.html";
        String redirectLea = "/TrainingSystem/leader/login.html";
        String redirectAdm = "/TrainingSystem/admin/login.html";
        
        
        //debug
//        if (servletPath.contains("leader")) {
//            chain.doFilter(req, res);
//            return;
//        }
        
        if (servletPath.contains("login")) {
            chain.doFilter(req, res);
            return;
        }

        //System.out.println(servletPath);
        if (servletPath.contains("student/")) {
	        if (userClass == null || !userClass.equals("s") || userID == null) {
	            res.sendRedirect(redirectStu);
	            return;
	        }
        } 
        else if (servletPath.contains("leader/")) {
	        if (userClass == null || !userClass.equals("l") || userID == null) {
	            res.sendRedirect(redirectLea);
	            return;
	        }
        }
        else if (servletPath.contains("admin/")) {
	        if (userClass == null || !userClass.equals("a") || userID == null) {
	            res.sendRedirect(redirectAdm);
	            return;
	        }
        }
        else {
        	res.sendRedirect(redirectStu);
        	return;
        }

		// pass the request along the filter chain
		chain.doFilter(request, response);
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
