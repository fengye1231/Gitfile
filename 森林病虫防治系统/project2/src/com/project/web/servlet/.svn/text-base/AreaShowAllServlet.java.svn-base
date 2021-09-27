package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.AreaBean;
import com.project.web.bean.CutPageBean;
import com.project.web.service.IAreaService;
import com.project.web.service.impl.AreaServiceImpl;
import com.project.web.util.DateChange;
import com.project.web.util.StringCheck;
import com.project.web.util.pageNumCount;

/**
 * Servlet implementation class AreaShowAllServlet
 */
@WebServlet("/areaindex")
public class AreaShowAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAreaService ias = new AreaServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AreaShowAllServlet() {
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
		
		
		
		int num = Integer.parseInt(request.getParameter("pageNo"));
		String name = StringCheck.stringCheck(request.getParameter("name1"));
		String species = StringCheck.stringCheck(request.getParameter("species"));
		String className = StringCheck.stringCheck(request.getParameter("className"));
		CutPageBean<AreaBean> cut =ias.showAllArea(num, pageNumCount.EACH_NUM, name, species, className);
		
		//向ajax传值
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(),cut);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
