package com.project.web.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.LogBean;
import com.project.web.service.ILogServlet;
import com.project.web.service.impl.LogServiceImpl;
import com.project.web.util.DateChange;

/**
 * Servlet implementation class logServlet
 */
@WebServlet("/log")
public class logServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ILogServlet ils = new LogServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int num = Integer.parseInt(request.getParameter("pageNo"));
		Date startDate = DateChange.getDate(request.getParameter("startDate"));
		Date endDate = DateChange.getDate(request.getParameter("endDate"));
		
		CutPageBean<LogBean> cut = ils.findByCondition(num, startDate, endDate);
		
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), cut);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
