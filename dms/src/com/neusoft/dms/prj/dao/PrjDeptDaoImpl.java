package com.neusoft.dms.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.prj.domain.PrjDept;
import com.neusoft.dms.prj.domain.PrjDeptVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class PrjDeptDaoImpl implements PrjDeptDao {
	private Connection con;
	
	public PrjDeptDaoImpl(Connection con){
		this.con = con;
	}

	//添加项目与部门的联系
	public void addPrjDept(int prjID,int deptID)throws DaoException{
		PreparedStatement pstmt = null;
		String sql = "insert into PRJ_DEPT(PRJID,DEPTID) value(?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			pstmt.setInt(2, deptID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}
	
	//根据id删除项目与部门的联系
	public boolean delPrjDept(int prjID) throws DaoException{
		boolean isSuccess = false;
		PreparedStatement pstmt = null;
		String sql = "delete from PRJ_DEPT where PRJID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			pstmt.executeUpdate();
			isSuccess = true;
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt,null);
		}
		return isSuccess;
	}
	
	//根据项目id查找项目部门联系信息
	public List<PrjDeptVo> getPrjDeptByPID(int pID) throws DaoException{
		List<PrjDeptVo> pds = new ArrayList<PrjDeptVo>();
		PrjDeptVo pdVo = new PrjDeptVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select PDID,PRJID,DEPT.DEPTID,DEPTNAME from PRJ_DEPT,DEPT where PRJID=? AND DEPT.DEPTID=PRJ_DEPT.DEPTID ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				pdVo = new PrjDeptVo();
				pdVo.setPdID(rs.getInt("PDID"));
				pdVo.setDeptID(rs.getInt("DEPTID"));
				pdVo.setDeptName(rs.getString("DEPTNAME"));
				pdVo.setProjID(rs.getInt("PRJID"));
				pds.add(pdVo);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return pds;
	}

}
