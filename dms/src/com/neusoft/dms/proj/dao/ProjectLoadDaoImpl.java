package com.neusoft.dms.proj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.proj.domain.DepartmentLoad;
import com.neusoft.dms.proj.domain.DepartmentPrpLoad;
import com.neusoft.dms.proj.domain.DepartmentPrpLoadDo;
import com.neusoft.dms.proj.domain.ProjectVo;
import com.neusoft.dms.proj.domain.PrpLoad;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;


public class ProjectLoadDaoImpl implements ProjectLoadDao {

	private Connection con;
	
	public ProjectLoadDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public Load getDepartmentLoadSummary(int projectId, Date startDate, Date endDate) throws DaoException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT Sum(daily.totalWorkload), Sum(daily.overTimeLoad) "
					   + "FROM daily "
					   + "WHERE status = '已通过' AND prjId = ? AND submitDate BETWEEN ? AND ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectId);
			pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Load(rs.getFloat(1), rs.getFloat(2));
			}
			return new Load(0, 0);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public Page getDepartmentLoads(int projectId, Date startDate, Date endDate,
			int pageNum, int pageSize) throws DaoException {

		List<DepartmentLoad> loads = new ArrayList<DepartmentLoad>();
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			// 总条数就是所有相关部门数
			String pageSql = "SELECT COUNT(*) FROM prj_dept WHERE prjId = ?";
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setInt(1, projectId);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			
			if (page.getTotalNum() > 0) {
				//daily表中不一定有全部的相关部门，因为有的部门可能还没有有效日报
				//需要在prj_dept表中获取所有相关部门，在daily表中获取工作量
				String sql = "SELECT t1.deptId, t1.deptName, SUM(totalWorkload), SUM(overTimeLoad) "
					+ "FROM ( "
					+ "		SELECT dept.deptId, dept.deptName "
					+ " 		FROM dept JOIN prj_dept USING(deptId) "
					+ " 		WHERE prjId = ? "
					+ " 		) t1 "
					+ " 	LEFT JOIN ( "
					+ "		SELECT totalWorkload, overTimeLoad, deptId "
					+ " 		FROM daily JOIN employee USING(empId) "
					+ " 		WHERE status = '已通过' AND prjId = ? AND submitDate BETWEEN ? AND ? "
					+ " 		ORDER BY submitDate ASC "
					+ " 		) t2 "
					+ " 	USING(deptId) "
					+ " GROUP BY deptId "
					+ "LIMIT ?, ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, projectId);
				pstmt.setInt(2, projectId);				
				pstmt.setDate(3, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(4, new java.sql.Date(endDate.getTime()));
				pstmt.setInt(5, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(6, page.getPageSize());
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					loads.add(new DepartmentLoad(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4)));
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
	public Load getPrpLoadSummary(int projectId, Date startDate,
			Date endDate) throws DaoException {
		// 与部门工作总量相同
		return getDepartmentLoadSummary(projectId, startDate, endDate);
	}

	@Override
	public Page getPrpLoads(int projectId, Date startDate, Date endDate,
			int pageNum, int pageSize) throws DaoException {
		
		List<PrpLoad> PrpLoads = new ArrayList<PrpLoad>();
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			// 总条数就是所有相关PRP数
			String pageSql = "SELECT COUNT(*) FROM prj_prp WHERE prjId = ?";
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setInt(1, projectId);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			
			if (page.getTotalNum() > 0) {
				// daily表中不一定有全部的相关PRP，因为有的PRP可能还没有有效日报
				// 需要在prj_prp表中获取所有相关PRP，在daily表中获取工作量
				// 因为PRP阶段的时间没有明确定义，所以列出此项目所有的PRP
				String sql = "SELECT t2.prpId, prpName, SUM(totalWorkload), SUM(overTimeLoad) "
					+ "FROM (SELECT totalWorkload, overTimeLoad, prpId "
						+ "FROM daily "
						+ "WHERE status = '已通过' AND prjId = ? AND submitDate BETWEEN ? AND ? "
						+ "ORDER BY submitDate ASC) t1 "
						+ "RIGHT JOIN (SELECT prp.prpId, prp.prpName "
						+ "FROM prp, prj_prp "
						+ "WHERE prp.prpId = prj_prp.prpId AND prjId = ? "
						+ ") t2 "
						+ "USING(prpId) "
					+ "GROUP BY t2.prpId "
					+ "LIMIT ?, ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, projectId);
				pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
				pstmt.setInt(4, projectId);
				pstmt.setInt(5, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(6, page.getPageSize());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					PrpLoads.add(new PrpLoad(
							rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4)));
				}				
			}
			page.setList(PrpLoads);
			return page;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public Page getDepartmentPrpLoads(int projectId, Date startDate,
			Date endDate, int pageNum, int pageSize) throws DaoException {

		List<DepartmentPrpLoad> loads = new ArrayList<DepartmentPrpLoad>();
		List<DepartmentPrpLoadDo> loadDos = new ArrayList<DepartmentPrpLoadDo>();
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			// 总条数就是所有相关部门数，因为汇总是以部门作为单个实体
			String pageSql = "SELECT COUNT(*) FROM prj_dept WHERE prjId = ?";
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setInt(1, projectId);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			
			if (page.getTotalNum() > 0) {
				// daily表中不一定有全部的相关部门，因为有的部门可能还没有有效日报
				// 获取[部门,PRP,工作量]元组后再构造DepartmentPrpLoad
				String sql = "SELECT t1.deptId, deptName, prpId, prpName, SUM(totalWorkload), SUM(overTimeLoad) "
					+ "FROM ( "
					+ "		SELECT deptId, deptName , prpId, prpName "
					+ "		FROM (dept JOIN prj_dept USING(deptId)) JOIN (prp JOIN prj_prp USING(prpId)) USING(prjId) "
					+ "		WHERE prjId = ?"
					+ "		) t1 "
					+ "	LEFT JOIN ( "
					+ "		SELECT deptId, prpId, totalWorkload, overTimeLoad "
					+ "		FROM ("
					+ "				SELECT prpId, empId, totalWorkload, overTimeLoad "
					+ "				FROM daily "
					+ "				WHERE status = '已通过' AND prjId = ? AND submitDate BETWEEN ? AND ? "
					+ "				ORDER BY submitDate ASC "
					+ "				) t2 JOIN employee USING(empId) "
					+ "		) t3 "
					+ "	USING(deptId, prpId) "
					+ "GROUP BY deptId, prpId "
					+ "LIMIT ?, ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, projectId);
				pstmt.setInt(2, projectId);		
				pstmt.setDate(3, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(4, new java.sql.Date(endDate.getTime()));
				pstmt.setInt(5, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(6, page.getPageSize());
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					loadDos.add(new DepartmentPrpLoadDo(
							rs.getInt(1), rs.getString(2), 
							rs.getInt(3), rs.getString(4), 
							rs.getFloat(5), rs.getFloat(6)));
				}
				constructDepartmentPrpLoads(loadDos, loads);				
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
	public List<PrpLoad> getPrpLoads(int projectId, Date startDate,
			Date endDate) throws DaoException {
		
		List<PrpLoad> prpLoads = new ArrayList<PrpLoad>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// daily表中不一定有全部的相关PRP，因为有的PRP可能还没有有效日报
			// 需要在prj_prp表中获取所有相关PRP，在daily表中获取工作量
			String sql = "SELECT t2.prpId, prpName, SUM(totalWorkload), SUM(overTimeLoad) "
				+ "FROM ("
				+ "		SELECT totalWorkload, overTimeLoad, prpId "
				+ "		FROM daily "
				+ "		WHERE status = '已通过' AND prjId = ? AND submitDate BETWEEN ? AND ? "
				+ "		ORDER BY submitDate ASC"
				+ "		) t1 "
				+ "	RIGHT JOIN ("
				+ "		SELECT prp.prpId, prp.prpName "
				+ "		FROM prp JOIN prj_prp USING(prpId)"
				+ "		WHERE prjId = ? "
				+ "		) t2 "
				+ "	USING(prpId) "
				+ "GROUP BY t2.prpId";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectId);
			pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
			pstmt.setInt(4, projectId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prpLoads.add(new PrpLoad(
						rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4)));
			}
			return prpLoads;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public List<ProjectVo> getRunningProjects() throws DaoException {
		List<ProjectVo> projects = new ArrayList<ProjectVo>();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT prjId, prjName "
					   + "FROM prj "
					   + "WHERE curdate() BETWEEN startDate AND endDate ";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				projects.add(new ProjectVo(rs.getInt("prjId"), rs.getString("prjName")));
			}
			return projects;
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
	 * 构造DepartmentPrpLoad
	 * @param loadDos
	 * @param loads
	 */
	private void constructDepartmentPrpLoads(List<DepartmentPrpLoadDo> loadDos, List<DepartmentPrpLoad> loads) {
		Map<Integer, DepartmentPrpLoad> container = new HashMap<Integer, DepartmentPrpLoad>();
		for (DepartmentPrpLoadDo loadDo : loadDos) {
			if (!container.containsKey(loadDo.getDepartmentId())) {
				DepartmentPrpLoad load = new DepartmentPrpLoad(
						loadDo.getDepartmentId(), loadDo.getDepartmentName(), new ArrayList<PrpLoad>());
				container.put(loadDo.getDepartmentId(), load);
			}
			PrpLoad prpLoad = new PrpLoad(
					loadDo.getPrpId(), loadDo.getPrpName(), loadDo.getLoad(), loadDo.getExtraLoad());
			container.get(loadDo.getDepartmentId()).getPrpLoads().add(prpLoad);			
		}
		for( Map.Entry<Integer, DepartmentPrpLoad> entry : container.entrySet()) {    
			loads.add(entry.getValue());
		}
	}

}
