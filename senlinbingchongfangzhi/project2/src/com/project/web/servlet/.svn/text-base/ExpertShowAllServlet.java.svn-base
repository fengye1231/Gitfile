package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.ExpertBean;
import com.project.web.service.IExpertService;
import com.project.web.service.impl.ExpertServiceImpl;
import com.project.web.util.StringCheck;

/**
 * Servlet implementation class ExpertServlet
 */
@WebServlet("/expertShowAll")
public class ExpertShowAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IExpertService ies = new ExpertServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpertShowAllServlet() {
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
		
		int num = Integer.parseInt(request.getParameter("pageNo"));
		String name = StringCheck.stringCheck(request.getParameter("name"));
		String special = StringCheck.stringCheck(request.getParameter("special"));
		String workPlace = StringCheck.stringCheck(request.getParameter("workPlace"));
		
		CutPageBean<ExpertBean> cut = ies.showAllExpert(num, name, special,workPlace);
		System.out.println("num=" + num);
		System.out.println("name=" + name);
		System.out.println("special=" + special);
		System.out.println("workPlace=" + workPlace);
		System.out.println(cut);
		
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), cut);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
