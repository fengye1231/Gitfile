package com.project.web.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.web.bean.ConsultationBean;
import com.project.web.bean.EventBean;
import com.project.web.service.IConsultationService;
import com.project.web.service.impl.ConsultationServiceImpl;

/**
 * Servlet implementation class AddConsultationServlet
 */
@WebServlet("/addConsultation")
public class AddConsultationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IConsultationService ics = new ConsultationServiceImpl();
	IConsultationService csi = new ConsultationServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddConsultationServlet() {
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
		String result = request.getParameter("result");
		String content = request.getParameter("content");
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date time = Date.valueOf(dateFormat.format(date));
		
		
		int id = Integer.parseInt(request.getParameter("eventId"));
		EventBean bean = ics.findEventById(id);
		
		System.out.println("获得值："+ result + content + time + id);
		ics.addConsultation(new ConsultationBean(content, result, bean, time));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
