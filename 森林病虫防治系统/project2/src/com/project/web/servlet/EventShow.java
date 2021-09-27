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
import com.project.web.bean.EventBean;
import com.project.web.service.IEventService;
import com.project.web.service.impl.EventServiceImpl;
import com.project.web.util.DateChange;

/**
 * Servlet implementation class EventShow
 */
@WebServlet("/EventShow")
public class EventShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventShow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                          
		        IEventService ies=new EventServiceImpl();
		        int page=Integer.parseInt(request.getParameter("page"));
		        String name=request.getParameter("name");
		        String phase=request.getParameter("phase");
		        String area=request.getParameter("area");
		        Date begin=DateChange.getDate(request.getParameter("begin"));
		        Date end=DateChange.getDate(request.getParameter("end"));
		        CutPageBean<EventBean> bean=ies.Cutbean(page,name, phase, area, begin, end);
		        
		        ObjectMapper om=new ObjectMapper();
		        om.writeValue(response.getWriter(),bean);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
