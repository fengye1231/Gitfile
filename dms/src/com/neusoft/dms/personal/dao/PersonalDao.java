package com.neusoft.dms.personal.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.neusoft.dms.dept.domain.ProjectLoad;
import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.MenuItem;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.personal.domain.DailyVo;
import com.neusoft.dms.personal.domain.DateLoad;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.util.DaoException;


public interface PersonalDao {
	
	//根据提交日期获取日报列表
	public ArrayList<DailyVo> getDailyBySubmitDate(Date submitDate) throws DaoException;
	
	/*
	//按项目查询
	public DailyVo getDailyByProjectName(String prjName) throws DaoException;
	*/
	
	
	public List<EmployeeVo> getSubEmployee(int empId) throws DaoException;
	
	//获取指定人员所对应的分页日期工作量(按日期查询)
	public Page getDateLoads(int EmpId, Date startDate, Date endDate, int pageNum, int pageSize)throws DaoException;
	
	//获取指定人员所对应的日期工作量汇总(按日期查询)
	public Load getDateLoadsSummary(int EmpId,Date startDate,Date endDate)throws Exception;
	
	//获取指定人员所对应的所有日期工作量(按日期查询)
	public List<DateLoad> getDateLoads(int EmpId, Date startDate,Date endDate)throws DaoException;
	
	//获取指定人员所对应的分页项目工作量(按项目查询)
	public Page getProjectLoads (int EmpId, Date startDate, Date endDate, int pageNum, int pageSize) throws DaoException;
	
	//获取指定人员所对应的项目工作量汇总(按项目查询)
	public Load getProjectLoadsSummary(int EmpId, Date startDate, Date endDate)throws DaoException;
	
	//获取指定人员所对应的所有项目工作量(按项目查询)
	public List<ProjectLoad> getProjectLoads (int EmpId, Date startDate, Date endDate) throws DaoException;
	
	//获取指定人员所对应的日报记录(个人周报)
	public List<DailyVo> getDailyReport(int empId,Date submitDate)throws DaoException;
	
	//获取所有人员
	public List<EmployeeVo> getEmployee () throws DaoException;
	
	//获取个人周报
	public Page getWeekDaily(int empId,Date submitDate,int pageNum,int pageSize)throws DaoException;
	
	public List<DailyVo> getWeekDaily(int empId, Date submitDate) throws DaoException ;
	
	public Load getWeekDailySummary(int empId, Date submitDate) throws Exception;
	
	//展示个人日报
	public List<DailyVo> showDailyReport(int empId,Date date)throws Exception;

	
}