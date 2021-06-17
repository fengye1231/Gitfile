package com.neusoft.dms.personal.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neusoft.dms.dept.domain.ProjectLoad;
import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.personal.domain.Daily;
import com.neusoft.dms.personal.domain.DateLoad;
import com.neusoft.dms.personal.domain.DailyVo;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.DateUtil;

public class PersonalDaoImpl implements PersonalDao{
	
	private Connection con = null;
	public PersonalDaoImpl(Connection con){
		this.con = con;
	}
	
	 ResultSet rs = null;
	 PreparedStatement pstmt = null;
	 
	 //展示个人日报
	 public List<DailyVo> showDailyReport(int empId,Date date)
	 							throws DaoException{
		 
		 List<DailyVo> loads = new ArrayList<DailyVo>();
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try{
			 	String sql = "SELECT prjId, prjName, prpId, prpName, dailyDesc, totalWorkLoad, overTimeLoad, status "
			 				+ " FROM daily JOIN prp USING(prpId) JOIN prj USING(prjId) "
			 				+ " WHERE empId = ? AND submitDate = ?";
			 	pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, empId);
				pstmt.setDate(2, new java.sql.Date(date.getTime()));
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					DailyVo d = new DailyVo();
					d.setPrjId(rs.getInt(1));
					d.setPrjName(rs.getString(2));
					d.setPrpId(rs.getInt(3));
					d.setPrpName(rs.getString(4));
					d.setDesc(rs.getString(5));
					d.setTotalWorkload(rs.getFloat(6));
					d.setOverTimeLoad(rs.getFloat(7));
					d.setStatus(rs.getString(8));
					loads.add(d);
				}
				return loads;
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				DBUtil.close(pstmt, rs);
			}
			 
			 
}
		 
		 
	 
	 
	 //获取指定人员所对应的分页日期工作量（按日期查询）
	 @Override
	public Page getDateLoads(int empId, Date startDate, Date endDate,
				int pageNum, int pageSize) throws DaoException {

		 	List<DateLoad> loads = new ArrayList<DateLoad>();
			PreparedStatement pstmt = null, pagePstmt = null;
			ResultSet rs = null;
			
			try {
				String pageSql = "SELECT  COUNT(*) FROM daily " +
						"WHERE empId = ?  AND status = '已通过' AND (daily.submitDate BETWEEN ? AND ?) ";
				
				pagePstmt = con.prepareStatement(pageSql);
				pagePstmt.setInt(1, empId);
				pagePstmt.setDate(2, new java.sql.Date(startDate.getTime()));
				pagePstmt.setDate(3, new java.sql.Date(endDate.getTime()));
				
				Page page = getPageInfo(pagePstmt, pageNum, pageSize);
				if (page.getTotalNum() > 0) {
					
					String sql = "SELECT dailyId, submitDate, SUM(totalWorkload), SUM(overTimeLoad) "
							+ "FROM daily JOIN employee USING(empId) "
							+ "		WHERE empId = ? "
							+ "				AND (daily.submitDate BETWEEN ? AND ?)  "
							+ "GROUP BY empId "
							+ "LIMIT ?, ? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, empId);
					pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
					pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
					pstmt.setInt(4, (page.getPageNum() - 1) * page.getPageSize());
					pstmt.setInt(5, page.getPageSize());
					
					rs = pstmt.executeQuery();
					if(rs != null){
						while (rs.next()) {
							DateLoad dateLoad = new DateLoad();
							dateLoad.setdailyId(rs.getInt(1));
							dateLoad.setSubmitDate(new Date(rs.getDate(2).getTime()));
							dateLoad.setLoad(rs.getFloat(3));
							dateLoad.setExtraLoad(rs.getInt(4));
							
							loads.add(dateLoad);
						}
					}
					
				}
				page.setList(loads);
				System.out.println("loads.size():"+loads.size());
				return page;
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				DBUtil.close(pstmt, rs);
			}
				
		}
	 
	//获取指定人员所对应的项目工作量汇总(按日期查询)
	public List<DateLoad> getDateLoads(int EmpId, Date startDate, Date endDate)
				throws DaoException {
		List<DateLoad> loads = new ArrayList<DateLoad>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			String sql = "SELECT empId, empName, SUM(totalWorkload), SUM(overTimeLoad) "
				+ "FROM ( "
				+ "		SELECT * "
				+ "		FROM daily join employee USING(empId) "
				+ "		WHERE empId = ? "
				+ "				AND ((daily.submitDate BETWEEN ? AND ?) OR (? BETWEEN daily.submitDate AND daily.endDate)) "
				+ "		) t1 LEFT JOIN ( "
				+ "		SELECT * "
				+ "		FROM daily JOIN employee USING(empId) "
				+ "		WHERE status = '已通过' AND submitDate BETWEEN ? AND ? ORDER BY submitDate ASC "
				+ "		) t2 USING(empId) "
				+ "GROUP BY empId ";

			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, EmpId);
			pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
			pstmt.setInt(4, EmpId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				loads.add(new DateLoad(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getFloat(4)));
			}
			return loads;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	} 

	//获取指定人员所对应的所有项目工作量(按日期查询)
	public Load getDateLoadsSummary(int EmpId, Date startDate, Date endDate)
				throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Load load = new Load(0, 0);
			String sql = "SELECT SUM(totalWorkload), SUM(overTimeLoad) "
					+ "FROM daily "
					+ "WHERE empId = ? AND status = '已通过' AND submitDate BETWEEN ? AND ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, EmpId);
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
		// TODO Auto-generated method stub
	}
	 
	//获取指定人员所对应的分页项目工作量(按项目查询)
	public Page getProjectLoads(int empId, Date startDate, Date endDate,int pageNum, int pageSize) 
				throws DaoException {
		// TODO Auto-generated method stub
		List<ProjectLoad> loads = new ArrayList<ProjectLoad>();
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			//总条数就是所有相关部门数
			String pageSql = "SELECT  COUNT(*) FROM prj_emp JOIN prj USING(prjId) " +
					"WHERE empId = ? AND ((prj.startDate BETWEEN ? AND ?) OR " +
					"(? BETWEEN prj.startDate AND prj.endDate)) ";
			
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setInt(1, empId);
			pagePstmt.setDate(2, new java.sql.Date(startDate.getTime()));
			pagePstmt.setDate(3, new java.sql.Date(endDate.getTime()));
			pagePstmt.setDate(4, new java.sql.Date(endDate.getTime()));
			
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			if (page.getTotalNum() > 0) {
				
				String sql = "SELECT prjId, prjName, SUM(totalWorkload), SUM(overTimeLoad) "
						+ "FROM ( "
						+ "		SELECT * "
						+ "		FROM prj JOIN prj_emp USING(prjId) "
						+ "		WHERE empId = ? "
						+ "				AND ((prj.startDate BETWEEN ? AND ?) OR (? BETWEEN prj.startDate AND prj.endDate)) "
						+ "		) t1 LEFT JOIN ( "
						+ "		SELECT * "
						+ "		FROM daily JOIN employee USING(empId) "
						+ "		WHERE status='已通过' AND empId = ? AND submitDate BETWEEN ? AND ? "
						+ "		) t2 USING(prjId) "
						+ "GROUP BY prjId "
						+ "LIMIT ?, ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, empId);
				pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
				pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
				pstmt.setInt(5, empId);
				pstmt.setDate(6, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(7, new java.sql.Date(endDate.getTime()));
				pstmt.setInt(8, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(9, page.getPageSize());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					loads.add(new ProjectLoad(
							rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4)));
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
	//获取指定人员所对应的项目工作量汇总(按项目查询)
	public List<ProjectLoad> getProjectLoads(int EmpId, Date startDate,Date endDate) 
			throws DaoException {
		// TODO Auto-generated method stub
		System.out.println("getProjectLoadsloads.size():");
			List<ProjectLoad> loads = new ArrayList<ProjectLoad>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				
				String sql = "SELECT prjId, prjName, SUM(totalWorkload), SUM(overTimeLoad) "
					+ "FROM ( "
					+ "		SELECT * "
					+ "		FROM employee JOIN prj_emp USING(prjId) "
					+ "		WHERE EmpId = ? "
					+ "				AND ((prj.startDate BETWEEN ? AND ?) OR (? BETWEEN prj.startDate AND prj.endDate)) "
					+ "		) t1 LEFT JOIN ( "
					+ "		SELECT * "
					+ "		FROM daily JOIN employee USING(empId) "
					+ "		WHERE status = '已通过' AND submitDate BETWEEN ? AND ? ORDER BY submitDate ASC "
					+ "		) t2 USING(prjId) "
					+ "GROUP BY prjId ";
				
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, EmpId);
				pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
				pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
				pstmt.setInt(4, EmpId);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					loads.add(new ProjectLoad(
							rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4)));
				}
				System.out.println("loads.size()loads.size()loads.size()loads.size():"+loads.size());
				return loads;
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				DBUtil.close(pstmt, rs);
			}
	}
	
	
	//获取指定人员所对应的所有项目工作量(按项目查询)
	public Load getProjectLoadsSummary(int EmpId, Date startDate, Date endDate)
			throws DaoException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Load load = new Load(0, 0);
			String sql = "SELECT SUM(totalWorkload), SUM(overTimeLoad) "
					+ "FROM daily JOIN employee USING(empId) "
					+ "WHERE empId = ? AND status = '已通过' AND submitDate BETWEEN ? AND ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, EmpId);
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
		// TODO Auto-generated method stub
	}
	
	
	
	private Page getPageInfo(PreparedStatement pstmt, int pageNum, int pageSize) throws DaoException {
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pageSize = Math.max(1, pageSize);
				int totalNum = rs.getInt(1);
				int totalPage = totalNum / pageSize + totalNum % pageSize;
				int pageNumber = Math.max(Math.min(totalPage, pageNum),totalNum == 0? 0: 1);
				
				return new Page(null, totalNum, pageNumber, totalPage, pageSize);
			}
			return null;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
	
	@Override
	public List<EmployeeVo> getEmployee() throws DaoException {
		// TODO Auto-generated method stub
		
		try {
		
			String sql = "SELECT EmpId, EmpName FROM Employee";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<EmployeeVo> employee = new ArrayList<EmployeeVo>();
			
			while (rs.next()) {
				employee.add(new EmployeeVo(rs.getInt("EmpId"), rs.getString("EmpName")));
			}
			return employee;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
	
	public List<EmployeeVo> getSubEmployee(int empId) throws DaoException{
		List<EmployeeVo> employee = new ArrayList<EmployeeVo>();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql = "select empId,empName from employee where superiorId = ? OR empId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empId);
			pstmt.setInt(2, empId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				EmployeeVo empVo = new EmployeeVo();
				empVo.setEmpId(rs.getInt("empId"));
				empVo.setEmpName(rs.getString("empName"));
				employee.add(empVo);
				}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public ArrayList<DailyVo> getDailyBySubmitDate(Date startDate) throws DaoException {
		// TODO Auto-generated method stub
		ArrayList<DailyVo> daily = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select submitDate from Daily where submitDate " +
					"BETWEEN ? AND ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
			pstmt.setDate(2, new java.sql.Date((new Date()).getTime()));
			rs = pstmt.executeQuery();
			
			if(rs != null){
				daily = new ArrayList<DailyVo>();
				while (rs.next()) {
					DailyVo dailyVo = new DailyVo();
					dailyVo.setSubmitDate(rs.getDate(1));
					daily.add(dailyVo);
				}
			}
			
		}catch(Exception e){
			throw new DaoException(e);
		}finally {
			DBUtil.close(pstmt, rs);
		}
		
		return daily;
	}

	@Override
	public List<DailyVo> getDailyReport(int empId, Date submitDate)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	//获取个人周报
	public Page getWeekDaily(int empId,Date submitDate,int pageNum,int pageSize) throws DaoException{
		List<DailyVo> dailyVos = new ArrayList<DailyVo>();
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		
		Date endDate = new Date(submitDate.getTime() + 1000 * 3600 * 24 * 5);
		for (int i = 0; ; i++) {
			endDate.setTime(endDate.getTime() + i * 1000 * 3600 * 24);
			if (DateUtil.getWorkdayCount(submitDate, endDate) >= 5) {
				break;
			}
		}
		
		try {
			String pageSql = "SELECT  COUNT(*) FROM daily " +
					"WHERE empId = ? AND status = '已通过' AND (daily.submitDate BETWEEN ? AND ?) ";
			
			pagePstmt = con.prepareStatement(pageSql);
			pagePstmt.setInt(1, empId);
			pagePstmt.setDate(2, new java.sql.Date(submitDate.getTime()));
			pagePstmt.setDate(3, new java.sql.Date(endDate.getTime()));
			
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			if (page.getTotalNum() > 0) {
				
				String sql = "SELECT dailyId, submitDate, totalWorkload, overTimeLoad, prjName, prpName, dailyDesc "
						+ "FROM daily JOIN employee USING(empId) JOIN prp USING(prpId) JOIN prj USING(prjId) "
						+ "		WHERE empId = ? AND (daily.submitDate BETWEEN ? AND ?)  "
						+ " ORDER BY submitDate DESC "
						+ "LIMIT ?, ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, empId);
				pstmt.setDate(2, new java.sql.Date(submitDate.getTime()));
				pstmt.setDate(3, new java.sql.Date(endDate.getTime()));
				pstmt.setInt(4, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(5, page.getPageSize());
				
				rs = pstmt.executeQuery();
				if(rs != null){
					while (rs.next()) {
						DailyVo dailyVo = new DailyVo();
						dailyVo.setDailyId(rs.getInt(1));
						dailyVo.setSubmitDate(new Date(rs.getDate(2).getTime()));
						dailyVo.setTotalWorkload(rs.getFloat(3));
						dailyVo.setOverTimeLoad(rs.getFloat(4));
						dailyVo.setPrjName(rs.getString(5));
						dailyVo.setPrpName(rs.getString(6));
						dailyVo.setDesc(rs.getString(7));
						
						dailyVos.add(dailyVo);
					}
				}
			}
			
			page.setList(dailyVos);
			return page;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
	
		//获取个人周报
		public List<DailyVo> getWeekDaily(int empId, Date submitDate)
					throws DaoException {
			return (List<DailyVo>) getWeekDaily(empId, submitDate, 1, Integer.MAX_VALUE).getList();
		} 
		
		//获取个人周报
		public Load getWeekDailySummary(int empId, Date submitDate)
					throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Load load = new Load(0, 0);
				String sql = "SELECT SUM(totalWorkload), SUM(overTimeLoad) "
						+ "FROM daily JOIN employee USING(empId) "
						+ "WHERE empId = ? AND status = '已通过' AND submitDate = ? ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, empId);
				pstmt.setDate(2, new java.sql.Date(submitDate.getTime()));

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
			// TODO Auto-generated method stub
		}
		 

}
	


