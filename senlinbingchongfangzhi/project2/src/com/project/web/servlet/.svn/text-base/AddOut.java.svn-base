package com.project.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.ClassBean;
import com.project.web.bean.MachineBean;
import com.project.web.bean.OutMachineBean;
import com.project.web.service.IClassService;
import com.project.web.service.impl.ClassServiceImpl;

/**
 * Servlet implementation class AddOut
 */
@WebServlet("/addOut")
public class AddOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IClassService cs = new ClassServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//设置编码集
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				
				List<ClassBean>list = cs.FindClassAll();
				
//				List<String>lists = new ArrayList<String>();
//				lists.add("一般");
				
				ObjectMapper om = new ObjectMapper();
				om.writeValue(response.getWriter(), list);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
