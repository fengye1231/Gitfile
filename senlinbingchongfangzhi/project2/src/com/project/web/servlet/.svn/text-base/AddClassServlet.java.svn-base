package com.project.web.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.AreaBean;
import com.project.web.bean.ClassBean;
import com.project.web.dao.impl.AreaDaoImpl;
import com.project.web.service.IClassService;
import com.project.web.service.impl.ClassServiceImpl;
import com.project.web.util.DateChange;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet(name = "ClassAddServlet", urlPatterns = { "/ClassAdd" })
public class AddClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IClassService ics=new ClassServiceImpl();
	    
		 AreaDaoImpl area=new AreaDaoImpl();
		ClassBean bean=new ClassBean();
		String name=request.getParameter("name");
		String principal=request.getParameter("principal");
		String phoneNum=request.getParameter("phoneNum");
		int num=Integer.parseInt(request.getParameter("num"));
	
		String areaName=request.getParameter("areaName");
		System.out.println(areaName);
        int id=area.findName(areaName);
        AreaBean areaBean=area.findId(id);
        bean.setName(name);
        bean.setPhoneNum(phoneNum);
        bean.setNum(num);
        bean.setDate(new Date(System.currentTimeMillis()));
        bean.setPrincipal(principal);
        bean.setAreaBean(areaBean);
        ics.addClass(bean);
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
