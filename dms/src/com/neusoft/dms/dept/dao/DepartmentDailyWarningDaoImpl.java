package com.neusoft.dms.dept.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.dms.dept.domain.EmployeeProjectDailyDo;
import com.neusoft.dms.dept.domain.SubmitInfo;
import com.neusoft.dms.dept.domain.UncheckInfo;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class DepartmentDailyWarningDaoImpl implements DepartmentDailyWarningDao {
	
	private Connection con;
	
	public DepartmentDailyWarningDaoImpl(Connection con) {
		this.con = con;
	}


	@Override
	public Page getUncheck(int deptId, Date startDate, Date endDate,
			int pageNum, int pageSize) throws DaoException {
		
		List<UncheckInfo> infos = new ArrayList<UncheckInfo>();
		String subDeptIdsTableName = "subdeptIds";
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			String sqlBody = " FROM prj_dept t1 JOIN " + subDeptIdsTableName + " t2 ON t1.deptId = t2.id  "
					+ "	JOIN dept USING(deptId)  "
					+ "	JOIN ( "
					+ "		SELECT t3.empId, t3.empName, t3.deptId, t4.empId supId, t4.empName supName, t4.email supEmail "
					+ "		FROM employee t3 LEFT JOIN employee t4 ON t3.superiorId = t4.empId "
					+ "	) t5 USING(deptId) "
					+ "	JOIN daily USING(empId, prjId) "
					+ " WHERE submitDate BETWEEN ? AND ? AND daily.status = '未审核' ";
			
			generateSubDeptIdsTable(deptId, subDeptIdsTableName);
			String pageSql = "SELECT COUNT(*) " + sqlBody;
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setDate(1, new java.sql.Date(startDate.getTime()));
			pagePstmt.setDate(2, new java.sql.Date(endDate.getTime()));
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			
			if (page.getTotalNum() > 0) {
				String sql = "SELECT deptId, deptName, empId, empName, supId, supName, supEmail, submitDate "
					+ sqlBody
					+ "ORDER BY submitDate DESC "
					+ "LIMIT ?, ? ";

				pstmt = con.prepareStatement(sql);
				pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(2, new java.sql.Date(endDate.getTime()));			
				pstmt.setInt(3, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(4, page.getPageSize());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					infos.add(new UncheckInfo(
							rs.getInt(1), rs.getString(2), 
							rs.getInt(3), rs.getString(4), 
							rs.getInt(5), rs.getString(6), 
							rs.getString(7), new Date(rs.getDate(8).getTime())));
				}
			}
			page.setList(infos);
			return page;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public Page getSubmitInfo(int deptId, Date startDate, Date endDate,
			int pageNum, int pageSize) throws DaoException {
		
		List<SubmitInfo> infos = new ArrayList<SubmitInfo>();
		String subDeptIdsTableName = "subdeptIds";
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			// 总条数为下属部门人员总数
			generateSubDeptIdsTable(deptId, subDeptIdsTableName);
			String pageSql = "SELECT COUNT(*) FROM employee e JOIN " + subDeptIdsTableName + " s on e.deptId = s.id ";
			pagePstmt = con.prepareStatement(pageSql);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			
			if (page.getTotalNum() > 0) {
				String sql = "SELECT empID, empName, prjId, submitDate "
					+ "FROM employee e JOIN " + subDeptIdsTableName + " s on e.deptId = s.id "
					+ "		LEFT JOIN ( "
					+ " 		SELECT * "
					+ " 		FROM daily "
					+ "			WHERE submitDate BETWEEN ? AND ? "	
					+ " 	) t USING(empId) "
					+ "LIMIT ?, ? ";

				pstmt = con.prepareStatement(sql);
				pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(2, new java.sql.Date(endDate.getTime()));			
				pstmt.setInt(3, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(4, page.getPageSize());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Date date = (rs.getDate(4) == null? null: new Date(rs.getDate(4).getTime()));
					infos.add(new SubmitInfo(rs.getInt(1), rs.getString(2), rs.getInt(3), date));
				}
			}
			page.setList(infos);
			return page;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public boolean hasWarning(int empId, Type type) throws DaoException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT COUNT(*) FROM warning WHERE empId = ? AND type = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empId);
			pstmt.setString(2, type.toString());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return (rs.getInt(1) > 0);
			}
			return false;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public void clearWarning(int empId, Type type) throws DaoException {
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM warning WHERE empId = ? AND type = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empId);
			pstmt.setString(2, type.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt);
		}
	}
	
	@Override
	public void addUnsubmitWarning(List<Integer> empIds) throws DaoException {
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT IGNORE INTO warning(empId, type) VALUES(?, '" + Type.UNSUBMIT + "')";
			pstmt = con.prepareStatement(sql);
			for (int empId : empIds) {
				pstmt.setInt(1, empId);
				pstmt.addBatch();
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt);
		}
	}
	
	@Override
	public void addUncheckWarning(int deptId) throws DaoException {
		String subDeptIdsTableName = "subdeptIds";
		PreparedStatement pstmt = null;
		try {
			generateSubDeptIdsTable(deptId, subDeptIdsTableName);
			String sql = "INSERT IGNORE INTO warning(empId, type) "
				+ "SELECT supId, '" + Type.UNCHECK + "' "
				+ "FROM prj_dept t1 JOIN " + subDeptIdsTableName + " t2 ON t1.deptId = t2.id  "
				+ "		JOIN dept USING(deptId)  "
				+ "		JOIN ( "
				+ "				SELECT t3.empId, t3.deptId, t4.empId supId "
				+ "				FROM employee t3 JOIN employee t4 ON t3.superiorId = t4.empId "
				+ "		) t5 USING(deptId) "
				+ "		JOIN daily USING(empId, prjId) "
				+ "WHERE daily.status = '未审核' ";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt);
		}
	}
	

	@Override
	public List<EmployeeProjectDailyDo> getSubmitCount(int deptId) throws DaoException {
		
		List<EmployeeProjectDailyDo> epds = new ArrayList<EmployeeProjectDailyDo>();
		String subDeptIdsTableName = "subdeptIds";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			generateSubDeptIdsTable(deptId, subDeptIdsTableName);
			String sql = "SELECT empId, prjId, COUNT(DISTINCT submitDate) "
				+ "FROM " + subDeptIdsTableName + " t1 JOIN employee t2 ON t1.id = t2.deptId "
				+ "		LEFT JOIN prj_emp USING(empId) "
				+ "		LEFT JOIN ( "
				+ "				SELECT * "
				+ "				FROM daily  "
				+ "				WHERE DAYOFWEEK(submitDate) <> 1 AND DAYOFWEEK(submitDate) <> 7 "
				+ "		) t3  "
				+ "USING(empId, prjId) "
				+ "GROUP BY empId, prjId ";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				epds.add(new EmployeeProjectDailyDo(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			return epds;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt);
		}
	}
	
	/**
	 * 根据数据库里的总条数获取分页信息
	 * @param pstmt
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
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
	
	/**
	 * 在数据库内生成指定部门的所有子部门ID临时表（包括自身）
	 * @param deptId
	 * @param tableName
	 * @throws DaoException
	 */
	private void generateSubDeptIdsTable(int deptId, String tableName) throws DaoException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("CALL getSubDeptIdsWithSelf(?, '" + tableName + "') ");
			pstmt.setInt(1, deptId);
			pstmt.execute();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}

}
