package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.UserBean;
import com.project.web.service.IUserService;
import com.project.web.service.impl.UserServiceImpl;
import com.project.web.util.StringCheck;
import com.project.web.util.pageNumCount;

/**
 * Servlet implementation class UserShowAll
 */
@WebServlet("/UserShowAll")
public class UserShowAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       IUserService ius = new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserShowAllServlet() {
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
		String level = StringCheck.stringCheck(request.getParameter("level"));
		
		CutPageBean<UserBean> cut = ius.findByCondition(num, level);
		cut.setTotalPageNum(pageNumCount.countPage(ius.findCountPageNo(level)));
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
