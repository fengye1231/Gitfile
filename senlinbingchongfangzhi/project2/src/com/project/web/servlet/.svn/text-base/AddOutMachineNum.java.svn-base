package com.project.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.OutMachineBean;

/**
 * Servlet implementation class AddOutMachineNum
 */
@WebServlet("/addOutMachineNum")
public class AddOutMachineNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOutMachineNum() {
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
		HttpSession s = request.getSession();
		List<OutMachineBean>list = (List<OutMachineBean>) s.getAttribute("machineList");
		int jsId = Integer.parseInt(request.getParameter("id").substring(2)); 
		int num = Integer.parseInt(request.getParameter("num"));
		
		for(int i=0;i<list.size();i++) {
				int outId = list.get(i).getMachineId();
				if(jsId==outId) {
					list.get(i).setNum(num);
				}
		}
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), null);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
