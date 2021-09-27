package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.ClassBean;
import com.project.web.bean.CutPageBean;
import com.project.web.service.IClassService;
import com.project.web.service.impl.ClassServiceImpl;

/**
 * Servlet implementation class ClassShowServlet
 */
@WebServlet("/ClassShow")
public class ClassShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassShowServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 IClassService service=new ClassServiceImpl();
		 int page=Integer.parseInt(request.getParameter("page"));
		 String name=request.getParameter("name");
		 String area=request.getParameter("area");
		 CutPageBean<ClassBean>bean=service.cutpage(page, name, area);	
		 ObjectMapper om=new ObjectMapper();
		 om.writeValue(response.getWriter(), bean);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
