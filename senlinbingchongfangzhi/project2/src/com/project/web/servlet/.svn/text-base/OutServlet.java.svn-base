package com.project.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.OutBean;
import com.project.web.bean.OutMachineBean;
import com.project.web.service.IOutService;
import com.project.web.service.impl.OutServiceImpl;
import com.project.web.util.DateChange;
import com.project.web.util.StringCheck;

/**
 * Servlet implementation class OutServlet
 */
@WebServlet("/outServlet")
public class OutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private 	IOutService os = new OutServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutServlet() {
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
				
				//获取值
				int num = Integer.parseInt(request.getParameter("num"));
				String strDate = request.getParameter("starDate");
				String endDate = request.getParameter("endDate");
				String className = request.getParameter("className");
		
				HttpSession s = request.getSession();
				
				s.setAttribute("machineList", new ArrayList<OutMachineBean>());
			
				CutPageBean<OutBean>cut = os.findProject(num, StringCheck.stringCheck(className), DateChange.getDate(strDate), DateChange.getDate(endDate));
			
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
