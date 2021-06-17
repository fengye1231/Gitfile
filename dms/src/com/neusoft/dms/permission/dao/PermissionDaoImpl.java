package com.neusoft.dms.permission.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.permission.domain.Permission;
import com.neusoft.dms.permission.domain.PermissionVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class PermissionDaoImpl implements PermissionDao {

	private Connection con;
	public PermissionDaoImpl(Connection con){
		this.con = con;
	}
	@Override
	public void addPermission(Permission permission) throws DaoException {
		PreparedStatement pstmt = null;
		try{
			System.out.println("perleadid:"+permission.getLeaderPermissionId());
			if(permission.getLeaderPermissionId()!=0){
				String sql = "insert into perminfo(perName,perPath,leaderPermissionId) values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, permission.getPerName());
				pstmt.setString(2, permission.getPerPath());
				pstmt.setInt(3, permission.getLeaderPermissionId());
				pstmt.executeUpdate();
			}else{
				String sql = "insert into perminfo(perName,perPath) values(?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, permission.getPerName());
				pstmt.setString(2, permission.getPerPath());
				pstmt.executeUpdate();
			}
			
			
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}

	@Override
	public void delPermission(String perName) throws DaoException {
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from perminfo where perName =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, perName);
			pstmt.execute();
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}

	@Override
	public void updatePermission(Permission permission) throws DaoException {
		PreparedStatement pstmt = null;
		try{
			if(permission.getLeaderPermissionId()!=0){
				String sql = "update perminfo set perName =?,perPath =?,leaderPermissionId =? where perId =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, permission.getPerName());
				pstmt.setString(2, permission.getPerPath());
				pstmt.setInt(3, permission.getLeaderPermissionId());
				pstmt.setInt(4, permission.getPerId());
				pstmt.executeUpdate();
			}else{
				String sql = "update perminfo set perName =?,perPath =?,leaderPermissionId = null where perId =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, permission.getPerName());
				pstmt.setString(2, permission.getPerPath());
				pstmt.setInt(3, permission.getPerId());
				pstmt.executeUpdate();
			}
			
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}
	
	@Override
	public Permission getPermissionByName(String perName) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Permission permission = null;
		
		try{
			String sql = "select * from perminfo where perName =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, perName);
			pstmt.executeQuery();
			
			rs = pstmt.getResultSet();
			if(rs!=null){
				while(rs.next()){
					permission = new Permission();
					permission.setPerId(rs.getInt("perId"));
					permission.setPerName(rs.getString("perName"));
					permission.setPerPath(rs.getString("perPath"));
					permission.setLeaderPermissionId(rs.getInt("leaderPermissionId"));
				}
			}
			
			DBUtil.close(pstmt, rs);
		}catch(SQLException e){
			throw new DaoException(e);
		}
		
		return permission;
	}
	
	public Permission getPermissionById(int perId) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Permission permission = null;
		
		try{
			String sql = "select * from perminfo where perId =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, perId);
			pstmt.executeQuery();
			
			rs = pstmt.getResultSet();
			if(rs!=null){
				while(rs.next()){
					permission = new Permission();
					permission.setPerId(rs.getInt("perId"));
					permission.setPerName(rs.getString("perName"));
					permission.setPerPath(rs.getString("perPath"));
					permission.setLeaderPermissionId(rs.getInt("leaderPermissionId"));
				}
			}
			DBUtil.close(pstmt, rs);
		}catch(SQLException e){
			throw new DaoException(e);
		}
		
		return permission;
	}


	public List<Permission> listAllPermission() throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Permission> perList = new ArrayList<Permission>();
		Permission permission = null;
		try{
			String sql = "select * from perminfo ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){	
				permission = new Permission();
				permission.setPerId(rs.getInt("perId"));
				permission.setPerName(rs.getString("perName"));
				permission.setPerPath(rs.getString("perPath"));
				permission.setLeaderPermissionId(rs.getInt("leaderPermissionId"));
				perList.add(permission);
			}
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return perList;
	}
	
	public Page listPermissionByAll(String perName,String perPath,int pageNum,int pageSize) throws DaoException{
		PreparedStatement pstmt = null,pagePstmt = null;
		ResultSet rs = null;
	    List<PermissionVo> perList = new ArrayList<PermissionVo>();
		PermissionVo permissionVo = null;
		
		try{
			String sql = "select * from perminfo where 1=1";
			String pageSql = "select count(*) from perminfo where 1 = 1";
			if(perName!=null){
				sql = sql + " and perName like '%"+perName+"%'";
				pageSql = pageSql +" and perName like '%"+perName+"%'";
			}
			if(perPath!=null){
				sql = sql + " and perPath like '%"+perPath+"%'";
				pageSql = pageSql +" and perPath like '%"+perPath+"%'";
			}
			pagePstmt = con.prepareStatement(pageSql);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			if (page.getTotalNum() > 0) {
				sql=sql+" limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, (page.getPageNum() - 1)*page.getPageSize());
				pstmt.setInt(2, page.getPageSize());
				rs = pstmt.executeQuery();
				while(rs.next()){
					permissionVo = new PermissionVo();
					permissionVo.setPerId(rs.getInt("perId"));
					permissionVo.setPerName(rs.getString("perName"));
					permissionVo.setPerPath(rs.getString("perPath"));
					if(rs.getInt("leaderPermissionId")==0){
						permissionVo.setLeaderPermissionId(null);
					}else{
					    permissionVo.setLeaderPermissionId(getPermissionById(rs.getInt("leaderPermissionId")).getPerName());
					}
						perList.add(permissionVo);
			}
			}
			page.setList(perList);
			return page;
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
	}

	private Page getPageInfo(PreparedStatement pstmt, int pageNum, int pageSize) throws DaoException {
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pageSize = Math.max(1, pageSize);
				int totalNum = rs.getInt(1);
				int totalPage = (int) Math.ceil((double)totalNum / pageSize);
				pageNum = Math.max(Math.min(totalPage, pageNum), totalNum == 0? 0: 1);
				
				return new Page(null, totalNum, pageNum, totalPage, pageSize);
			}
			throw new DaoException("分页信息获取失败");
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
	
	public List<Permission> listChildPermission(int perId) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Permission> childList = new ArrayList<Permission>();
		Permission permission = null;
		try{
			String sql = "select * from perminfo where leaderPermissionId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, perId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				permission = new Permission();
				permission.setPerId(rs.getInt("perId"));
				permission.setPerName(rs.getString("perName"));
				permission.setPerPath(rs.getString("perPath"));
				permission.setLeaderPermissionId(perId);
				childList.add(permission);
		    }
			}catch(SQLException e){
				throw new DaoException(e);
			}finally{
				DBUtil.close(pstmt, rs);
			}
			return childList;
	}

	public List<Permission> listParentPermission() throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Permission> parentList = new ArrayList<Permission>();
		Permission permission = null;
		try{
			String sql = "select * from perminfo where leaderPermissionId is null";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				permission = new Permission();
				permission.setPerId(rs.getInt("perId"));
				permission.setPerName(rs.getString("perName"));
				permission.setPerPath(rs.getString("perPath"));
				permission.setLeaderPermissionId(rs.getInt("leaderPermissionId"));
				parentList.add(permission);
			}
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return parentList;
	}
	
	public Permission listParentPermission(int perId) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Permission permission = null;
		try{
			String sql = "select * from perminfo where perId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, perId);
			rs = pstmt.executeQuery();
			if(rs.next()){
					permission = new Permission();
					permission.setPerId(rs.getInt("perId"));
					permission.setPerName(rs.getString("perName"));
					permission.setPerPath(rs.getString("perPath"));
					permission.setLeaderPermissionId(rs.getInt("leaderPermissionId"));
			}
		
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return permission;
	}
	
}
