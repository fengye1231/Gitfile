package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.web.service.IExpertService;
import com.project.web.service.impl.ExpertServiceImpl;
import com.project.web.util.StringCheck;

/**
 * Servlet implementation class ExpertUpdateServlet2
 */
@WebServlet("/updateExpert2")
public class ExpertUpdateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IExpertService ies = new ExpertServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpertUpdateServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String position = StringCheck.stringCheck(request.getParameter("position"));
		String phoneNum = StringCheck.stringCheck(request.getParameter("phoneNum"));
		String workPlace = StringCheck.stringCheck(request.getParameter("workPlace"));
		String email = StringCheck.stringCheck(request.getParameter("email"));
		System.out.println(workPlace);
		ies.updateExpert(id, position, phoneNum, workPlace, email);
		
		response.sendRedirect("/project2/jsp/expertConsultation/expert/main.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
