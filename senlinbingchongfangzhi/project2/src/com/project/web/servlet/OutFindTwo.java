package com.project.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.OutMachineBean;
import com.project.web.service.IOutMachineService;
import com.project.web.service.impl.OutMachineServiceImpl;

/**
 * Servlet implementation class findTwo
 */
@WebServlet("/outFindTwo")
public class OutFindTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IOutMachineService oms = new OutMachineServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutFindTwo() {
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
				
				int num = Integer.parseInt(request.getParameter("num"));
				int id = Integer.parseInt(request.getParameter("id"));
				
				CutPageBean<OutMachineBean>cut = oms.findByOut(num, id);
//				System.out.println(cut.getTotalPageNum());
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
