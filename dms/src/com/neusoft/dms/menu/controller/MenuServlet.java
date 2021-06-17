package com.neusoft.dms.menu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.neusoft.dms.dept.service.DepartmentLoadServiceImpl;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.menu.service.MenuService;
import com.neusoft.dms.menu.service.MenuServiceImpl;
import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.InvaliedParamException;
import com.neusoft.dms.util.ParameterUtil;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;
import com.neusoft.dms.util.State;

/*
 * TODO 整合menu查询功能
 *      调试下拉列表取值
 *      添加删除确认
 */

public class MenuServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String service = request.getParameter("service");

		if("addMenu".equals(service)){
			
			addMenu(request, response);
			
		}	else if ("checkMenuName".equals(service)){
			
			checkMenuName(request, response);
			
		}	else if ("checkMenuCode".equals(service)){
			
			checkMenuCode(request, response);
			
		}	else if ("queryMenu".equals(service)){
			
			queryMenu(request, response);
			
		}	else if ("delMenu".equals(service)){
			
			delMenu(request, response);
			
		}	else if ("modifyMenu".equals(service)){
			
			modifyMenu(request, response);		
			
		}	else if ("getMenu".equals(service)){
			
			getMenu(request, response);
			
		}	else if ("getParent".equals(service)){
			
			getParent(response);
			
		}
	}
	
	private void addMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String menuCode = request.getParameter("menuCode");
		String menuName = request.getParameter("menuName");
		String menuPath = request.getParameter("menuPath");
		String parentMenuId = request.getParameter("parentMenuId");
		Menu menu = new Menu();
		
		if("".equals(parentMenuId)){
			menu.setMenuCode(menuCode);
			menu.setMenuName(menuName);
			menu.setParentMenuId(0);
			menu.setMenuPath(menuPath);
		}else{
			int mpi = Integer.parseInt(parentMenuId);
			menu.setMenuCode(menuCode);
			menu.setMenuName(menuName);
			menu.setParentMenuId(mpi);
			menu.setMenuPath(menuPath);
		}
		
		MenuService menuService = MenuServiceImpl.getInstance();
		
		try {
			boolean isSuccess = menuService.addMenu(menu);
			if(isSuccess){
				request.getRequestDispatcher("/admin/menu/index.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "添加失败");
				request.getRequestDispatcher("/admin/menu/addmenu.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private void checkMenuName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String menuName = new String(request.getParameter("menuName").getBytes("ISO-8859-1"),"utf-8");

		Menu menu = new Menu();
		menu.setMenuName(menuName);
		
		MenuService menuService = MenuServiceImpl.getInstance();
		
		try{
			boolean isSuccess = menuService.checkMenuName(menu);
			if(isSuccess){
				response.getWriter().write("true");
			}else{
				response.getWriter().write("false");
			}	
		}catch(ServiceException e){
			e.printStackTrace();
		}
	}
	
	private void checkMenuCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String menuCode = request.getParameter("menuCode");
		Menu menu = new Menu();
		menu.setMenuCode(menuCode);
		
		MenuService menuService = MenuServiceImpl.getInstance();
		
		try{
			boolean isSuccess = menuService.checkMenuCode(menu);
			if(isSuccess){
				response.getWriter().write("true");
			}else{
				response.getWriter().write("false");
			}
		}catch(ServiceException e){
			e.printStackTrace();
		}
	}

	private void queryMenu(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		int parentMenuId = ParameterUtil.getInt(request, "parentMenuId", 0);
		String menuName = ParameterUtil.get(request, "menuName", null, ParameterUtil.EMPTY);
		if(menuName!=null){
			menuName = URLDecoder.decode(menuName, "UTF-8");
		}
		
		int pageNum = ParameterUtil.getInt(request, "pageNum", 1);
		int pageSize = ParameterUtil.getInt(request, "pageSize", Constant.PAGE_SIZE);
		PageQuery query = new PageQuery(pageNum, pageSize);
		query.setParam("menuName", menuName);
		query.setParam("parentMenuId", parentMenuId);
		
		try {

				Table table = MenuServiceImpl.getInstance().queryMenuByAll(query);
				ServletUtil.output(response, table);
			
			
		} catch (ServiceException e) {
			e.printStackTrace();
			ServletUtil.stateJson(response, State.INTERNAL_SERVER_ERROR);
		}
    }
	
	private void delMenu(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			ServletException, IOException {
		
		String menuName = request.getParameter("menuName");
		menuName = URLDecoder.decode(menuName, "UTF-8");
		System.out.println(menuName);
		
		MenuService menuService = MenuServiceImpl.getInstance();
		
		try {
			boolean isSuccess = menuService.delMenu(menuName);
			if(isSuccess){
				ServletUtil.stateJson(response, State.OK);
			}else{
				ServletUtil.stateJson(response, State.FAILED);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private void modifyMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int menuId = Integer.parseInt(request.getParameter("menuId"));
		String menuCode = request.getParameter("menuCode");
		String menuName = request.getParameter("menuName");
		String parentMenuId = request.getParameter("parentMenuId");
		String menuPath = request.getParameter("menuPath");

		Menu menu = new Menu();
		
		if("".equals(parentMenuId)){
			menu.setMenuId(menuId);
			menu.setMenuCode(menuCode);
			menu.setMenuName(menuName);
			menu.setMenuPath(menuPath);
			menu.setParentMenuId(0);
		}else{
			int mpi = Integer.parseInt(parentMenuId);
			menu.setMenuId(menuId);
			menu.setMenuCode(menuCode);
			menu.setMenuName(menuName);
			menu.setMenuPath(menuPath);
			menu.setParentMenuId(mpi);
		}
		
		MenuService menuService = MenuServiceImpl.getInstance();
		
		try {
			boolean isSuccess = menuService.updateMenu(menu);
			if(isSuccess){
				request.getRequestDispatcher("/admin/menu/index.jsp").forward(request, response);
			}else{
				request.setAttribute("error", "修改失败");
				request.getRequestDispatcher("/admin/menu/modify.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	private void getMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String menuName = new String(request.getParameter("menuName").getBytes("ISO-8859-1"),"utf-8");
		MenuService menuService = MenuServiceImpl.getInstance();
		System.out.println("menuName:"+menuName);
		
		try {
			Menu menu = menuService.getMenuByName(menuName);
			request.setAttribute("menu", menu);	
			request.getRequestDispatcher("/admin/menu/modify.jsp").forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	private void getParent(HttpServletResponse response) throws IOException {
		
		List<Menu> parentList = null;
		MenuService menuService = MenuServiceImpl.getInstance();
		
		try {
			parentList = menuService.listParentMenu();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("parentList", parentList);
			response.getWriter().write(jsonObject.toString());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
