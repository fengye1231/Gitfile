package com.neusoft.dms.dept.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.dms.dept.dao.DepartmentDailyWarningDao;
import com.neusoft.dms.dept.dao.DepartmentDailyWarningDaoImpl;
import com.neusoft.dms.dept.dao.DepartmentLoadDao;
import com.neusoft.dms.dept.dao.DepartmentLoadDaoImpl;
import com.neusoft.dms.dept.domain.EmployeeProject;
import com.neusoft.dms.dept.domain.EmployeeProjectDailyDo;
import com.neusoft.dms.dept.domain.ProjectPeriod;
import com.neusoft.dms.dept.domain.SubmitInfo;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.domain.TableHeaderCell;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.DateUtil;
import com.neusoft.dms.util.ServiceException;

@SuppressWarnings("unchecked")
public class DepartmentDailyWarningServiceImpl implements DepartmentDailyWarningService {

	private static DepartmentDailyWarningServiceImpl instance = new DepartmentDailyWarningServiceImpl();
	
	public static DepartmentDailyWarningServiceImpl getInstance() {
		return instance;
	}

	
	@Override
	public Table queryUncheck(PageQuery query) throws ServiceException {
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			int deptId = (Integer) query.getParam("deptId");
			Date startDate = (Date) query.getParam("startDate");
			Date endDate = (Date) query.getParam("endDate");
			
			con = DBUtil.getConnection();
			DepartmentDailyWarningDao waringDao = new DepartmentDailyWarningDaoImpl(con);
			
			Page page = waringDao.getUncheck(deptId, startDate, endDate, pageNum, pageSize);

			return new Table(page);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@Override
	public Table queryUnsubmit(PageQuery query) throws ServiceException {
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			int deptId = (Integer) query.getParam("deptId");
			Date startDate = (Date) query.getParam("startDate");
			Date endDate = (Date) query.getParam("endDate");
			
			con = DBUtil.getConnection();
			DepartmentLoadDao loadDao = new DepartmentLoadDaoImpl(con);
			DepartmentDailyWarningDao waringDao = new DepartmentDailyWarningDaoImpl(con);
			
			Page page = waringDao.getSubmitInfo(deptId, startDate, endDate, pageNum, pageSize);
			List<ProjectPeriod> projectPeriods = loadDao.getProjectPeriod(deptId, startDate, endDate);
			List<EmployeeProject> employeeProjects = loadDao.getEmployeeProject(deptId, startDate, endDate);
			
			Table result = constructUnsubmitTable(page, projectPeriods, employeeProjects, startDate, endDate);
			return result;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	
	/**
	 * 构造未提交日报的表格
	 * @param page
	 * @param projectPeriods
	 * @param employeeProjects
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private Table constructUnsubmitTable(Page page, List<ProjectPeriod> projectPeriods, 
			List<EmployeeProject> employeeProjects, Date startDate, Date endDate) {
		
		List<SubmitInfo> submitInfos = page.getList();
		String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		Table table = new Table();

		// 生成每列对应的日期
		List<Date> dates = new ArrayList<Date>();
		dates.add(0, new Date(endDate.getTime()));
		while (dates.get(0).after(startDate)) {
			dates.add(0, new Date(dates.get(0).getTime() - 1000 * 3600 * 24));
		}
		
		Map<Integer, int[]> shouldSubmit = getEmployeeShouldSubmitOnDates(projectPeriods, employeeProjects, dates);
		Map<Integer, int[]> submited = getEmployeeSubmitedOnDates(submitInfos, dates);
		
		// 生成列标题
		table.addHeaderCell(new TableHeaderCell("employeeName", "姓名"));
		for (Date date : dates) {
			table.addHeaderCell(new TableHeaderCell(
					String.valueOf(date.getTime()), 
					dateFormat.format(date) + "(" + weeks[DateUtil.dayOfWeek(date) - 1] + ")"));
		}
		
		// 构造行数据
		List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();
		Map<Integer, String> employeeNames = new HashMap<Integer, String>();
		
		//每一行对应一个员工
		for (SubmitInfo info : submitInfos) {
			if (!employeeNames.containsKey((info.getEmployeeId()))) {
				employeeNames.put(info.getEmployeeId(), info.getEmployeeName());
			}
		}
		// 行循环
		for(Map.Entry<Integer, String> entry : employeeNames.entrySet()) {
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("employeeName", entry.getValue());
			// 列循环
			for (int index = 0; index < dates.size(); index++) {
				int shouldSubmitCount = shouldSubmit.get(entry.getKey())[index];
				int submitedCount = submited.get(entry.getKey())[index];
				String submitShow = null;
				if (shouldSubmitCount == 0 && submitedCount > 0) {	// 当天不需要提交日报但员工提交了
					submitShow = String.valueOf(submitedCount);
				} else if (shouldSubmitCount == 0) {	// 当天不需要提交日报
					submitShow = "";
				} else if (submitedCount < shouldSubmitCount) {	// 当天提交数不足
					submitShow = submitedCount + "/" + shouldSubmitCount;
				} else {	// 当天已提交要求份数的日报
					submitShow = String.valueOf(submitedCount);
				}
				row.put(String.valueOf(dates.get(index).getTime()), submitShow);
			}
			rowList.add(row);
		}
		
		Page resultPage = new Page(rowList, page.getTotalNum(), 
				page.getPageNum(), page.getTotalPage(), page.getPageSize());
		table.setPage(resultPage);
		return table;
	}
	
	/**
	 * 获取指定日期员工应提交日报数
	 * @param projectPeriods
	 * @param employeeProjects
	 * @param dates
	 * @return
	 */
	private Map<Integer, int[]> getEmployeeShouldSubmitOnDates(
			List<ProjectPeriod> projectPeriods, List<EmployeeProject> employeeProjects, List<Date> dates) {
		
		Map<Integer, int[]> shouldSubmit = new HashMap<Integer, int[]>();
		for (EmployeeProject empProj : employeeProjects) {
			int employeeId = empProj.getEmployeeId();
			List<Integer> projectIds = empProj.getProjectIds();
			if (!shouldSubmit.containsKey(employeeId)) {
				shouldSubmit.put(employeeId, new int[dates.size()]);
			}
			for (int index = 0; index < dates.size(); index++) {
				Date date = dates.get(index);
				int dayOfWeek = DateUtil.dayOfWeek(date);
				if (dayOfWeek == 1 || dayOfWeek == 7) continue;	//周六周日
				for (ProjectPeriod period : projectPeriods) {
					if (projectIds.contains(period.getProjectId())
							&& DateUtil.isInThePeriod(date, period.getStartDate(), period.getEndDate())) {
						shouldSubmit.get(employeeId)[index]++;
					}
				}
			}
		}
		return shouldSubmit;
	}
	
	/**
	 * 获取指定日期员工已提交日报数
	 * @param submitInfos
	 * @param dates
	 * @return
	 */
	private Map<Integer, int[]> getEmployeeSubmitedOnDates(List<SubmitInfo> submitInfos, List<Date> dates) {
		Map<Integer, int[]> submited = new HashMap<Integer, int[]>();
		for (SubmitInfo info : submitInfos) {
			if (!submited.containsKey(info.getEmployeeId())) {
				submited.put(info.getEmployeeId(), new int[dates.size()]);
			}
			for (int index = 0; index < dates.size(); index++) {
				if (DateUtil.isSameDay(dates.get(index), info.getDate())) {
					submited.get(info.getEmployeeId())[index]++;
					break;
				}
			}
		}
		return submited;
	}

	@Override
	public void clearUncheckWarning(int empId) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentDailyWarningDao warningDao = new DepartmentDailyWarningDaoImpl(con);
			warningDao.clearWarning(empId, DepartmentDailyWarningDao.Type.UNCHECK);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void clearUnsubmitWarning(int empId) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentDailyWarningDao warningDao = new DepartmentDailyWarningDaoImpl(con);
			warningDao.clearWarning(empId, DepartmentDailyWarningDao.Type.UNSUBMIT);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@Override
	public boolean hasUncheckWarning(int empId) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentDailyWarningDao warningDao = new DepartmentDailyWarningDaoImpl(con);
			return warningDao.hasWarning(empId, DepartmentDailyWarningDao.Type.UNCHECK);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@Override
	public boolean hasUnsubmitWarning(int empId) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentDailyWarningDao warningDao = new DepartmentDailyWarningDaoImpl(con);
			return warningDao.hasWarning(empId, DepartmentDailyWarningDao.Type.UNSUBMIT);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void warnUncheck(int deptId) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentDailyWarningDao warningDao = new DepartmentDailyWarningDaoImpl(con);
			warningDao.addUncheckWarning(deptId);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@Override
	public void warnUnsubmit(int deptId) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentLoadDao loadDao = new DepartmentLoadDaoImpl(con);
			DepartmentDailyWarningDao warningDao = new DepartmentDailyWarningDaoImpl(con);
			
			List<EmployeeProjectDailyDo> empProjDailyDos = warningDao.getSubmitCount(deptId);
			List<ProjectPeriod> periods = loadDao.getProjectPeriod(deptId, new Date(0), new Date());	//至今
			List<Integer> shouldWarning = getEmployeeShouldBeWarned(empProjDailyDos, periods);
			
			warningDao.addUnsubmitWarning(shouldWarning);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	
	/**
	 * 获取应该被提醒的员工ID列表
	 * @param empProjDailyDos
	 * @param periods
	 * @return
	 */
	private List<Integer> getEmployeeShouldBeWarned(
			List<EmployeeProjectDailyDo> empProjDailyDos, List<ProjectPeriod> periods) {
		
		List<Integer> shouldBeWarned = new ArrayList<Integer>();
		Map<Integer, Integer> projectWorkdays = new HashMap<Integer, Integer>();
		Date now = DateUtil.floor(new Date());
		
		for (ProjectPeriod period : periods) {
			projectWorkdays.put(period.getProjectId(), 
					DateUtil.getWorkdayCount(period.getStartDate(), 
							period.getEndDate().before(now)? period.getEndDate(): now));	// 至今
		}
		for (EmployeeProjectDailyDo epd : empProjDailyDos) {
			if (projectWorkdays.containsKey(epd.getProjectId()) 
					&& epd.getCount() < projectWorkdays.get(epd.getProjectId())) {
				shouldBeWarned.add(epd.getEmployeeId());
			}
		}
		return shouldBeWarned;
	}

}
