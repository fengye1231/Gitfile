package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.web.bean.PestslBean;
import com.project.web.service.IPestslService;
import com.project.web.service.impl.PestslServiceImpl;

/**
 * Servlet implementation class PestslCheck
 */
@WebServlet("/pestslcheck")
public class PestslCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPestslService ips = new PestslServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PestslCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pestslId = Integer.parseInt(request.getParameter("id").substring(2));
		PestslBean bean = ips.findById(pestslId);
		request.getSession().setAttribute("pestsl", bean);
		response.sendRedirect("/project2/jsp/dataManagement/pestsl/findPestsl.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
