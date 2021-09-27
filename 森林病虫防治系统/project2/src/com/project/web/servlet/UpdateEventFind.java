package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.web.bean.EventBean;
import com.project.web.dao.impl.AreaDaoImpl;
import com.project.web.service.IClassService;
import com.project.web.service.IEventService;
import com.project.web.service.impl.ClassServiceImpl;
import com.project.web.service.impl.EventServiceImpl;

/**
 * Servlet implementation class UpdateEventFind
 */
@WebServlet("/UpdateEventFind")
public class UpdateEventFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEventFind() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IEventService service=new EventServiceImpl();
		IClassService servicec=new ClassServiceImpl();
		 AreaDaoImpl area=new AreaDaoImpl();
		 int id=Integer.parseInt(request.getParameter("id"));
		 EventBean bean=service.chooseEvent(id);
         String name=bean.getArea();
         System.out.println(name);
		 int areaId=area.findName(name);
		 System.out.println(areaId+"测试");
		 HttpSession session =request.getSession();
		 session.setAttribute("eventBean", bean);
		 String classname=servicec.getClassNameByArea(areaId);
		 session.setAttribute("classBean",classname);
		 response.sendRedirect("/project2/jsp/disasterPrevention/event/UpdateEvent.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
