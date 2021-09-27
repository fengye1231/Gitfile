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
 * Servlet implementation class UserAddServlet
 */
@WebServlet("/UserAdd")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       IUserService ius = new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddServlet() {
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
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String level = request.getParameter("level");
		
		UserBean user = new UserBean(userName, pwd, name, level);
		
		ius.addUser(user);
		
		response.sendRedirect("/project2/jsp/systemMessage/user/user-index.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
