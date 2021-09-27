package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.service.IClassService;
import com.project.web.service.IEventService;
import com.project.web.service.impl.ClassServiceImpl;
import com.project.web.service.impl.EventServiceImpl;

/**
 * Servlet implementation class EventUpdate
 */
@WebServlet("/EventUpdate")
public class EventUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 IEventService sercice=new EventServiceImpl();
		  int id=Integer.parseInt(request.getParameter("id"));
		  String phase=request.getParameter("phase");
		  String plan=request.getParameter("plan");
           sercice.updateEvent(id, phase, plan);
           
           ObjectMapper om=new ObjectMapper();
           om.writeValue(response.getWriter(),1);
           
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
