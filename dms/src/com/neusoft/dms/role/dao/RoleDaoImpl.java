package com.neusoft.dms.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Node;

import org.w3c.dom.NodeList;

import com.neusoft.dms.dept.dao.DoPstmt;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.role.domain.Role;
import com.neusoft.dms.role.domain.RoleVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class RoleDaoImpl implements RoleDao {

	private Connection con=null;
	public RoleDaoImpl(Connection con){
		this.con=con;
	}
	public RoleDaoImpl(){
		
	}
	//添加角色
	public void addRole(Role role) throws DaoException {
		PreparedStatement pstmt=null;
		String sql="insert into role(roleCode,roleName) values(?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, role.getRoleCode());
			pstmt.setString(2, role.getRoleName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}

	//删除角色
	public void deleteRole(String name) throws DaoException {
		PreparedStatement pstmt=null;
		String sql="delete from role where roleName=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}

	//更新角色
	public void updateRole(Role role) throws DaoException {
		PreparedStatement pstmt=null;
		String sql="update role set roleName=? where roleCode=?";
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, role.getRoleName());
			pstmt.setString(2, role.getRoleCode());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}
	//查找角色
	public RoleVo getRoleByName(String name) throws DaoException {
		PreparedStatement pstmt=null;
		String sql="select * from role where roleName=?";
		ResultSet resultSet=null;
		RoleVo roleVo=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			resultSet=pstmt.executeQuery();
			if(resultSet.next()){
				roleVo=new RoleVo();
				roleVo.setRoleCode(resultSet.getString("roleCode"));
				roleVo.setRoleName(resultSet.getString("roleName"));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, resultSet);
		}
		return roleVo;
	}
	
	//角色列表
	public List<Role> listRole()throws DaoException{
		List<Role> list=new ArrayList<Role>();
		PreparedStatement pstmt=null;
		String sql="select * from role";
		ResultSet resultSet=null;
		try {
			pstmt=con.prepareStatement(sql);
			resultSet=pstmt.executeQuery();
			while (resultSet.next()) {
				Role role=new Role();
				role.setRoleName(resultSet.getString("roleName"));
				role.setRoleCode(resultSet.getString("roleCode"));
				list.add(role);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, resultSet);
		}
		return list;
	}
	
	//角色权限
	public List<String> role_perm()throws DaoException{
		List<String> list=new ArrayList<String>();
		PreparedStatement pstmt=null;
		String sql="select * from perminfo";
		ResultSet resultSet=null;
		try {
			pstmt=con.prepareStatement(sql);
			resultSet=pstmt.executeQuery();
			while (resultSet.next()) {
				String perm=resultSet.getString("perName");
				list.add(perm);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, resultSet);
		}
		return list;
	}
	
	//获取上级部门并转化为nodes
	public ArrayList<Nodes> getNodes(int pId){
		ArrayList<Nodes> allPerm = null;
		
		String sql = "select * from perminfo where leaderPermissionId=?";
		Object[] params = {pId};
		
		allPerm = this.getNodesList(sql,params);
		
		return allPerm;
	}
	
	public ArrayList<Nodes> getNodesList(String sql,Object[] params){
		ArrayList<Nodes> nodesList = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			Connection con = DBUtil.getConnection();
			if(sql != null && !sql.equals("")){
				if(params == null)
					params = new Object[0];
				if(con != null){
						pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
						for(int i = 0 ; i < params.length ; i++){
							pstmt.setObject(i+1,params[i]);
						}
						pstmt.execute();
				}
			}
			resultSet = pstmt.getResultSet();
			if(resultSet != null){
				nodesList = new ArrayList<Nodes>();
				while(resultSet.next()){
					Nodes temp=new Nodes();
					temp.setId((resultSet.getInt(1)));
					resultSet.getString(2);
					temp.setText(resultSet.getString("perName"));
					temp.setpId(resultSet.getInt("leaderPermissionId"));
					temp.setNodes();
					
					nodesList.add(temp);
				}
			}
			
			DBUtil.close(con, pstmt, resultSet);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodesList;
	}
	
	//根据ID查找角色
	public Role getRoleById(int id) throws DaoException {
		PreparedStatement pstmt=null;
		String sql="select * from role where roleId=?";
		ResultSet resultSet=null;
		Role role=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, id);
			resultSet=pstmt.executeQuery();
			if(resultSet.next()){
				role=new Role();
				role.setRoleCode(resultSet.getString("roleCode"));
				role.setRoleName(resultSet.getString("roleName"));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, resultSet);
		}
		return role;
	}
	
	//写入角色权限
	public void addRolePerm(int roleId,int perId) throws DaoException {
		PreparedStatement pstmt=null;
		String sql="insert into role_permission(perId,roleId) values(?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, perId);
			pstmt.setInt(2, roleId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}
	
	//清空角色权限
	public void deleteRolePerm(int roleId)throws DaoException{
		PreparedStatement pstmt=null;
		String sql="delete from role_permission where roleId=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, roleId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt,null);
		}
	}
	
   //根据姓名查找角色Id
	public int getIdByName(String name)throws DaoException{
		int id=0;
		PreparedStatement pstmt=null;
		String sql="select roleId from role where roleName=?";
		ResultSet resultSet=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			resultSet=pstmt.executeQuery();
			if(resultSet.next())
				id=resultSet.getInt(1);
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, resultSet);
		}
		return id;
		
		
	}
	@Override
	public ArrayList<Integer> getAllPerm(int roleId) throws DaoException {
		ArrayList<Integer> permArrayList=new ArrayList<Integer>();
		PreparedStatement pstmt=null;
		String sql="select perId from role_permission where roleId=?";
		ResultSet resultSet=null;
		int i;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, roleId);
			resultSet=pstmt.executeQuery();
			while (resultSet.next()) {
				i=resultSet.getInt(1);
				permArrayList.add(new Integer(i));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt,resultSet);
		}
		return permArrayList;
	}
	@Override
	public List<Integer> hascheck(String name)throws DaoException {
		PreparedStatement pstmt=null;
		String sql="select perId from role_permission where roleId=?";
		ResultSet resultSet=null;
	    List<Integer> checklist=new ArrayList<Integer>();
		int i=0;
		try {
			int id=getIdByName(name);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, id);
			resultSet=pstmt.executeQuery();
			while(resultSet.next()){
				checklist.add(new Integer(resultSet.getInt(1)));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return checklist;
		
	}
	
}
