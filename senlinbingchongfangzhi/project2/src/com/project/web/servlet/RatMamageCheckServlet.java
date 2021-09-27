package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.web.bean.PestslBean;
import com.project.web.bean.RatDamageBean;
import com.project.web.service.IRatDamageService;
import com.project.web.service.impl.RatDamageServiceImpl;

/**
 * Servlet implementation class RatMamageCheckServlet
 */
@WebServlet("/ratmamagecheck")
public class RatMamageCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IRatDamageService irs = new RatDamageServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatMamageCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pestslId = Integer.parseInt(request.getParameter("id").substring(2));
		RatDamageBean bean = irs.findById(pestslId);
		request.getSession().setAttribute("RatDamage", bean);
		response.sendRedirect("/project2/jsp/dataManagement/ratDamage/findRatDamage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
