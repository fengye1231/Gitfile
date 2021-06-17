package com.neusoft.dms.role.dao;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.role.domain.Role;
import com.neusoft.dms.role.domain.RoleVo;
import com.neusoft.dms.util.DaoException;

public interface RoleDao {
	//添加role
	public void addRole(Role role)throws DaoException;
	//删除role
	public void deleteRole(String code)throws DaoException ;
	//更新role
	public void updateRole(Role role)throws DaoException;
	//查找role
	public RoleVo getRoleByName(String name)throws DaoException;
	//role列表
	public List<Role> listRole()throws DaoException;
	
	public ArrayList<Nodes> getNodes(int pId);
	
	//写入角色权限
	public void addRolePerm(int roleId,int perId) throws DaoException;
	
	//根据ID查找角色
	public Role getRoleById(int id)throws DaoException;
	
	//角色权限清空
	public void deleteRolePerm(int roleId)throws DaoException;
	
	//根据姓名查找角色Id
	public int getIdByName(String name)throws DaoException;
	
	public ArrayList<Integer> getAllPerm(int roleId)throws DaoException;
	public List<Integer> hascheck(String name) throws DaoException;
}
