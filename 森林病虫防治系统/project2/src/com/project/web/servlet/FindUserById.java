package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.web.bean.UserBean;
import com.project.web.service.IUserService;
import com.project.web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class FindUserById
 */
@WebServlet("/FindUser")
public class FindUserById extends HttpServlet {
	private static final long serialVersionUID = 1L;
    IUserService ius = new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserById() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		UserBean userBean = ius.findById(id);
		
		request.getSession().setAttribute("userBean", userBean);
		
		response.sendRedirect("/project2/jsp/systemMessage/user/updateUser.jsp");
			
 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
