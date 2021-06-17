package com.neusoft.dms.permission.service;

import java.util.List;

import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.permission.domain.Permission;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

public interface PermissionService {
	
	/**
	 * 添加权限
	 * @param permission
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean addPermission(Permission permission) throws ServiceException;
	
	/**
	 * 删除权限
	 * @param perName
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean delPermission(String perName) throws ServiceException;
	
	/**
	 * 更新权限
	 * @param permission
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean updatePermission(Permission permission) throws ServiceException;
	
	/**
	 * 检查权限名是否重复
	 * @param permission
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean checkPerName(Permission permission) throws ServiceException;
	
    /**
     * 通过权限名得到权限	
     * @param perName
     * @return Permission
     * @throws ServiceException
     */
	public Permission getPermissionByName(String perName) throws ServiceException;
	
	/**
	 * 获得所有权限
	 * @return List<Permission>
	 * @throws ServiceException
	 */
	public List<Permission> listAllPermission() throws ServiceException;
	
	/**
	 * 综合分页查询
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table queryPermissionByAll(PageQuery query) throws ServiceException;
	
	/**
	 * 比较权限路径和菜单路径
	 * @param perId
	 * @return List<Menu>
	 * @throws DaoException
	 */
	public Permission getPermissionById(int pId) throws ServiceException;
	
	public List<Permission> listPermissionByEmpId(int empId) throws ServiceException;
	
	public List<Permission> listParentPermission() throws ServiceException;
}
