package com.neusoft.dms.permission.dao;

import java.util.List;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.permission.domain.Permission;
import com.neusoft.dms.util.DaoException;

public interface PermissionDao {

	/**
	 * 添加权限
	 * @param permission
	 * @throws DaoException
	 */
	public void addPermission(Permission permission) throws DaoException;
	
	/**
	 * 删除权限
	 * @param perName
	 * @throws DaoException
	 */
	public void delPermission(String perName) throws DaoException;
	
	/**
	 * 更新权限
	 * @param permission
	 * @throws DaoException
	 */
	public void updatePermission(Permission permission) throws DaoException;
	
	/**
	 * 通过权限名获取权限
	 * @param perName
	 * @return Permission
	 * @throws DaoException
	 */
	public Permission getPermissionByName(String perName) throws DaoException;
	
	/**
	 * 通过权限id获取权限
	 * @param perId
	 * @return Permission
	 * @throws DaoException
	 */
	public Permission getPermissionById(int perId) throws DaoException;
	
	/**
	 * 获取所有权限
	 * @return List<Permission>
	 * @throws DaoException
	 */
	public List<Permission> listAllPermission() throws DaoException;
	
	/**
	 * 获取综合查询的分页
	 * @param perName
	 * @param perPath
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
	public Page listPermissionByAll(String perName,String perPath,int pageNum,int pageSize) throws DaoException;
	
	/**
	 * 获取权限的子权限
	 * @param perId
	 * @return List<Permission>
	 * @throws DaoException
	 */
	public List<Permission> listChildPermission(int perId) throws DaoException;
	
	public List<Permission> listParentPermission() throws DaoException;
	
	public Permission listParentPermission(int perId) throws DaoException;
	
}
