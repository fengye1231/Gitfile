package com.neusoft.dms.prj.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.ARRAY;

import com.neusoft.dms.employee.dao.EmployeeDao;
import com.neusoft.dms.employee.dao.EmployeeDaoImpl;
import com.neusoft.dms.prj.domain.PrjEmp;
import com.neusoft.dms.prj.domain.PrjEmpVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class PrjEmpDaoImpl implements PrjEmpDao {
	private Connection con;

	public PrjEmpDaoImpl(Connection con2) {
		super();
		this.con = con2;
	}

	//添加人员到项目中
	public void addPrjEmp(PrjEmp pe) throws DaoException{
		PreparedStatement pstmt = null;
		String sql = "insert into PRJ_EMP(PRJID,EMPID,PRID) value(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pe.getPrjID());
			pstmt.setInt(2, pe.geteID());
			pstmt.setInt(3, pe.getprID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}
		
	//从项目中删除人员及角色
	public void delPrjEmp(int prjID,int prID) throws DaoException{
		PreparedStatement pstmt = null;
		String sql = "delete from PRJ_EMP where PRJID=? and PRID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			pstmt.setInt(2, prID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}
		
	//更改角色人员
	public void updatePrjEmp(PrjEmp pe) throws DaoException{
		PreparedStatement pstmt = null;
		String sql = "update PRJ_EMP set PRJID=?,EMPID=?, PRID=?";
		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
		
	//根据项目ID获取该项目人员
	public List<PrjEmpVo> getPrjEmpByPrjID(int prjID) throws DaoException{
		List<PrjEmpVo> pes = new ArrayList<PrjEmpVo>();
		PrjEmpVo pe = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT peid,prjId,pe.empId,empName,prj_resp.prId,resname FROM (select peid, prjid, employee.empid,empName,prId from prj_emp join employee on prj_emp.empId = employee.empId where prjid=?) as pe right join prj_resp on pe.prId = prj_resp.prId";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				pe = new PrjEmpVo();
				pe.setpEID(rs.getInt("PEID"));
				pe.setPrjID(rs.getInt("PRJID"));
				pe.seteID(rs.getInt("EMPID"));
				pe.setEmpName(rs.getString("EMPNAME"));
				pe.setpRID(rs.getInt("PRID"));
				pe.setResName(rs.getString("RESNAME"));
				pes.add(pe);
			}
			for(PrjEmpVo p:pes){
				System.out.println(p.geteID());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
		return pes;
	}
	
	//获取人员参与的所有项目信息
	public List<PrjEmpVo> getAllPrjByEmpID(int empID) throws DaoException{
		List<PrjEmpVo> peVos = new ArrayList<PrjEmpVo>();
		PrjEmpVo peVo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select peId,prj.prjId,prjName,prjStatus,employee.empid,empname,prj_resp.prId,resname "
					+ " from PRJ_EMP,prj,employee,prj_resp "
					+ " where prj_emp.EMPID=? and prj.prjid=prj_emp.prjId " 
					+ " and employee.empId=prj_emp.empId and prj_resp.prid=prj_emp.prId ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				peVo = new PrjEmpVo();
				peVo.setpEID(rs.getInt("PEID"));
				peVo.setPrjID(rs.getInt("PRJID"));
				peVo.setStatus(rs.getString("PRJSTATUS"));
				peVo.setPrjName(rs.getString("PRJNAME"));
				peVo.seteID(rs.getInt("EMPID"));
				peVo.setEmpName(rs.getString("EMPNAME"));
				peVo.setpRID(rs.getInt("PRID"));
				peVo.setResName(rs.getString("RESNAME"));
				peVos.add(peVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return peVos;
	}
	
	//获取该人员目前参与的项目信息
	public PrjEmpVo getPrjByEmpID(int empID) throws DaoException{
		PrjEmpVo peVo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select peId,prj.prjId,prjName,prjStatus,employee.empid,empname,prj_resp.prId,resname "
					+ " from PRJ_EMP,prj,employee,prj_resp "
					+ " where prj_emp.EMPID=? and prj.prjid=prj_emp.prjId and employee.empId=prj_emp.empId" 
					+ " and prj_resp.prid=prj_emp.prId and prjStatus='运行中'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				peVo = new PrjEmpVo();
				peVo.setpEID(rs.getInt("PEID"));
				peVo.setPrjID(rs.getInt("PRJID"));
				peVo.setStatus(rs.getString("PRJSTATUS"));
				peVo.setPrjName(rs.getString("PRJNAME"));
				peVo.seteID(rs.getInt("EMPID"));
				peVo.setEmpName(rs.getString("EMPNAME"));
				peVo.setpRID(rs.getInt("PRID"));
				peVo.setResName(rs.getString("RESNAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return peVo;
	}


}
