package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.PestslBean;
import com.project.web.bean.RatDamageBean;
import com.project.web.dao.impl.RatDamageDaoImpl;
import com.project.web.service.IRatDamageService;
import com.project.web.service.impl.RatDamageServiceImpl;
import com.project.web.util.StringCheck;

/**
 * Servlet implementation class RatDamageServlet
 */
@WebServlet("/ratDamageshowAll")
public class RatDamageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IRatDamageService irs = new RatDamageServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatDamageServlet() {
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
		String ratDamageName =StringCheck.stringCheck(request.getParameter("ratDamageName"));
		CutPageBean<RatDamageBean> cut = irs.showAllRatDamage(num, ratDamageName);
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
