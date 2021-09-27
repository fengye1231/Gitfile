package com.project.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.web.bean.ClassBean;
import com.project.web.bean.OutBean;
import com.project.web.bean.OutMachineBean;
import com.project.web.bean.UserBean;
import com.project.web.service.IClassService;
import com.project.web.service.IOutMachineService;
import com.project.web.service.IOutService;
import com.project.web.service.impl.ClassServiceImpl;
import com.project.web.service.impl.OutMachineServiceImpl;
import com.project.web.service.impl.OutServiceImpl;

/**
 * Servlet implementation class Outbound
 */
@WebServlet("/outbound")
public class Outbound extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOutService os = new OutServiceImpl();
	private IClassService cs = new ClassServiceImpl(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Outbound() {
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
		HttpSession session = request.getSession();
		
		int classId = Integer.parseInt(request.getParameter("classId"));
		//找到班级对象
		ClassBean classBean = cs.FindClassBean(classId);
		//得到用户
		UserBean bean = (UserBean) session.getAttribute("user");
		System.out.println("取"+bean);
		//得到出库记录对象集合
		List<OutMachineBean>machineList = (List<OutMachineBean>) session.getAttribute("machineList");
		//得到新的出库记录
		OutBean outBean = new OutBean();
		outBean.setClassId(classId);
		outBean.setClassName(classBean.getName());
		System.err.println(classBean);
		outBean.setUserId(bean.getId());
		outBean.setUserName(bean.getName());
		outBean.setList(machineList);
		
		os.addOut(outBean);
		
		response.sendRedirect("/project2/jsp/drugDeliveryManagement/outBound/outBound_main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
