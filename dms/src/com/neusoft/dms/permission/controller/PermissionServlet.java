package com.neusoft.dms.permission.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.menu.service.MenuService;
import com.neusoft.dms.menu.service.MenuServiceImpl;
import com.neusoft.dms.permission.domain.Permission;
import com.neusoft.dms.permission.service.PermissionService;
import com.neusoft.dms.permission.service.PermissionServiceImpl;
import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;
import com.neusoft.dms.util.State;

public class PermissionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String service = request.getParameter("service");
		
		if("addPermission".equals(service)){
			
			addPermission(request, response);
			
		} else if ("queryPermission".equals(service)){
			
			queryPermission(request, response);
			
		} else if ("delPermission".equals(service)){
			
			delPermission(request, response);
			
		} else if ("modifyPermission".equals(service)){
			
			modifyPermission(request, response);
			
		} else if ("getPermission".equals(service)){
			
			getPermission(request, response);
			
		} else if ("checkPerName".equals(service)){
			
			checkPerName(request, response);
			
		} else if ("getLeader".equals(service)){
			
			getLeader(response);
			
		}
	}
	
	private void addPermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String perName = request.getParameter("perName");
		String perPath = request.getParameter("perPath");
		String leaderPermissionId = request.getParameter("leaderPermissionId");
		
		Permission permission = new Permission();
		
		if("".equals(leaderPermissionId)){
			permission.setPerName(perName);
			permission.setPerPath(perPath);
			permission.setLeaderPermissionId(0);
		}else{
			int slp = Integer.parseInt(leaderPermissionId);
			permission.setPerName(perName);
			permission.setPerPath(perPath);
			permission.setLeaderPermissionId(slp);
		}
		
		PermissionService permissionService = PermissionServiceImpl.getInstance();
		
		try {
			boolean isSuccess = permissionService.addPermission(permission);
			if(isSuccess){
				request.getRequestDispatcher("/admin/permission/index.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "添加失败");
				request.getRequestDispatcher("/admin/permission/addpermission.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private void queryPermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String perName = ParameterUtil.get(request, "perName", null, ParameterUtil.EMPTY);
		String perPath = ParameterUtil.get(request, "perPath", null, ParameterUtil.EMPTY);
		if(perName!=null){
			perName = URLDecoder.decode(perName, "UTF-8");
		}
		if(perPath!=null){
			perPath = URLDecoder.decode(perPath, "UTF-8");
		}
		
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
		
		PageQuery query = new PageQuery(pageNum, pageSize);
		query.setParam("perName", perName);
		query.setParam("perPath", perPath);
		
		try {	
			Table table = PermissionServiceImpl.getInstance().queryPermissionByAll(query);
			ServletUtil.output(response, table);		
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void delPermission(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			ServletException, IOException {
		
		String perName = request.getParameter("perName");
		if(perName!=null){
			perName = URLDecoder.decode(perName, "UTF-8");
		}
		System.out.println("perName:"+perName);
		PermissionService permissionService = PermissionServiceImpl.getInstance();
		
		try {
			boolean isSuccess = permissionService.delPermission(perName);
			if(isSuccess){
				ServletUtil.stateJson(response, State.OK);
			}else{
				ServletUtil.stateJson(response, State.FAILED);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private void modifyPermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int perId = Integer.parseInt(request.getParameter("perId"));
		String perName = request.getParameter("perName");
		String perPath = request.getParameter("perPath");
		String leaderPermissionId = request.getParameter("leaderPermissionId");
		
		Permission permission = new Permission();
		
		if(leaderPermissionId == null || "".equals(leaderPermissionId)){
			permission.setPerId(perId);
			permission.setPerName(perName);
			permission.setPerPath(perPath);
			permission.setLeaderPermissionId(0);
		}else{
			int slp = Integer.parseInt(leaderPermissionId);
			permission.setPerId(perId);
			permission.setPerName(perName);
			permission.setPerPath(perPath);
			permission.setLeaderPermissionId(slp);
		}
		
		PermissionService permissionService = PermissionServiceImpl.getInstance();
		
		try {
			boolean isSuccess = permissionService.updatePermission(permission);
			if(isSuccess){
				//request.getRequestDispatcher("/admin/permission/index.jsp").forward(request, response);
				response.sendRedirect(response.encodeRedirectURL("/dms/admin/permission/"));
			}else{
				request.setAttribute("error", "修改失败");
				request.getRequestDispatcher("/admin/permission/modify.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private void getPermission(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			ServletException, IOException {
		
		String perName = new String(request.getParameter("perName").getBytes("ISO-8859-1"),"utf-8");

		PermissionService permissionService = PermissionServiceImpl.getInstance();
		
		try {
			Permission permission = permissionService.getPermissionByName(perName);
			request.setAttribute("permission", permission);	
			request.getRequestDispatcher("/admin/permission/modify.jsp").forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	private void checkPerName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String perName = new String(request.getParameter("perName").getBytes("ISO-8859-1"),"utf-8");

		System.out.println("PerName:"+perName);
		Permission permission = new Permission();
		permission.setPerName(perName);
		
		PermissionService permissionService = PermissionServiceImpl.getInstance();
		
		try{
			boolean isSuccess = permissionService.checkPerName(permission);
			if(isSuccess){
				response.getWriter().write("true");
			}else{
				response.getWriter().write("false");
			}
		}catch(ServiceException e){
			e.printStackTrace();
		}
	}

	private void getLeader(HttpServletResponse response) throws IOException {
		
		List<Permission> perList = null;
		PermissionService permissionService = PermissionServiceImpl.getInstance();
		
		try {
			perList = permissionService.listParentPermission();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("perList", perList);
			response.getWriter().write(jsonObject.toString());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
