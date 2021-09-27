package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.web.bean.OutBean;
import com.project.web.service.IOutMachineService;
import com.project.web.service.IOutService;
import com.project.web.service.impl.OutMachineServiceImpl;
import com.project.web.service.impl.OutServiceImpl;

/**
 * Servlet implementation class findOne
 */
@WebServlet("/outFindOne")
public class OutFindOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private 	IOutService os = new OutServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutFindOne() {
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
		
		
		OutBean bean = os.findById(id);
	
		HttpSession session = request.getSession();
		session.setAttribute("bean", bean);
		
		response.sendRedirect("/project2/jsp/drugDeliveryManagement/outBound/findByOutBound.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
