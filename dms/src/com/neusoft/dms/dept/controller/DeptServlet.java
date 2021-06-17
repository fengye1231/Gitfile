package com.neusoft.dms.dept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.dms.dept.dao.Nodes;
import com.neusoft.dms.dept.service.DeptService;
import com.neusoft.dms.dept.service.DeptServiceImpl;
import com.neusoft.dms.domain.Dept;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServletUtil;
import com.neusoft.dms.util.State;

public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
	}

	public void destroy() {
		super.destroy(); 
	}
	
	public DeptServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deptAction = null;
		
		deptAction = request.getParameter("deptAction");
		
		if("ajaxTree".equals(deptAction))
			doAjaxData(request, response);
		if("ajaxChildListPage".equals(deptAction))
			doAjaxChildListPage(request, response);
		if("displayInfo".equals(deptAction))
			doDisplayInfo(request, response);
		if("modifyDept".equals(deptAction))
			doModifyDept(request, response);
		if("deleteDept".equals(deptAction))
			doDeleteDept(request, response);
		
		if("addChild".equals(deptAction))
			doAddChild(request, response);
		//if("modifyChild".equals(deptAction))
			//doModifyChild(request, response);
		if("deleteChild".equals(deptAction))
			doDeleteChild(request, response);
		if("viewModify".equals(deptAction))
			doViewModify(request, response);
		
		if("validateCode".equals(deptAction))
			doValidateCode(request, response);
		if("validateName".equals(deptAction))
			doValidateName(request, response);
		if("validateDel".equals(deptAction))
			doValidateDel(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doAjaxData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String deptID = (String) session.getAttribute("deptId");
		
		int deptId = 0;
		if(deptID != null && !"".equals(deptID)){
			deptId = Integer.parseInt(deptID);
		}
		
		DeptService deptService = new DeptServiceImpl();
		ArrayList<Nodes> nodeList = deptService.getAllDept();
		Nodes root = deptService.setSelectedNode(deptId,nodeList.get(0));
		nodeList.set(0, root);
		
		String jsonString = deptService.objectToJson(nodeList);
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		out.flush();
		out.close();
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doAjaxChildListPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int deptId = -1;
		
		String deptID = (String) session.getAttribute("deptId");
		if(deptID != null && !"".equals(deptID)){
			deptId = Integer.parseInt(deptID);
		}
		//System.out.println("sessiondeptID:"+deptID);
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		//int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
		
		DeptService deptService = new DeptServiceImpl();
		Page page = deptService.getChildListPage(deptId, pageNum);
		ServletUtil.output(response, page);
	}
	
	/**
	 * display the dept info when click on the dept in the tree menu
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doDisplayInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		HttpSession session = request.getSession();
		
		String deptID = request.getParameter("deptId");
		
		int deptId = -1;
		if(!"".equals(deptID)){
			deptId = Integer.parseInt(deptID);
		}
		
		DeptService deptService = new DeptServiceImpl();
		Dept deptCurr = null;
		deptCurr = deptService.getDeptById(deptId);
		
		Dept deptSenior = null;
		int seniorDeptId = deptService.getSeniorDeptId(deptId);
		deptSenior = deptService.getDeptById(seniorDeptId);
		
		ArrayList<Dept> childList = null;
		childList = deptService.getChildList(deptId);
		
		session.setAttribute("deptCurr", deptCurr);
		session.setAttribute("deptSenior", deptSenior);
		session.setAttribute("deptId", deptID);
		session.setAttribute("childList", childList);
		
		forward = this.getInitParameter("displayInfo");
		request.getRequestDispatcher(forward).forward(request,response);
	}
	
	/**
	 * modify the current dept
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doModifyDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String deptCode = request.getParameter("deptCode");
		String deptName = request.getParameter("deptName");
		String deptRemark = request.getParameter("deptRemark");
		String seniorDeptID = request.getParameter("seniorDeptId");

		Integer seniorDeptId = null;
		if(!deptId.equals(seniorDeptID)){
			if(!"".equals(seniorDeptID)){
				seniorDeptId = new Integer(Integer.parseInt(seniorDeptID));
			}
		}
		
		Dept dept = new Dept();
		dept.setDeptId(Integer.parseInt(deptId));
		dept.setDeptCode(deptCode);
		dept.setDeptName(deptName);
		dept.setDeptRemark(deptRemark);
		dept.setSeniorDeptId(seniorDeptId);
		
		DeptService deptService = new DeptServiceImpl();
		deptService.modify(dept);
		
		response.sendRedirect("/dms/daily/setting/dept");
	}
	
	/**
	 * delete the current dept including all the child dept
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doDeleteDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String deptID = request.getParameter("deptId");
		int deptId = -1;
		if(!"".equals(deptID)){
			deptId = Integer.parseInt(deptID);
		}
		DeptService deptService = new DeptServiceImpl();
		int updateCount = deptService.deleteParent(deptId);
		session.setAttribute("updateCount", updateCount);
		
		response.sendRedirect("/dms/daily/setting/dept");
	}
	
	/**
	 * add child dept
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doAddChild(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deptCode = request.getParameter("deptCode");
		String deptName = request.getParameter("deptName");
		String deptRemark = request.getParameter("deptRemark");
		String seniorDeptID = request.getParameter("deptId");
		
		int seniorDeptId = -1;
		if(!"".equals(seniorDeptID)){
			seniorDeptId = Integer.parseInt(seniorDeptID);
		}
		else{
			System.out.println("deptId null----doDisplayInfo----DeptServlet");
		}
		
		Dept dept = new Dept();
		dept.setDeptCode(deptCode);
		dept.setDeptName(deptName);
		dept.setDeptRemark(deptRemark);
		dept.setSeniorDeptId(seniorDeptId);
		
		DeptService deptService = new DeptServiceImpl();
		deptService.insert(dept);
		
		response.sendRedirect("/dms/daily/setting/dept");
	}
	
	/**
	 * modify the child dept
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 
	private void doModifyChild(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String deptCode = request.getParameter("deptCode");
		String deptName = request.getParameter("deptName");
		String deptRemark = request.getParameter("deptRemark");
		String seniorDeptID = request.getParameter("deptId");
		
		Integer seniorDeptId = null;
		if(!deptId.equals(seniorDeptID)){
			if(!"".equals(seniorDeptID)){
				seniorDeptId = new Integer(Integer.parseInt(seniorDeptID));
			}
		}
		
		Dept dept = new Dept();
		dept.setDeptId(Integer.parseInt(deptId));
		dept.setDeptCode(deptCode);
		dept.setDeptName(deptName);
		dept.setDeptRemark(deptRemark);
		dept.setSeniorDeptId(seniorDeptId);
		
		DeptService deptService = new DeptServiceImpl();
		deptService.modify(dept);
		
		response.sendRedirect("/dms/daily/setting/dept");
	}
	*/
	/**
	 * delete the child dept
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doDeleteChild(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String deptID = request.getParameter("deptId");
		
		System.out.println(deptID);
		int deptId = -1;
		if(!"".equals(deptID)){
			deptId = Integer.parseInt(deptID);
		}
		
		DeptService deptService = new DeptServiceImpl();
		int updateCount = deptService.deleteChild(deptId);

		ServletUtil.stateJson(response, updateCount == -1? State.FAILED: State.OK);
	}
	
	/**
	 * 修改之前跳转到"修改页面"
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doViewModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		
		String deptID = request.getParameter("deptId");
		int deptId = -1;
		if(!"".equals(deptID)){
			deptId = Integer.parseInt(deptID);
		}
		Dept dept = null;
		DeptService deptService = new DeptServiceImpl();
		dept = deptService.getDeptById(deptId);
		
		request.setAttribute("deptModify", dept);
		forward = this.getInitParameter("viewModify");
		request.getRequestDispatcher(forward).forward(request,response);
	}
	
	private void doValidateCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deptCode = request.getParameter("deptCode");
		//System.out.println("deptCode:"+deptCode);
		
		DeptService deptService = new DeptServiceImpl();
		boolean isExist = deptService.getDeptByDeptCode(deptCode);
		
		if(isExist){
			response.getWriter().write("false");
		}else{
			response.getWriter().write("true");
		}
	}
	
	private void doValidateName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deptName = new String(request.getParameter("deptName").getBytes("ISO-8859-1"),"utf-8");
		//System.out.println("deptName:"+deptName);
		
		DeptService deptService = new DeptServiceImpl();
		boolean isExist = deptService.getDeptByDeptName(deptName);
		
		if(isExist){
			response.getWriter().write("false");
		}else{
			response.getWriter().write("true");
		}
	}
	
	private void doValidateDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer updateCount = (Integer) session.getAttribute("updateCount");

		
	}
}
