package com.neusoft.dms.daily.entry.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.daily.entry.domain.Daily;
import com.neusoft.dms.daily.entry.domain.DailyCon;
import com.neusoft.dms.daily.entry.domain.DailyVo;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.employee.dao.EmployeeDaoImpl;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.employee.service.EmployeeServiceImpl;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class DailyDaoImpl implements DailyDao {
	private Connection con;

	public DailyDaoImpl(Connection con) {
		this.con = con;
	}

	// 添加日报
	public void addDaily(Daily daily) throws DaoException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into DAILY(dailyId,submitDate,dailyDesc,totalWorkload,overTimeLoad,tomorrowPlan,prjId,prpId,empId) values(?,?,?,?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, getMaxID() + 1);
			pstmt.setDate(2, new java.sql.Date(daily.getSubmitDate().getTime()));
			pstmt.setString(3, daily.getDesc());
			pstmt.setFloat(4, daily.getTotalWorkload());
			if(daily.getOverTimeLoad()!=0){
				pstmt.setFloat(5, daily.getOverTimeLoad());
			}else{
				pstmt.setFloat(5, 0);
			}
			pstmt.setString(6, daily.getTomorrowPlan());
			pstmt.setInt(7, daily.getPrjId());
			pstmt.setInt(8, daily.getPrpId());
			pstmt.setInt(9, daily.getEmpId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}

	@Override
	public DailyVo checkAddDaily(Daily daily) throws DaoException {
		DailyVo d = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from Daily  where empId=? and prjId=? and prpId=? and submitDate=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, daily.getEmpId());
			pstmt.setInt(2, daily.getPrjId());
			pstmt.setInt(3, daily.getPrpId());
			pstmt.setDate(4, new java.sql.Date(daily.getSubmitDate().getTime()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				d = new DailyVo();
				d.setDailyId(rs.getInt("dailyID"));
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return d;
	}

	// 根据日报编号获取日报信息
	public DailyVo getDailyById(int dailyId) throws DaoException {
		DailyVo d = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from Daily  where dailyid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dailyId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				d = new DailyVo();
				d.setDailyId(rs.getInt("dailyID"));
				d.setSubmitDate(new java.util.Date(rs.getDate("SubmitDate").getTime()));
				d.setEmpId(rs.getInt("EmpId"));
				d.setPrjId(rs.getInt("PrjId"));
				d.setPrpId(rs.getInt("PrpId"));
				d.setDesc(rs.getString("dailyDesc"));
				d.setTotalWorkload(rs.getFloat("TotalWorkload"));
				d.setOverTimeLoad(rs.getFloat("OverTimeLoad"));
				d.setStatus(rs.getString("Status"));
				d.setReviewEmpId(rs.getInt("ReviewEmpId"));
				d.setReason(rs.getString("Reason"));
				if(rs.getDate("ReviewDate")!=null){
					d.setReviewDate(new java.util.Date(rs.getDate("ReviewDate")
						.getTime()));
				}
				d = getNameById(d);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return d;
	}

	// 删除日报
	public void delDaily(int dailyId) throws DaoException {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from DAILY where dailyId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dailyId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}

	// 修改日报
	public void updateDaily(Daily daily) throws DaoException {
		PreparedStatement pstmt = null;
		try {
			String sql = "update DAILY set SubmitDate=?,dailyDesc=?,TotalWorkload=?,OverTimeLoad=?,PrjId=?,PrjId=? where DailyID=?";
			pstmt = con.prepareStatement(sql);
			pstmt
					.setDate(1, new java.sql.Date(daily.getSubmitDate()
							.getTime()));
			pstmt.setString(2, daily.getDesc());
			pstmt.setFloat(3, daily.getTotalWorkload());
			pstmt.setFloat(4, daily.getOverTimeLoad());
			// pstmt.setString(5, daily.getTomorrowPlan());
			pstmt.setInt(5, daily.getPrjId());
			pstmt.setInt(6, daily.getPrpId());
			pstmt.setInt(7, daily.getDailyId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}

	// 审核日报
	public void checkDaily(Daily daily) throws DaoException {
		PreparedStatement pstmt = null;
		try {
			String sql = "update DAILY set STATUS=?,REASON=?,ReviewEmpId=?,reviewDate=? where DailyID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, daily.getStatus());
			pstmt.setString(2, daily.getReason());
			pstmt.setInt(3, daily.getReviewEmpId());
			pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setInt(5, daily.getDailyId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}

	private Long getMaxID() throws DaoException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long maxid = 0L;
		try {
			con = DBUtil.getConnection();
			// 3.执行SQL
			String sql = "select max(DAILYID) from DAILY";
			// 3.1获取PreparedStatement对象
			pstmt = con.prepareStatement(sql);
			// 3.3利用Statement对象的相关execute执行SQL语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				maxid = rs.getLong(1);
			}

		} catch (SQLException e) {
			// (3)回滚事务
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 4.关闭资源
			DBUtil.close(con, pstmt, rs);
		}
		return maxid;
	}

	// 按条件列出日报
	public Page listDaily(Daily daily, Date sdate, Date edate, int pageNum,
			int pageSize) throws DaoException {
		Page page = null;
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			String pagesql = "select count(*) from (select * from DAILY where submitDate >= ? AND submitDate <= ? and empId=?)as a where Status like ?";
			if (daily.getPrjId() != 0) {
				pagesql += " and PrjId=" + daily.getPrjId();
			}
			if (daily.getPrpId() != 0) {
				pagesql += " and PrpId=" + daily.getPrjId();
			}
			pagePstmt = con.prepareStatement(pagesql);
			if (sdate != null) {
				pagePstmt.setDate(1, new java.sql.Date(sdate.getTime()));
			}
			if (edate != null) {
				pagePstmt.setDate(2, new java.sql.Date(edate.getTime()));
			}
			if (daily.getEmpId() != 0) {
				pagePstmt.setInt(3, daily.getEmpId());
			} else {
				pagePstmt.setString(3, "%%");
			}
			if (daily.getStatus() != null) {
				pagePstmt.setString(4, "%" + daily.getStatus() + "%");
			} else {
				pagePstmt.setString(4, "%%");
			}
			page = getPageInfo(pagePstmt, pageNum, pageSize);
			if (page.getTotalNum() > 0) {
				String sql = "select * from (select * from (select * from DAILY where submitDate >= ? AND submitDate <= ? and empId=?)as a where Status like ?";
				if (daily.getPrjId() != 0) {
					sql += " and PrjId=" + daily.getPrjId();
				}
				if (daily.getPrpId() != 0) {
					sql += " and PrpId=" + daily.getPrjId();
				}
				sql += ")as b limit ?,?";
				pstmt = con.prepareStatement(sql);
				if (sdate != null) {
					pstmt.setDate(1, new java.sql.Date(sdate.getTime()));
				}
				if (edate != null) {
					pstmt.setDate(2, new java.sql.Date(edate.getTime()));
				}
				if (daily.getEmpId() != 0) {
					pstmt.setInt(3, daily.getEmpId());
				} else {
					pstmt.setString(3, "%%");
				}
				if (daily.getStatus() != null) {
					pstmt.setString(4, "%" + daily.getStatus() + "%");
				} else {
					pstmt.setString(4, "%%");
				}
				pstmt.setInt(5, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(6, page.getPageSize());
				rs = pstmt.executeQuery();
				List<DailyVo> dailylist = new ArrayList<DailyVo>();
				while (rs.next()) {
					DailyVo d = new DailyVo();
					d.setDailyId(rs.getInt("dailyID"));
					d.setSubmitDate(new java.util.Date(rs.getDate("SubmitDate")
							.getTime()));
					d.setEmpId(rs.getInt("EmpId"));
					d.setPrjId(rs.getInt("PrjId"));
					d.setPrpId(rs.getInt("PrpId"));
					d.setDesc(rs.getString("dailyDesc"));
					d.setTotalWorkload(rs.getFloat("TotalWorkload"));
					d.setOverTimeLoad(rs.getFloat("OverTimeLoad"));
					d.setStatus(rs.getString("Status"));
					d.setReviewEmpId(rs.getInt("ReviewEmpId"));
					if(rs.getDate("ReviewDate")!=null){
						d.setReviewDate(new java.util.Date(rs.getDate("ReviewDate")
							.getTime()));
					}
					d = getNameById(d);
					dailylist.add(d);
				}
				page.setList(dailylist);
			}
			return page;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	@Override
	public Page listDaily(DailyCon dailyCon) throws DaoException {
		Page page = null;
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		try {
			String pagesql = "select count(*) from (select * from DAILY where submitDate >= ? AND submitDate <= ? and empId=?)as a where Status like ?";
			if (dailyCon.getPrjId() != 0) {
				pagesql += " and PrjId=" + dailyCon.getPrjId();
			}
			if (dailyCon.getPrpId() != 0) {
				pagesql += " and PrpId=" + dailyCon.getPrjId();
			}
			pagePstmt = con.prepareStatement(pagesql);
			if (dailyCon.getStartDate() != null) {
				pagePstmt.setDate(1, new java.sql.Date(dailyCon.getStartDate().getTime()));
			}
			if (dailyCon.getEndDate() != null) {
				pagePstmt.setDate(2, new java.sql.Date(dailyCon.getEndDate().getTime()));
			}
			if (dailyCon.getEmpId() != 0) {
				pagePstmt.setInt(3, dailyCon.getEmpId());
			} else {
				pagePstmt.setString(3, "%%");
			}
			if (dailyCon.getStatus() != null) {
				pagePstmt.setString(4, "%" + dailyCon.getStatus() + "%");
			} else {
				pagePstmt.setString(4, "%%");
			}
			page = getPageInfo(pagePstmt, dailyCon.getPageNum(), dailyCon.getPageSize());
			if (page.getTotalNum() > 0) {
				String sql = "select * from (select * from (select * from DAILY where submitDate >= ? AND submitDate <= ? and empId=?)as a where Status like ?";
				if (dailyCon.getPrjId() != 0) {
					sql += " and PrjId=" + dailyCon.getPrjId();
				}
				if (dailyCon.getPrpId() != 0) {
					sql += " and PrpId=" + dailyCon.getPrjId();
				}
				sql += ")as b limit ?,?";
				pstmt = con.prepareStatement(sql);
				if (dailyCon.getStartDate() != null) {
					pstmt.setDate(1, new java.sql.Date(dailyCon.getStartDate().getTime()));
				}
				if (dailyCon.getEndDate() != null) {
					pstmt.setDate(2, new java.sql.Date(dailyCon.getEndDate().getTime()));
				}
				if (dailyCon.getEmpId() != 0) {
					pstmt.setInt(3, dailyCon.getEmpId());
				} else {
					pstmt.setString(3, "%%");
				}
				if (dailyCon.getStatus() != null) {
					pstmt.setString(4, "%" + dailyCon.getStatus() + "%");
				} else {
					pstmt.setString(4, "%%");
				}
				pstmt.setInt(5, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(6, page.getPageSize());
				rs = pstmt.executeQuery();
				List<DailyVo> dailylist = new ArrayList<DailyVo>();
				while (rs.next()) {
					DailyVo d = new DailyVo();
					d.setDailyId(rs.getInt("dailyID"));
					d.setSubmitDate(new java.util.Date(rs.getDate("SubmitDate")
							.getTime()));
					d.setEmpId(rs.getInt("EmpId"));
					d.setPrjId(rs.getInt("PrjId"));
					d.setPrpId(rs.getInt("PrpId"));
					d.setDesc(rs.getString("dailyDesc"));
					d.setTotalWorkload(rs.getFloat("TotalWorkload"));
					d.setOverTimeLoad(rs.getFloat("OverTimeLoad"));
					d.setStatus(rs.getString("Status"));
					d.setReviewEmpId(rs.getInt("ReviewEmpId"));
					if(rs.getDate("ReviewDate")!=null){
						d.setReviewDate(new java.util.Date(rs.getDate("ReviewDate")
							.getTime()));
					}
					d = getNameById(d);
					dailylist.add(d);
				}
				page.setList(dailylist);
			}
			return page;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}
	@Override
	public Page listCheckDaily(DailyCon dailyCon) throws DaoException {
		Page page = null;
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;

		try {
			String pageSql = "select count(*) from (select * from DAILY where submitDate >= ? AND submitDate <= ? )as a where Status like ?";
			if (dailyCon.getEmpId() != 0) {
				pageSql += " and EmpId=" + dailyCon.getEmpId();
			} else {
				List<EmployeeVo> emps = EmployeeServiceImpl.getInstance()
				.leaderSearchEmp(dailyCon.getReviewEmpId());
				pageSql += "and EmpId=0";
				for (EmployeeVo emp : emps) {
					pageSql += " or EmpId=" + emp.getEmpId();
				}
			}
			pagePstmt = con.prepareStatement(pageSql);
			if (dailyCon.getStartDate() != null) {
				pagePstmt.setDate(1, new java.sql.Date(dailyCon.getStartDate().getTime()));
			}
			if (dailyCon.getEndDate() != null) {
				pagePstmt.setDate(2, new java.sql.Date(dailyCon.getEndDate().getTime()));
			}
			if (dailyCon.getStatus() != null) {
				pagePstmt.setString(3, "%" + dailyCon.getStatus() + "%");
			} else {
				pagePstmt.setString(3, "%%");
			}
			page = getPageInfo(pagePstmt, dailyCon.getPageNum(), dailyCon.getPageSize());
			if (page.getTotalNum() > 0) {
				String sql = "select * from (select * from (select * from DAILY where submitDate >= ? AND submitDate <= ? )as a where Status like ?";
				if (dailyCon.getEmpId() != 0) {
					sql += " and EmpId=" + dailyCon.getEmpId();
				} else {
					List<EmployeeVo> emps = EmployeeServiceImpl.getInstance()
							.leaderSearchEmp(dailyCon.getReviewEmpId());
					sql += "and EmpId=0";
					for (EmployeeVo emp : emps) {
						sql += " or EmpId=" + emp.getEmpId();
					}
				}
				sql += " )as b limit ?,?";
				pstmt = con.prepareStatement(sql);
				if (dailyCon.getStartDate() != null) {
					pstmt.setDate(1, new java.sql.Date(dailyCon.getStartDate().getTime()));
				}
				if (dailyCon.getEndDate() != null) {
					pstmt.setDate(2, new java.sql.Date(dailyCon.getEndDate().getTime()));
				}
				if (dailyCon.getStatus() != null) {
					pstmt.setString(3, "%" + dailyCon.getStatus() + "%");
				} else {
					pstmt.setString(3, "%%");
				}
				pstmt.setInt(4, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(5, page.getPageSize());
				rs = pstmt.executeQuery();
				List<DailyVo> dailylist = new ArrayList<DailyVo>();
				while (rs.next()) {
					DailyVo d = new DailyVo();
					d.setDailyId(rs.getInt("dailyID"));
					d.setSubmitDate(new java.util.Date(rs.getDate("SubmitDate")
							.getTime()));
					d.setEmpId(rs.getInt("EmpId"));
					d.setPrjId(rs.getInt("PrjId"));
					d.setPrpId(rs.getInt("PrpId"));
					d.setDesc(rs.getString("dailyDesc"));
					d.setTotalWorkload(rs.getFloat("TotalWorkload"));
					d.setOverTimeLoad(rs.getFloat("OverTimeLoad"));
					d.setStatus(rs.getString("Status"));
					d.setReviewEmpId(rs.getInt("ReviewEmpId"));
					if(rs.getDate("ReviewDate")!=null){
						d.setReviewDate(new java.util.Date(rs.getDate("ReviewDate")
							.getTime()));
					}
					d = getNameById(d);
					dailylist.add(d);
				}
				page.setList(dailylist);
			}
			return page;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	public Page listCheckDaily(Daily daily, Date sdate, Date edate,
			int pageNum, int pageSize) throws DaoException {
		Page page = null;
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;

		try {
			String pageSql = "select count(*) from (select * from DAILY where submitDate >= ? AND submitDate <= ? )as a where Status like ?";
			if (daily.getEmpId() != 0) {
				pageSql += " and EmpId=" + daily.getEmpId();
			} else {
				List<EmployeeVo> emps = EmployeeServiceImpl.getInstance()
				.leaderSearchEmp(daily.getReviewEmpId());
				pageSql += "and EmpId=0";
				for (EmployeeVo emp : emps) {
					pageSql += " or EmpId=" + emp.getEmpId();
				}
			}
			pagePstmt = con.prepareStatement(pageSql);
			if (sdate != null) {
				pagePstmt.setDate(1, new java.sql.Date(sdate.getTime()));
			}
			if (edate != null) {
				pagePstmt.setDate(2, new java.sql.Date(edate.getTime()));
			}
			if (daily.getStatus() != null) {
				pagePstmt.setString(3, "%" + daily.getStatus() + "%");
			} else {
				pagePstmt.setString(3, "%%");
			}
			page = getPageInfo(pagePstmt, pageNum, pageSize);
			if (page.getTotalNum() > 0) {
				String sql = "select * from (select * from (select * from DAILY where submitDate >= ? AND submitDate <= ? )as a where Status like ?";
				if (daily.getEmpId() != 0) {
					sql += " and EmpId=" + daily.getEmpId();
				} else {
					List<EmployeeVo> emps = EmployeeServiceImpl.getInstance()
							.leaderSearchEmp(daily.getReviewEmpId());
					sql += "and EmpId=0";
					for (EmployeeVo emp : emps) {
						sql += " or EmpId=" + emp.getEmpId();
					}
				}
				sql += " )as b limit ?,?";
				pstmt = con.prepareStatement(sql);
				if (sdate != null) {
					pstmt.setDate(1, new java.sql.Date(sdate.getTime()));
				}
				if (edate != null) {
					pstmt.setDate(2, new java.sql.Date(edate.getTime()));
				}
				if (daily.getStatus() != null) {
					pstmt.setString(3, "%" + daily.getStatus() + "%");
				} else {
					pstmt.setString(3, "%%");
				}
				pstmt.setInt(4, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(5, page.getPageSize());
				rs = pstmt.executeQuery();
				List<DailyVo> dailylist = new ArrayList<DailyVo>();
				while (rs.next()) {
					DailyVo d = new DailyVo();
					d.setDailyId(rs.getInt("dailyID"));
					d.setSubmitDate(new java.util.Date(rs.getDate("SubmitDate")
							.getTime()));
					d.setEmpId(rs.getInt("EmpId"));
					d.setPrjId(rs.getInt("PrjId"));
					d.setPrpId(rs.getInt("PrpId"));
					d.setDesc(rs.getString("dailyDesc"));
					d.setTotalWorkload(rs.getFloat("TotalWorkload"));
					d.setOverTimeLoad(rs.getFloat("OverTimeLoad"));
					d.setStatus(rs.getString("Status"));
					d.setReviewEmpId(rs.getInt("ReviewEmpId"));
					if(rs.getDate("ReviewDate")!=null){
						d.setReviewDate(new java.util.Date(rs.getDate("ReviewDate")
							.getTime()));
					}
					d = getNameById(d);
					dailylist.add(d);
				}
				page.setList(dailylist);
			}
			return page;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	public Page listAllCheckDaily(Daily daily, Date sdate, Date edate,
			int pageNum, int pageSize) throws DaoException {
		List<DailyVo> dailylist = new ArrayList<DailyVo>();
		Page page = null;
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;

		try {
			String pageSql = "select count(*) from (select * from DAILY where submitDate >= ? AND submitDate <= ? )as a where Status like ?";
			if (daily.getEmpId() != 0) {
				pageSql += " and EmpId=" + daily.getPrjId();
			}
			pagePstmt = con.prepareStatement(pageSql);
			if (sdate != null) {
				pagePstmt.setDate(1, new java.sql.Date(sdate.getTime()));
			}
			if (edate != null) {
				pagePstmt.setDate(2, new java.sql.Date(edate.getTime()));
			}
			if (daily.getStatus() != null) {
				pagePstmt.setString(3, "%" + daily.getStatus() + "%");
			} else {
				pagePstmt.setString(3, "%%");
			}
			page = getPageInfo(pagePstmt, pageNum, pageSize);
			if (page.getTotalNum() > 0) {
				String sql = "select * from (select * from (select * from DAILY where submitDate >= ? AND submitDate <= ? )as a where Status like ?";
				if (daily.getEmpId() != 0) {
					sql += " and EmpId=" + daily.getEmpId();
				}
				sql += " )as b limit ?,?";
				pstmt = con.prepareStatement(sql);
				if (sdate != null) {
					pstmt.setDate(1, new java.sql.Date(sdate.getTime()));
				}
				if (edate != null) {
					pstmt.setDate(2, new java.sql.Date(edate.getTime()));
				}
				if (daily.getStatus() != null) {
					pstmt.setString(3, "%" + daily.getStatus() + "%");
				} else {
					pstmt.setString(3, "%%");
				}
				pstmt.setInt(4, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(5, page.getPageSize());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					DailyVo d = new DailyVo();
					d.setDailyId(rs.getInt("dailyID"));
					d.setSubmitDate(new java.util.Date(rs.getDate("SubmitDate")
							.getTime()));
					d.setEmpId(rs.getInt("EmpId"));
					d.setPrjId(rs.getInt("PrjId"));
					d.setPrpId(rs.getInt("PrpId"));
					d.setDesc(rs.getString("dailyDesc"));
					d.setTotalWorkload(rs.getFloat("TotalWorkload"));
					d.setOverTimeLoad(rs.getFloat("OverTimeLoad"));
					d.setStatus(rs.getString("Status"));
					d.setReviewEmpId(rs.getInt("ReviewEmpId"));
					if(rs.getDate("ReviewDate")!=null){
						d.setReviewDate(new java.util.Date(rs.getDate("ReviewDate")
							.getTime()));
					}
					d = getNameById(d);
					dailylist.add(d);
				}
				page.setList(dailylist);
			}
			return page;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	/**
	 * 根据数据库里的总条数获取分页信息
	 * 
	 * @param pstmt
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
	private Page getPageInfo(PreparedStatement pstmt, int pageNum, int pageSize)
			throws DaoException {
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pageSize = Math.max(1, pageSize);
				int totalNum = rs.getInt(1);
				int totalPage = (int) Math.ceil((double) totalNum / pageSize);
				pageNum = Math.max(Math.min(totalPage, pageNum),
						totalNum == 0 ? 0 : 1);

				return new Page(null, totalNum, pageNum, totalPage, pageSize);
			}
			throw new DaoException("分页信息获取失败");
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, rs);
		}
	}

	// private Page getPageInfo(Daily daily, Date sdate, Date edate, int
	// pageNum,
	// int PageSize) throws DaoException {
	// Page page = new Page();
	// int pageSize = page.getPageSize();
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// int totalNum = 0;
	//
	// try {
	// String sql =
	// "select count(*) from (select * from DAILY where submitDate >= ? AND submitDate <= ? )as a where Status like ?";
	// if (daily.getPrjId() != 0) {
	// sql += " and PrjId=" + daily.getPrjId();
	// }
	// if (daily.getPrpId() != 0) {
	// sql += " and PrpId=" + daily.getPrjId();
	// }
	// pstmt = con.prepareStatement(sql);
	// if (sdate != null) {
	// pstmt.setDate(1, new java.sql.Date(sdate.getTime()));
	// }
	// if (edate != null) {
	// pstmt.setDate(2, new java.sql.Date(edate.getTime()));
	// }
	// if (daily.getStatus() != null) {
	// pstmt.setString(3, "%" + daily.getStatus() + "%");
	// } else {
	// pstmt.setString(3, "%%");
	// }
	// rs = pstmt.executeQuery();
	//
	// if (rs.next()) {
	// totalNum = rs.getInt(1);
	// }
	//
	// int totalPage = (totalNum % pageSize) > 0 ? totalNum / pageSize + 1
	// : totalNum / pageSize;
	//
	// page.setTotalNum(totalNum);
	// page.setTotalPage(totalPage);
	// page.setPageNum(pageNum);
	//
	// } catch (SQLException e) {
	// throw new DaoException("Error on get Pageinfo", e);
	// } finally {
	// DBUtil.close(pstmt, rs);
	// }
	// return page;
	// }
	//
	// private Page getCheckPageInfo(Daily daily, Date sdate, Date edate,
	// int pageNum, int PageSize) throws DaoException {
	// Page page = new Page();
	// int pageSize = page.getPageSize();
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// int totalNum = 0;
	//
	// try {
	// String sql =
	// "select count(*) from (select * from DAILY where submitDate >= ? AND submitDate <= ? )as a where Status like ?";
	// if (daily.getEmpId() != 0) {
	// sql += " and EmpId=" + daily.getPrjId();
	// }
	// pstmt = con.prepareStatement(sql);
	// if (sdate != null) {
	// pstmt.setDate(1, new java.sql.Date(sdate.getTime()));
	// }
	// if (edate != null) {
	// pstmt.setDate(2, new java.sql.Date(edate.getTime()));
	// }
	// if (daily.getStatus() != null) {
	// pstmt.setString(3, "%" + daily.getStatus() + "%");
	// } else {
	// pstmt.setString(3, "%%");
	// }
	// rs = pstmt.executeQuery();
	//
	// if (rs.next()) {
	// totalNum = rs.getInt(1);
	// }
	//
	// int totalPage = (totalNum % pageSize) > 0 ? totalNum / pageSize + 1
	// : totalNum / pageSize;
	//
	// page.setTotalNum(totalNum);
	// page.setTotalPage(totalPage);
	// page.setPageNum(pageNum);
	//
	// } catch (SQLException e) {
	// throw new DaoException("Error on get Pageinfo", e);
	// } finally {
	// DBUtil.close(pstmt, rs);
	// }
	//
	// return page;
	// }

	@Override
	public void downloadDaily(Date date, int empId) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public DailyVo getDailyByprj(Date sdate, Date edate, int prjId)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyVo getDailyBysubmitDate(Date sdate, Date edate, int empId)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyVo getUnsubmitDaily(int EmpId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void failDaily(Daily daily) throws DaoException {
		PreparedStatement pstmt = null;
		try {
			// String dIds = null;
			// String sql =
			// "update DAILY set STATUS=不通过,ReviewEmpId=?,reviewDate=? where DailyID in( "+dIds+" )";
			String sql = "update DAILY set STATUS='未通过',ReviewEmpId=?,reviewDate=? where DailyID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, daily.getReviewEmpId());
			pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setInt(3, daily.getDailyId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}

	@Override
	public void passDaily(Daily daily) throws DaoException {
		PreparedStatement pstmt = null;
		try {
			String sql = "update DAILY set STATUS='已通过',ReviewEmpId=?,reviewDate=? where DailyID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, daily.getReviewEmpId());
			pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setInt(3, daily.getDailyId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}

	@Override
	public List<DailyVo> getDailyByPrj(int prjId) throws DaoException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from DAILY where prjId=?";
		List<DailyVo> dailylist = new ArrayList<DailyVo>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DailyVo d = new DailyVo();
				d.setDailyId(rs.getInt("dailyID"));
				d.setSubmitDate(new java.util.Date(rs.getDate("SubmitDate")
						.getTime()));
				d.setEmpId(rs.getInt("EmpId"));
				d.setPrjId(rs.getInt("PrjId"));
				d.setPrpId(rs.getInt("PrpId"));
				d.setDesc(rs.getString("dailyDesc"));
				d.setTotalWorkload(rs.getFloat("TotalWorkload"));
				d.setOverTimeLoad(rs.getFloat("OverTimeLoad"));
				d.setStatus(rs.getString("Status"));
				d.setReviewEmpId(rs.getInt("ReviewEmpId"));
				if(rs.getDate("ReviewDate")!=null){
					d.setReviewDate(new java.util.Date(rs.getDate("ReviewDate")
						.getTime()));
				}
				d = getNameById(d);
				dailylist.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dailylist;
	}

	@Override
	public DailyVo getNameById(DailyVo daily) throws DaoException {
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		String sql1 = "select employee.empName,prj.prjName,prp.prpName "
				+ "from daily,employee,prp,prj where daily.empId=? and daily.prjId=? and daily.prpId=? "
				+ "and daily.empId=employee.empId and daily.prjId=prj.prjId and daily.prpId=prp.prpId";
		try {
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, daily.getEmpId());
			pstmt1.setInt(2, daily.getPrjId());
			pstmt1.setInt(3, daily.getPrpId());
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				daily.setEmpName(rs1.getString("empName"));
				daily.setPrjName(rs1.getString("prjName"));
				daily.setPrpName(rs1.getString("prpName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (daily.getReviewEmpId() != 0) {
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			String sql2 = "select employee.empName from daily,employee "
					+ "where  daily.reviewEmpId=? and daily.reviewEmpId=employee.empId";
			try {
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, daily.getReviewEmpId());
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					daily.setReviewEmpName(rs2.getString("empName"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return daily;
	}

}
