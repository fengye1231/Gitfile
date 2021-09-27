package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.web.bean.MachineBean;
import com.project.web.service.IMachineService;
import com.project.web.service.impl.MachineServiceImpl;
import com.project.web.util.StringCheck;

/**
 * Servlet implementation class AddMachine
 */
@WebServlet("/addMachine")
public class AddMachine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private 	IMachineService ms = new MachineServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMachine() {
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
		
		String name = StringCheck.stringCheck(request.getParameter("names"));
		String defeat = StringCheck.stringCheck(request.getParameter("addDrugDeviceSel"));
		String kind = StringCheck.stringCheck(request.getParameter("sel"));
		String use = StringCheck.stringCheck(request.getParameter("text"));
		
		MachineBean bean = new MachineBean(name, defeat, kind, use);
		ms.addMachine(bean);
		response.sendRedirect("jsp/drugDeliveryManagement/drugDevice/drugDevice-main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
