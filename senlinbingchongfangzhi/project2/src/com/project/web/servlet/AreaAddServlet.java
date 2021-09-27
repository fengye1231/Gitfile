package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.web.bean.AreaBean;
import com.project.web.bean.CutPageBean;
import com.project.web.service.IAreaService;
import com.project.web.service.impl.AreaServiceImpl;
import com.project.web.util.StringCheck;
import com.project.web.util.pageNumCount;

/**
 * Servlet implementation class AreaAddServlet
 */
@WebServlet("/add")
public class AreaAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAreaService ias = new AreaServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AreaAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String name = StringCheck.stringCheck(request.getParameter("name"));
		String species = StringCheck.stringCheck(request.getParameter("species"));
		String great = StringCheck.stringCheck(request.getParameter("great"));
		String choose = StringCheck.stringCheck(request.getParameter("sel"));
		
		System.out.println(name);
		System.out.println(species);
		System.out.println(great);
		System.out.println(choose);
		

		ias.AddArea(new AreaBean( name, species, great, choose));
		
		
		
		response.sendRedirect("/project2/jsp/disasterPrevention/area/area-index.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
