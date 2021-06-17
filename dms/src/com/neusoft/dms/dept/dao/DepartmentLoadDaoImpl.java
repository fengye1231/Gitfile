package com.neusoft.dms.dept.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.dms.dept.domain.DepartmentLoadVo;
import com.neusoft.dms.dept.domain.DepartmentVo;
import com.neusoft.dms.dept.domain.EmployeeProject;
import com.neusoft.dms.dept.domain.EmployeeProjectLoad;
import com.neusoft.dms.dept.domain.EmployeeProjectLoadDo;
import com.neusoft.dms.dept.domain.ProjectLoad;
import com.neusoft.dms.dept.domain.ProjectPeriod;
import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class DepartmentLoadDaoImpl implements DepartmentLoadDao {
	
	private Connection con;
	
	public DepartmentLoadDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public Page getEmployeeProjectLoads(int deptId, Date startDate, Date endDate,
			int pageNum, int pageSize) throws DaoException {

		List<EmployeeProjectLoad> loads = new ArrayList<EmployeeProjectLoad>();
		List<EmployeeProjectLoadDo> loadDos = new ArrayList<EmployeeProjectLoadDo>();
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			// 总条数为部门内人员数
			String pageSql = "SELECT COUNT(*) FROM employee WHERE deptId = ? ";
			
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setInt(1, deptId);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			
			if (page.getTotalNum() > 0) {
				// 列出部门内的所有人员，不管其有没有参加项目
				// 列出部门在此时期内负责的所有项目，不管其有没有日报
				String sql = "SELECT empId, empName, prjId, prjName, SUM(totalWorkload), SUM(overTimeLoad) "
					+ "FROM (SELECT empId, empName "
					+ "		FROM employee  "
					+ "		WHERE deptId = ? "
					+ "		) t1 LEFT JOIN ( "
					+ "		SELECT * "
					+ "		FROM prj JOIN prj_emp USING(prjId) "
					+ "		WHERE ((startDate BETWEEN ? AND ?) OR (? BETWEEN startDate AND endDate)) "
					+ "		) t2 USING(empId) LEFT JOIN ( "
					+ "		SELECT * "
					+ "		FROM daily  "
					+ "		WHERE  status = '已通过' AND submitDate BETWEEN ? AND ? "
					+ "		) t3 USING(empId, prjId)  "
					+ "GROUP BY empId, prjId "
					+ "LIMIT ?, ? ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, deptId);
				pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
				pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(5, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(6, new java.sql.Date(endDate.getTime()));
				pstmt.setInt(7, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(8, page.getPageSize());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					loadDos.add(new EmployeeProjectLoadDo(
							rs.getInt(1), rs.getString(2), 
							rs.getInt(3), rs.getString(4), 
							rs.getFloat(5), rs.getFloat(6)));
				}
				constructEmployeeProjectLoads(loadDos, loads);
			}
			page.setList(loads);
			return page;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public Load getProjectLoadSummary(int deptId, Date startDate, Date endDate)
			throws DaoException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Load load = new Load(0, 0);
			String sql = "SELECT SUM(totalWorkload), SUM(overTimeLoad) "
				+ "FROM daily JOIN employee USING(empId) "
				+ "WHERE deptId = ? AND status = '已通过' AND submitDate BETWEEN ? AND ? ";
				
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptId);
			pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				load.setLoad(rs.getFloat(1));
				load.setExtraLoad(rs.getFloat(2));
			}
			return load;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public Page getProjectLoads(int deptId, Date startDate, Date endDate,
			int pageNum, int pageSize) throws DaoException {
		
		List<ProjectLoad> loads = new ArrayList<ProjectLoad>();
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			// 总条数为在查询时间段内的部门项目数
			String pageSql = "SELECT COUNT(*) "
				+ "FROM prj_dept JOIN prj USING(prjId) "
				+ "WHERE deptId = ? "
				+ "		AND ((prj.startDate BETWEEN ? AND ?) OR (? BETWEEN prj.startDate AND prj.endDate)) ";
			
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setInt(1, deptId);
			pagePstmt.setDate(2, new java.sql.Date(startDate.getTime()));
			pagePstmt.setDate(3, new java.sql.Date(endDate.getTime()));
			pagePstmt.setDate(4, new java.sql.Date(startDate.getTime()));
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			
			if (page.getTotalNum() > 0) {
				String sql = "SELECT prjId, prjName, SUM(totalWorkload), SUM(overTimeLoad) "
					+ "FROM ( "
					+ "		SELECT * "
					+ "		FROM prj JOIN prj_dept USING(prjId) "
					+ "		WHERE deptId = ? "
					+ "				AND ((prj.startDate BETWEEN ? AND ?) OR (? BETWEEN prj.startDate AND prj.endDate)) "
					+ "		) t1 LEFT JOIN ( "
					+ "		SELECT * "
					+ "		FROM daily JOIN employee USING(empId) "
					+ "		WHERE status = '已通过' AND deptId = ? AND submitDate BETWEEN ? AND ? "
					+ "		) t2 USING(prjId) "
					+ "GROUP BY prjId "
					+ "LIMIT ?, ? ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, deptId);
				pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
				pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
				pstmt.setInt(5, deptId);
				pstmt.setDate(6, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(7, new java.sql.Date(endDate.getTime()));
				pstmt.setInt(8, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(9, page.getPageSize());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					loads.add(new ProjectLoad(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4)));
				}
			}
			page.setList(loads);
			return page;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public List<ProjectLoad> getProjectLoads(int deptId, Date startDate, Date endDate)
			throws DaoException {
		
		List<ProjectLoad> loads = new ArrayList<ProjectLoad>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			
			String sql = "SELECT prjId, prjName, SUM(totalWorkload), SUM(overTimeLoad) "
				+ "FROM ( "
				+ "		SELECT * "
				+ "		FROM prj JOIN prj_dept USING(prjId) "
				+ "		WHERE deptId = ? "
				+ "				AND ((prj.startDate BETWEEN ? AND ?) OR (? BETWEEN prj.startDate AND prj.endDate)) "
				+ "		) t1 LEFT JOIN ( "
				+ "		SELECT * "
				+ "		FROM daily JOIN employee USING(empId) "
				+ "		WHERE status = '已通过' AND submitDate BETWEEN ? AND ? "
				+ "		) t2 USING(prjId) "
				+ "GROUP BY prjId ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptId);
			pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
			pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(5, new java.sql.Date(startDate.getTime()));				
			pstmt.setDate(6, new java.sql.Date(endDate.getTime()));
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				loads.add(new ProjectLoad(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4)));
			}
			return loads;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public DepartmentLoadVo getSubDepartmentLoadSummary(int deptId,
			Date startDate, Date endDate) throws DaoException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			DepartmentLoadVo load = new DepartmentLoadVo();
			String sql = "SELECT num, sumLoad, sumExtarload, sumUnacpLoad "
				+ "FROM ( "
				+ "		SELECT COUNT(*) num, SUM(totalWorkload) sumLoad, SUM(overTimeLoad) sumExtarload "
				+ "		FROM daily JOIN employee USING(empId) "
				+ "		WHERE status = '已通过' AND submitDate BETWEEN ? AND ? "
				+ "				AND deptId IN (SELECT deptId FROM dept WHERE seniorDeptId = ?) "
				+ "		) t1, ( "
				+ "		SELECT SUM(totalWorkload) sumUnacpLoad "
				+ "		FROM daily JOIN employee USING(empId) "
				+ "		WHERE status <> '已通过' AND submitDate BETWEEN ? AND ? "
				+ "				AND deptId IN (SELECT deptId FROM dept WHERE seniorDeptId = ?) "
				+ "		) t2 ";
				
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(2, new java.sql.Date(endDate.getTime()));			
			pstmt.setInt(3, deptId);
			pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(5, new java.sql.Date(endDate.getTime()));			
			pstmt.setInt(6, deptId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				load.setDailyNum(rs.getInt(1));
				load.setLoad(rs.getFloat(2));
				load.setExtraLoad(rs.getFloat(3));
				load.setUnacceptedLoad(rs.getFloat(4));
			}
			return load;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public Page getSubDepartmentLoads(int deptId, Date startDate, Date endDate,
			int pageNum, int pageSize) throws DaoException {
		
		List<DepartmentLoadVo> loads = new ArrayList<DepartmentLoadVo>();
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			// 总条数为子部门数
			String pageSql = "SELECT COUNT(*) FROM dept WHERE seniorDeptId = ? ";
			
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setInt(1, deptId);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			
			if (page.getTotalNum() > 0) {
				String sql = "SELECT t1.deptId, t1.deptCode, t1.deptName, num, sumLoad, sumExtarload, sumUnacpLoad "
					+ "FROM ( "
					+ "		SELECT * "
					+ "		FROM (SELECT COUNT(*) num, SUM(totalWorkload) sumLoad, SUM(overTimeLoad) sumExtarload, deptId  "
					+ "				FROM daily JOIN employee USING(empId)  "
					+ "				WHERE status = '已通过' AND submitDate BETWEEN ? AND ? "
					+ "				GROUP BY deptId	"
					+ "				) tt1 RIGHT JOIN dept USING(deptId)  "
					+ "		WHERE seniorDeptId = ? "
					+ "		) t1 LEFT JOIN ( "
					+ "		SELECT SUM(totalWorkload) sumUnacpLoad, deptId "
					+ "		FROM daily JOIN employee USING(empId) RIGHT JOIN dept USING(deptId)  "
					+ "		WHERE status <> '已通过' AND submitDate BETWEEN ? AND ?  "
					+ "				AND deptId IN (SELECT deptId FROM dept WHERE seniorDeptId = ?) "
					+ "		GROUP BY deptId "
					+ "		) t2 USING(deptId) "
					+ "ORDER BY deptId "
					+ "LIMIT ?, ? ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(2, new java.sql.Date(endDate.getTime()));			
				pstmt.setInt(3, deptId);
				pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(5, new java.sql.Date(endDate.getTime()));			
				pstmt.setInt(6, deptId);
				pstmt.setInt(7, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(8, page.getPageSize());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					loads.add(new DepartmentLoadVo(
							rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getInt(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7)));
				}
			}
			page.setList(loads);
			return page;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public List<DepartmentLoadVo> getSubDepartmentLoads(int deptId,
			Date startDate, Date endDate) throws DaoException {
		
		List<DepartmentLoadVo> loads = new ArrayList<DepartmentLoadVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT t1.deptId, t1.deptCode, t1.deptName, num, sumLoad, sumExtarload, sumUnacpLoad "
				+ "FROM ( "
				+ "		SELECT * "
				+ "		FROM (SELECT COUNT(*) num, SUM(totalWorkload) sumLoad, SUM(overTimeLoad) sumExtarload, deptId  "
				+ "				FROM daily JOIN employee USING(empId)  "
				+ "				WHERE status = '已通过' AND submitDate BETWEEN ? AND ? "
				+ "				GROUP BY deptId	"
				+ "				) tt1 RIGHT JOIN dept USING(deptId)  "
				+ "		WHERE seniorDeptId = ? "
				+ "		) t1 LEFT JOIN ( "
				+ "		SELECT SUM(totalWorkload) sumUnacpLoad, deptId "
				+ "		FROM daily JOIN employee USING(empId) RIGHT JOIN dept USING(deptId)  "
				+ "		WHERE status <> '已通过' AND submitDate BETWEEN ? AND ?  "
				+ "				AND deptId IN (SELECT deptId FROM dept WHERE seniorDeptId = ?) "
				+ "		GROUP BY deptId "
				+ "		) t2 USING(deptId) "
				+ "ORDER BY deptId ";
				
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(2, new java.sql.Date(endDate.getTime()));			
			pstmt.setInt(3, deptId);
			pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(5, new java.sql.Date(endDate.getTime()));			
			pstmt.setInt(6, deptId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				loads.add(new DepartmentLoadVo(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7)));
			}
			return loads;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public List<DepartmentVo> getDepartments() throws DaoException {
		List<DepartmentVo> departments = new ArrayList<DepartmentVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT deptId, deptName FROM dept";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				departments.add(new DepartmentVo(rs.getInt(1), rs.getString(2)));
			}
			return departments;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
	
	@Override
	public List<DepartmentVo> getLowestLevelDepartments() throws DaoException {
		List<DepartmentVo> departments = new ArrayList<DepartmentVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT deptId, deptName "
				+ "FROM dept t "
				+ "WHERE NOT EXISTS (SELECT * FROM dept WHERE seniorDeptId = t.deptId)";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				departments.add(new DepartmentVo(rs.getInt(1), rs.getString(2)));
			}
			return departments;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public List<DepartmentVo> getLowestSecondLevelDepartments() throws DaoException {
		List<DepartmentVo> departments = new ArrayList<DepartmentVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT DISTINCT deptId, deptName "
				+ "FROM (SELECT seniorDeptId "
				+ "		FROM dept t "
				+ "		WHERE NOT EXISTS (SELECT * FROM dept WHERE seniorDeptId = t.deptId) "
				+ "		) t1 JOIN dept ON t1.seniorDeptId = deptId ";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				departments.add(new DepartmentVo(rs.getInt(1), rs.getString(2)));
			}
			return departments;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public List<EmployeeProject> getEmployeeProject(int deptId, Date startDate,
			Date endDate) throws DaoException {
		
		List<EmployeeProject> employeeProjects = new ArrayList<EmployeeProject>();
		Map<Integer, List<Integer>> container = new HashMap<Integer, List<Integer>>();
		String subDeptIdsTableName = "subdeptIds";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			generateSubDeptIdsTable(deptId, subDeptIdsTableName);
			
			String sql = "SELECT empId, prjId FROM " + subDeptIdsTableName + " t1 JOIN employee t2 on t1.id = t2.deptId "
				+ "LEFT JOIN prj_emp USING(empId) LEFT JOIN ( "
				+ "		SELECT 	* FROM prj WHERE ((startDate BETWEEN ? AND ?) OR (? BETWEEN startDate AND endDate)) ) t3 "
				+ "USING(prjId)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(2, new java.sql.Date(endDate.getTime()));
			pstmt.setDate(3, new java.sql.Date(startDate.getTime()));
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Integer employeeId = rs.getInt(1);
				Integer projectId = rs.getInt(2);
				if (!container.containsKey(employeeId)) {
					container.put(employeeId, new ArrayList<Integer>());
				}
				container.get(employeeId).add(projectId);
			}
			for( Map.Entry<Integer, List<Integer>> entry : container.entrySet()) {    
				employeeProjects.add(new EmployeeProject(entry.getKey(), entry.getValue()));
			}
			return employeeProjects;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
	
	@Override
	public List<ProjectPeriod> getProjectPeriod(int deptId, Date startDate, Date endDate)
			throws DaoException {
		
		List<ProjectPeriod> periods = new ArrayList<ProjectPeriod>();
		String subDeptIdsTableName = "subdeptIds";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			generateSubDeptIdsTable(deptId, subDeptIdsTableName);
			
			String sql = "SELECT DISTINCT prjId, startDate, endDate "
				+ "FROM prj_dept JOIN prj USING(prjId) "
				+ "WHERE ((startDate BETWEEN ? AND ?) OR (? BETWEEN startDate AND endDate)) "
				+ "		AND EXISTS (SELECT * FROM " + subDeptIdsTableName + " WHERE id = deptId) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(2, new java.sql.Date(endDate.getTime()));
			pstmt.setDate(3, new java.sql.Date(startDate.getTime()));
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				periods.add(new ProjectPeriod(
						rs.getInt(1), new Date(rs.getDate(2).getTime()), new Date(rs.getDate(3).getTime())));
			}
			return periods;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
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
	
	/**
	 * 构造EmployeeProjectLoad
	 * @param loadDos
	 * @param loads
	 */
	private void constructEmployeeProjectLoads(List<EmployeeProjectLoadDo> loadDos, List<EmployeeProjectLoad> loads) {
		Map<Integer, EmployeeProjectLoad> container = new HashMap<Integer, EmployeeProjectLoad>();
		for (EmployeeProjectLoadDo loadDo : loadDos) {
			if (!container.containsKey(loadDo.getEmployeeId())) {
				EmployeeProjectLoad epload = new EmployeeProjectLoad(
						loadDo.getEmployeeId(), loadDo.getEmployeeName(), new ArrayList<ProjectLoad>());
				container.put(loadDo.getEmployeeId(), epload);
			}
			ProjectLoad pLoad = new ProjectLoad(
					loadDo.getProjectId(), loadDo.getProjectName(), loadDo.getLoad(), loadDo.getExtraLoad());
			container.get(loadDo.getEmployeeId()).getProjectLoads().add(pLoad);			
		}
		for( Map.Entry<Integer, EmployeeProjectLoad> entry : container.entrySet()) {    
			loads.add(entry.getValue());
		}
	}

}
