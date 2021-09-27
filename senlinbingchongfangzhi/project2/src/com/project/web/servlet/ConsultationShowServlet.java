package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.project.web.bean.EventBean;
import com.project.web.service.IConsultationService;
import com.project.web.service.impl.ConsultationServiceImpl;

/**
 * Servlet implementation class ConsultationShowServlet
 */
@WebServlet("/consultationShow")
public class ConsultationShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IConsultationService ics = new ConsultationServiceImpl();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultationShowServlet() {
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
		EventBean bean = ics.findEventById(id);
		HttpSession session = request.getSession();
		session.setAttribute("bean", bean);

		request.getRequestDispatcher("/jsp/expertConsultation/consultation/buss.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
