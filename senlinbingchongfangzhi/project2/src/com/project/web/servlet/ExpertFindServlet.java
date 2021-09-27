package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.web.bean.ExpertBean;
import com.project.web.service.IExpertService;
import com.project.web.service.impl.ExpertServiceImpl;

/**
 * Servlet implementation class ExpertFindServlet
 */
@WebServlet("/findExpert")
public class ExpertFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IExpertService ies = new ExpertServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpertFindServlet() {
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
		ExpertBean bean = ies.findExpertById(id);
		request.getSession().setAttribute("bean", bean);
		
		System.out.println(bean);
		request.getRequestDispatcher("/jsp/expertConsultation/expert/show.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
