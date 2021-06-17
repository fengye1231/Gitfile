package com.neusoft.dms.role.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.neusoft.dms.permission.dao.PermissionDaoImpl;
import com.neusoft.dms.permission.domain.Permission;
import com.neusoft.dms.role.dao.Nodes;
import com.neusoft.dms.role.dao.RoleDao;
import com.neusoft.dms.role.dao.RoleDaoImpl;
import com.neusoft.dms.role.domain.Role;
import com.neusoft.dms.role.domain.RoleVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

public class RoleServiceImpl implements RoleService {

	private static RoleServiceImpl instance=new RoleServiceImpl();
	private RoleServiceImpl (){
		
	}
	public static RoleServiceImpl getInstance(){
		return instance;
	}
	@Override
	public boolean addRole(Role role) throws ServiceException {
		boolean isSuccess=false;
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			RoleVo roleVo=roleDao.getRoleByName(role.getRoleName());
			if(roleVo==null){
				roleDao.addRole(role);
				isSuccess=true;
			}
		} catch (DaoException e) {
			throw new ServiceException("添加角色错误",e);
		}catch (Exception e) {
			throw new ServiceException("添加角色错误",e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("添加角色错误",e);
			}
		}
		return isSuccess;
	}

	@Override
	public boolean deleteRole(String name) throws ServiceException {
		boolean isSuccess=false;
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			
			roleDao.deleteRole(name);
			isSuccess=true;
		} catch (DaoException e) {
			throw new ServiceException("删除角色错误",e);
		}catch (Exception e) {
			throw new ServiceException("删除角色错误",e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除角色错误",e);
			}
		}
		return isSuccess;
	}

	@Override
	public boolean updateRole(Role role) throws ServiceException {
		boolean isSuccess=false;
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			roleDao.updateRole(role);
			isSuccess=true;
		} catch (DaoException e) {
			throw new ServiceException("修改角色错误",e);
		}catch(Exception e){
			throw new ServiceException("修改角色错误",e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("修改角色错误",e);
			}
		}
		return isSuccess;
	}
	@Override
	public Role getRoleByName(String name) throws ServiceException {
		Role roleVo=null;
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			roleVo=roleDao.getRoleByName(name);
		} catch (DaoException e) {
			throw new ServiceException("查询role信息错误",e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("查询role信息错误",e);
			}
		}
		return roleVo;
	}
	
	//角色列表
	public List<Role> listRole()throws ServiceException{
		List<Role> list=new ArrayList<Role>();
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			list=roleDao.listRole();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return list;
	}
	
	//角色权限
	public ArrayList<Nodes> getAllPerm(){
		ArrayList<Nodes> treeList = new ArrayList<Nodes>();
		Nodes root=new Nodes();
		
		try {
			//设置根权限
			Connection con = DBUtil.getConnection();
			PermissionDaoImpl permDao = new PermissionDaoImpl(con);
			Permission permission = permDao.getPermissionByName("所有权限");
		    
		    root.setId(permission.getPerId());
			root.setText(permission.getPerName());
			root.setNodes();
			
			treeList.add(root);
			root = this.makeTree(root);
			
			return treeList;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return treeList;
	}
	
	public Nodes makeTree(Nodes treeRoot){
		Nodes root=treeRoot;
		
		RoleDao roleDao=new RoleDaoImpl();
		root.setNodes(roleDao.getNodes(root.getId()));
		
		List<Nodes> nodes=root.getNodes();
		if(nodes != null && nodes.size() > 0){
			for(Nodes node:nodes){
				node=this.makeTree(node);
			}
		}
		
		return root;
	}
	
	public String objectToJson(Object object){
		String jsonString="";
		jsonString=JSON.toJSONString(object);
		return jsonString;	
	}
	
	//角色权限写入
	public boolean addRolePerm(int roleId,int perId)throws ServiceException{
		boolean isSuccess=false;
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			roleDao.addRolePerm(roleId, perId);
		} catch (DaoException e) {
			throw new ServiceException("角色权限写入错误",e);
		}catch (Exception e) {
			throw new ServiceException("角色权限写入错误",e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("角色权限写入错误",e);
			}
		}
		return isSuccess;
	}
	
	//添加前，角色权限清空
	public void deleteRolePerm(int roleId)throws ServiceException{
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			roleDao.deleteRolePerm(roleId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}
	
	//根据姓名获得Id
	public int getIdByName(String name)throws ServiceException{
		Connection con=null;
		int id;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			id=roleDao.getIdByName(name);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return id;
	}
	@Override
	public ArrayList<Integer> getAllPerm(int roleId) throws ServiceException {
		ArrayList<Integer> permList=new ArrayList<Integer>();
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			permList=roleDao.getAllPerm(roleId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return permList;
	}
	
	public List<Integer> hasCheck(String name)throws ServiceException {
		List<Integer> checklist;
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			RoleDao roleDao=new RoleDaoImpl(con);
			checklist=roleDao.hascheck(name);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return checklist;
		
	}

	
}
