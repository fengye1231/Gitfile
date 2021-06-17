package com.neusoft.dms.role.controller;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.neusoft.dms.permission.domain.Permission;
import com.neusoft.dms.permission.service.PermissionService;
import com.neusoft.dms.permission.service.PermissionServiceImpl;
import com.neusoft.dms.prp.service.PrpService;
import com.neusoft.dms.role.dao.Nodes;
import com.neusoft.dms.role.domain.Role;
import com.neusoft.dms.role.domain.RoleVo;
import com.neusoft.dms.role.service.RoleService;
import com.neusoft.dms.role.service.RoleServiceImpl;
import com.neusoft.dms.util.ServiceException;
import com.neusoft.dms.util.ServletUtil;

public class RoleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String service=request.getParameter("service");
		//添加角色
		if ("add".equals(service)) {
			String name=request.getParameter("roleName");
			String roleCode=request.getParameter("roleCode");
			
			Role role=new Role();
			role.setRoleName(name);
			role.setRoleCode(roleCode);
			RoleService roleService=RoleServiceImpl.getInstance();
			try {
				boolean isSuccess=roleService.addRole(role);
				if(isSuccess){
					request.setAttribute("tip", "角色添加成功");
				}
				else{
					request.setAttribute("tip", "角色添加失败");
				}
				request.getRequestDispatcher("/admin/role/index.jsp").forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/dms/error/index.jsp").forward(request, response);
			}
		}
		
		//删除角色
		if("del".equals(service)){
			String n=request.getParameter("roleName");
			String name=new String(n.getBytes("iso-8859-1"),"utf-8");
			RoleService roleService=RoleServiceImpl.getInstance();
			try {
				boolean isSuccess=roleService.deleteRole(name);
				if(isSuccess){
					request.setAttribute("tip", "删除角色成功");
				}
				else{
					request.setAttribute("tip", "删除角色失败");
				}
				request.getRequestDispatcher("/admin/role/index.jsp").forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/dms/error/index.jsp").forward(request, response);
			}
		}
		
		//查询角色
		if("search".equals(service)){
			String name=request.getParameter("roleName");
			RoleService roleService=RoleServiceImpl.getInstance();
			Role roleVo=null;
			try {
				roleVo=roleService.getRoleByName(name);
				request.setAttribute("role", roleVo);
				request.getRequestDispatcher("/admin/role/index.jsp").forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/dms/error/index.jsp").forward(request, response);
			}
		}
		
		//修改角色
		if("update".equals(service)){
			String name=request.getParameter("roleName");
			String code=request.getParameter("roleCode");
			Role role=new Role();
			role.setRoleCode(code);
			role.setRoleName(name);
			RoleService roleService=RoleServiceImpl.getInstance();
			try {
				boolean isSuccess=roleService.updateRole(role);
				if(isSuccess){
					request.setAttribute("tip", "角色更新成功");
				}
				else {
					request.setAttribute("tip", "角色更新失败");
				}
				request.getRequestDispatcher("/admin/role/index.jsp").forward(request, response);
				
			} catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/dms/error/index.jsp").forward(request, response);
			}
		}
		
		//显示角色
		if("listRole".equals(service)){
			List<Role> roleList=new ArrayList<Role>();
			RoleService roleService=RoleServiceImpl.getInstance();
			try {
				roleList=roleService.listRole();
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("roleList",roleList);
				response.getWriter().write(jsonObject.toString());
				//request.setAttribute("listRole", roleList);
				//request.getRequestDispatcher("/admin/role/index.jsp").forward(request, response);
			} catch (ServiceException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/dms/error.jsp").forward(request, response);
			}
		}
		
		//角色权限
		if("role_perm".equals(service)){ 
			String role=request.getParameter("roleName");
			String rolem=new String(role.getBytes("iso-8859-1"),"utf-8");
			List<Permission> list=new ArrayList<Permission>();
			List<Integer> checklist=new ArrayList<Integer>();
			PermissionService peService=PermissionServiceImpl.getInstance();
			RoleService roleService=RoleServiceImpl.getInstance();
			try {
				list=peService.listAllPermission();
				checklist=roleService.hasCheck(rolem);
				//System.out.println(checklist);
				request.setAttribute("permList", list);
				request.setAttribute("role", rolem);
				request.setAttribute("checklist",checklist);
				request.getRequestDispatcher("/admin/role/role_pm.jsp").forward(request, response);
			} catch (ServiceException e) {
				ServletUtil.errorPage(request, response, null, e.getMessage());
			}
		}
		if("ajaxData".equals(service)){
			RoleService roleService=RoleServiceImpl.getInstance();
			ArrayList<Nodes> nodeList=roleService.getAllPerm();
			String jsonString=roleService.objectToJson(nodeList);
			
			PrintWriter out=response.getWriter();
			out.print(jsonString);
			out.flush();
			out.close();
		}
		
		//角色权限写入
		if("daoPerm".equals(service)){
			String arrp=request.getParameter("arrp");
			String r=request.getParameter("role");
			String roleName = r;
			//String roleName=new String(r.getBytes("iso-8859-1"),"utf-8");
			//System.out.println(roleName);
			Role role=null;
			int roleid=0;
			int pid=0;
			String p="";
			String []pIds=arrp.split(",");
			
			RoleService roleService=RoleServiceImpl.getInstance();
			try {
				role=roleService.getRoleByName(roleName);
				roleid=roleService.getIdByName(roleName);
				roleService.deleteRolePerm(roleid);
				if(arrp!=""){
				for(int i=0;i<pIds.length;i++){
					p=pIds[i];
					pid=Integer.parseInt(p);
					roleService.addRolePerm(roleid, pid);
				}
				}
				request.getRequestDispatcher("/admin/role/index.jsp").forward(request, response);
		     } catch (ServiceException e) {
		    	 e.printStackTrace();
		    	 ServletUtil.errorPage(request, response, null, e.getMessage());
			}
			
		}
		
	}

}