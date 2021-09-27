package com.project.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.project.web.bean.EventBean;
import com.project.web.service.IConsultationService;
import com.project.web.service.impl.ConsultationServiceImpl;


/**
 * Servlet implementation class EventNeedExpertShowServlet
 */
@WebServlet("/eventNeedExpertShow")
public class EventNeedExpertShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IConsultationService csi = new ConsultationServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventNeedExpertShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<EventBean> list = csi.findEventNeed();
		
		request.getSession().setAttribute("list", list);
		request.getRequestDispatcher("/jsp/expertConsultation/consultation/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
