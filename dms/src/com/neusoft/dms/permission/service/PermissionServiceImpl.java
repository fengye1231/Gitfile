package com.neusoft.dms.permission.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.domain.MenuItem;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.dao.EmployeeDao;
import com.neusoft.dms.employee.dao.EmployeeDaoImpl;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.menu.dao.MenuDao;
import com.neusoft.dms.menu.dao.MenuDaoImpl;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.permission.dao.PermissionDao;
import com.neusoft.dms.permission.dao.PermissionDaoImpl;
import com.neusoft.dms.permission.domain.Permission;
import com.neusoft.dms.role.service.RoleService;
import com.neusoft.dms.role.service.RoleServiceImpl;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

public class PermissionServiceImpl implements PermissionService {

	private static PermissionServiceImpl instance = new PermissionServiceImpl();
	private PermissionServiceImpl(){}
	public static PermissionServiceImpl getInstance(){
		return instance;
	}
	@Override
	public boolean addPermission(Permission permission) throws ServiceException {
		boolean isSuccess = false;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			PermissionDao permissionDao = new PermissionDaoImpl(con);
			if(permissionDao.getPermissionByName(permission.getPerName())==null){
				permissionDao.addPermission(permission);
				isSuccess = true;
			}
		}catch(DaoException e){
			throw new ServiceException("添加权限错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("添加权限错误"+e);
			}
		}
		return isSuccess;
	}

	@Override
	public boolean delPermission(String perName) throws ServiceException {
		boolean isSuccess = false;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			PermissionDao permissionDao = new PermissionDaoImpl(con);
			permissionDao.delPermission(perName);
			isSuccess = true;
		}catch(DaoException e){
			throw new ServiceException("删除权限错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除权限错误"+e);
			}
		}
		return isSuccess;
	}
	
	
	public Permission getPermissionByName(String perName) throws ServiceException{
		Permission permission = null;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			PermissionDao permissionDao = new PermissionDaoImpl(con);
			permission = permissionDao.getPermissionByName(perName);
			//System.out.println("ServiceMenuCode"+menu.getMenuCode());

		}catch(DaoException e){
			throw new ServiceException("获取菜单名失败"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("获取菜单名失败"+e);
			}
		}
		return permission;
	}
	
	public boolean checkPerName(Permission permission) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			PermissionDao permissionDao = new PermissionDaoImpl(con);
			if(permissionDao.getPermissionByName(permission.getPerName())==null){
				isSuccess = true;
			}
		}catch(DaoException e){
			throw new ServiceException("添加权限错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("添加权限错误"+e);

			}
		}
		return isSuccess;
	}


	

	@Override
	public boolean updatePermission(Permission permission)
			throws ServiceException {
		boolean isSuccess = false;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			PermissionDao permissionDao = new PermissionDaoImpl(con);
			permissionDao.updatePermission(permission);
			isSuccess = true;
		}catch(DaoException e){
			throw new ServiceException("修改权限错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("修改权限错误"+e);
			}
		}
		return isSuccess;
	}
	
	public List<Permission> listAllPermission() throws ServiceException{
		List<Permission> perList = null;
		Connection con = null; 
		List<Permission> perList1 = new ArrayList<Permission>();
		try{
			con = DBUtil.getConnection();
			PermissionDao permissionDao = new PermissionDaoImpl(con);
			perList = permissionDao.listAllPermission();
			
			for(int i=0;i<perList.size();i++){
				if(perList.get(i).getPerId()!=0){
					perList1.add(perList.get(i));
				}
			}
		}catch(DaoException e){
			throw new ServiceException("列出所有菜单错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("列出所有菜单错误"+e);
			}
		}
		return perList;
	}
	
	public Table queryPermissionByAll(PageQuery query) throws ServiceException{
		Connection con = null;
		try{
			Table result = null;
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			String perName = (String)query.getParam("perName");
			String perPath = (String)query.getParam("perPath");
			
			con = DBUtil.getConnection();
			PermissionDao permissionDao = new PermissionDaoImpl(con);
			Page page = permissionDao.listPermissionByAll(perName,perPath, pageNum, pageSize);
			result = new Table(page);
			return result;			
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	public List<MenuItem> comparePath(Permission permission) throws DaoException{
		Connection con = null;
		String perPath = permission.getPerPath();
		List<MenuItem> newList = new ArrayList<MenuItem>();
		MenuDao menuDao = new MenuDaoImpl(con);
		try {
			con = DBUtil.getConnection();
			List<MenuItem> menuList = menuDao.getItems();
			for(MenuItem menu : menuList){
				if(perPath.contains(menu.getLink())){
					newList.add(menu);
				}
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(con);
		}
		return newList;
	}
	@Override
	public Permission getPermissionById(int pId) throws ServiceException {
		Connection con = null;
		Permission permission=null;
		try {
			con = DBUtil.getConnection();
			PermissionDao permissionDao=new PermissionDaoImpl(con);
			permission=permissionDao.getPermissionById(pId);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return permission;
	}
	
	public List<Permission> listPermissionByEmpId(int empId) throws ServiceException{
		Connection con = null;
		RoleService roleService = RoleServiceImpl.getInstance();
		List<Permission> perList = new ArrayList<Permission>();
		try{
			con = DBUtil.getConnection();
			EmployeeDao employeeDao = new EmployeeDaoImpl(con);
			PermissionDao permissionDao = new PermissionDaoImpl(con);
			EmployeeVo empVo = employeeDao.getEmployeeByEmpId(empId);
			ArrayList<Integer> perId = roleService.getAllPerm(empVo.getRoleId());
			for(int pId:perId){
				Permission permission = permissionDao.getPermissionById(pId);
				Permission parentPer = permissionDao.listParentPermission(permission.getLeaderPermissionId());
				
				perList.add(permission);
				if(parentPer!=null){
					perList.add(parentPer);
				}
			}

		}catch(DaoException e){
			e.printStackTrace();
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return perList;
	}
	
	
	public List<Permission> listParentPermission() throws ServiceException{
		List<Permission> parentList = null;
		Connection con = null; 
		try{
			con = DBUtil.getConnection();
			PermissionDao permissionDao = new PermissionDaoImpl(con);
			
			parentList = permissionDao.listParentPermission();
	
		}catch(DaoException e){
			throw new ServiceException("列出所有菜单错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("列出所有菜单错误"+e);
			}
		}
		return parentList;
	}


}




