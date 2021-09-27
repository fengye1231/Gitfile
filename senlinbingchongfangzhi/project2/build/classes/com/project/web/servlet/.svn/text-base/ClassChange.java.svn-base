package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.web.bean.ClassBean;
import com.project.web.service.IClassService;
import com.project.web.service.impl.ClassServiceImpl;

/**
 * Servlet implementation class ClassChange
 */
@WebServlet("/ClassChange")
public class ClassChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 IClassService ics=new ClassServiceImpl();
		 HttpSession session=request.getSession();
		 String duty=request.getParameter("duty");
		 String phoneNum=request.getParameter("phoneNum");
		 ClassBean bean=(ClassBean) session.getAttribute("classBean");
		  ics.UpdateClassBean(bean.getId(), duty, phoneNum);
		  response.sendRedirect("/project2/jsp/disasterPrevention/class/class.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
