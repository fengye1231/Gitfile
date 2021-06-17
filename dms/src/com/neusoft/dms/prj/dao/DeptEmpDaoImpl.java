package com.neusoft.dms.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.prj.domain.PrjVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class DeptEmpDaoImpl implements DeptEmpDao{
	private Connection con;

	public DeptEmpDaoImpl(Connection con) {
		this.con = con;
	}
	
	//根据部门id查找该部门下没有工作的人员
	public Page getNullEmpByDept(int deptID,int pageNum, int pageSize) throws DaoException{
		List<EmployeeVo> eVos= new ArrayList<EmployeeVo>();
		EmployeeVo eVo = null;
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			String filter = " from (SELECT prj.prjId prjStatus,empId from prj join prj_emp on prj.prjId=prj_emp.prjId) as pe RIGHT JOIN employee on pe.empid=employee.empId where deptId=? and (prjstatus is null or prjstatus ='关闭')";
			String pageSql = " SELECT COUNT(*) " + filter;
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setInt(1, deptID);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			if (page.getTotalNum() > 0) {
				String sql = " select employee.empId,empName,username,remark,sex " + filter + " limit ?,? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, deptID);
				pstmt.setInt(2, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(3, page.getPageSize());
				rs = pstmt.executeQuery();
				while(rs.next()){
					eVo = new EmployeeVo();
					eVo.setEmpId(rs.getInt(1));
					eVo.setUsername(rs.getString(3));
					eVo.setEmpName(rs.getString(2));
					eVo.setSex(rs.getString(5));
					eVo.setRemark(rs.getString(4));
					eVos.add(eVo);
				}
			}
			page.setList(eVos);
			return page;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
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

}
