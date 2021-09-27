package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.web.bean.AreaBean;
import com.project.web.bean.ClassBean;
import com.project.web.service.IClassService;
import com.project.web.service.impl.ClassServiceImpl;

/**
 * Servlet implementation class UpdateClassFind
 */
@WebServlet("/UpdateClassFind")
public class UpdateClassFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private    IClassService ics=new ClassServiceImpl();;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClassFind() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		   int id=Integer.parseInt(request.getParameter("id"));
		   ClassBean bean=ics.FindClassBean(id);
//		   HttpSession session=request.getSession();
		   AreaBean bean2=bean.getAreaBean();
		   HttpSession session =request.getSession();
		   session.setAttribute("classBean", bean);
		   session.setAttribute("areaBean", bean2);
		   response.sendRedirect("/project2/jsp/disasterPrevention/class/toUpdateClass.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
