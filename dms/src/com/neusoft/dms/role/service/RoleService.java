package com.neusoft.dms.role.service;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.role.dao.Nodes;
import com.neusoft.dms.role.domain.Role;
import com.neusoft.dms.role.domain.RoleVo;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

public interface RoleService {
	//添加role
	public boolean addRole(Role role)throws ServiceException;
	//删除role
	public boolean deleteRole(String code)throws ServiceException;
	//修改role
	public boolean updateRole(Role role)throws ServiceException;
	//查找role
	public Role getRoleByName(String name)throws ServiceException;
	//列表role
	public List<Role> listRole()throws ServiceException;
	//写入角色权限
	public boolean addRolePerm(int roleId,int perId) throws ServiceException;
	
	//清空角色权限
	public void deleteRolePerm(int roleId)throws ServiceException;
	
	public ArrayList<Nodes> getAllPerm();
	public String objectToJson(Object object);
	
	//根据姓名获得Id
	public int getIdByName(String name)throws ServiceException;
	
	public ArrayList<Integer> getAllPerm(int roleId)throws ServiceException;
	public List<Integer> hasCheck(String name)throws ServiceException;
	
}
