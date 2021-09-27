package com.project.web.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.web.bean.LogBean;
import com.project.web.bean.UserBean;
import com.project.web.dao.ILogDao;
import com.project.web.dao.impl.LogDaoImpl;
import com.project.web.service.ILoginService;
import com.project.web.service.IUserService;
import com.project.web.service.impl.LoginServiceImpl;
import com.project.web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ILoginService ius = new LoginServiceImpl();
	private ILogDao ild = new LogDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		String name = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String code = request.getParameter("code");
		
		UserBean bean = new UserBean();
		bean.setUserName(name);
		bean.setPwd(pwd);
		
		UserBean user = ius.login(bean);
		
		HttpSession session = request.getSession(false);
		String codeEQ = (String) session.getAttribute("code");
		
		if(codeEQ.equalsIgnoreCase(code)&&user!=null) {
			session.setAttribute("user", user);
			String str = "用户"+bean.getUserName()+"在"
		+new SimpleDateFormat("HH:mm").format(System.currentTimeMillis())+"登陆系统";
			Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
			LogBean log = new LogBean(str, date);
			ild.addLog(log);
			response.sendRedirect("/project2/jsp/homepage/homepage.jsp");
		}else {
			response.sendRedirect("/project2/jsp/fail.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
