package com.project.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.jasper.tagplugins.jstl.core.Remove;
import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.OutMachineBean;

/**
 * Servlet implementation class DelOutMachine
 */
@WebServlet("/delOutMachine")
public class DelOutMachine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelOutMachine() {
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
		
		int id = Integer.parseInt(request.getParameter("id").substring(2));
		
		HttpSession session = request.getSession();
		List<OutMachineBean>machineList = (List<OutMachineBean>) session.getAttribute("machineList");

		System.out.println(machineList);
		for (int i = 0; i < machineList.size(); i++) {
			int ids = machineList.get(i).getMachineId();
			if(id==ids) {
				machineList.remove(i);
				
				session.setAttribute("machineList", machineList);
			
				response.sendRedirect("/project2/jsp/drugDeliveryManagement/outBound/addOutBound.jsp");
			}
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
