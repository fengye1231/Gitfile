package com.neusoft.dms.dept.dao;

import java.util.Date;
import java.util.List;

import com.neusoft.dms.dept.domain.DepartmentLoadVo;
import com.neusoft.dms.dept.domain.DepartmentVo;
import com.neusoft.dms.dept.domain.EmployeeProject;
import com.neusoft.dms.dept.domain.ProjectLoad;
import com.neusoft.dms.dept.domain.ProjectPeriod;
import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.util.DaoException;

public interface DepartmentLoadDao {
	
	/**
	 * 获取指定部门在指定日期范围内的项目工作量
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Page getProjectLoads (int deptId, Date startDate, Date endDate, int pageNum, int pageSize) throws DaoException;
	
	/**
	 * 获取指定部门在指定日期范围内的所有项目的工作量汇总
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return Load
	 */
	public Load getProjectLoadSummary (int deptId, Date startDate, Date endDate) throws DaoException;
	
	/**
	 * 获取指定部门在指定日期范围内的所有项目工作量
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return List<ProjectLoad>
	 * @throws DaoException
	 */
	public List<ProjectLoad> getProjectLoads (int deptId, Date startDate, Date endDate) throws DaoException;

	/**
	 * 获取指定部门在指定日期范围内的员工各个项目的工作量
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
	public Page getEmployeeProjectLoads (int deptId, Date startDate, Date endDate, int pageNum, int pageSize) throws DaoException;

	/**
	 * 获取指定部门在指定日期范围内的子部门的工作量
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
	public Page getSubDepartmentLoads (int deptId, Date startDate, Date endDate, int pageNum, int pageSize) throws DaoException;
	
	/**
	 * 获取指定部门在指定日期范围内的所有子部门的工作量
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return List<DepartmentLoadVo>
	 * @throws DaoException
	 */
	public List<DepartmentLoadVo> getSubDepartmentLoads (int deptId, Date startDate, Date endDate) throws DaoException;
	
	/**
	 * 获取指定部门在指定日期范围内的所有子部门的工作量汇总
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws DaoException
	 */
	public DepartmentLoadVo getSubDepartmentLoadSummary (int deptId, Date startDate, Date endDate) throws DaoException;

	/**
	 * 获取指定部门所有下属人员在指定时间段内的项目参与情况
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws DaoException
	 */
	public List<EmployeeProject> getEmployeeProject(int deptId, Date startDate, Date endDate) throws DaoException;
	
	/**
	 * 获取指定部门（包括所有下属部门）在指定时间段内的项目时间信息
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws DaoException
	 */
	public List<ProjectPeriod> getProjectPeriod(int deptId, Date startDate, Date endDate) throws DaoException;
	
	/**
	 * 获取所有部门
	 * @return List<DepartmentVo>
	 * @throws DaoException
	 */
	public List<DepartmentVo> getDepartments () throws DaoException;
	
	/**
	 * 获取所有最低级部门
	 * @return List<DepartmentVo>
	 * @throws DaoException
	 */
	public List<DepartmentVo> getLowestLevelDepartments () throws DaoException;
	
	/**
	 * 获取所有最低级的上一级部门
	 * @return List<DepartmentVo>
	 * @throws DaoException
	 */
	public List<DepartmentVo> getLowestSecondLevelDepartments () throws DaoException;
}
