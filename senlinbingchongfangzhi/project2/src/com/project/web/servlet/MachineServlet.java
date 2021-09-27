package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.MachineBean;
import com.project.web.service.IMachineService;
import com.project.web.service.impl.MachineServiceImpl;
import com.project.web.util.StringCheck;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/machine")
public class MachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private 	IMachineService ms = new MachineServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MachineServlet() {
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
		//获取jsp页面过来的值
		int num = Integer.parseInt(request.getParameter("pageNo"));
		String name = request.getParameter("nameMachine");
		String defeat = request.getParameter("defeat");
		String kind = request.getParameter("kind");
		

		
		CutPageBean<MachineBean> cut = ms.findProject(num,StringCheck.stringCheck(name), StringCheck.stringCheck(defeat), StringCheck.stringCheck(kind));
		
//		System.out.println(cut);
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
