package com.neusoft.dms.proj.dao;

import java.util.Date;
import java.util.List;

import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.proj.domain.PrpLoad;
import com.neusoft.dms.proj.domain.ProjectVo;
import com.neusoft.dms.util.DaoException;

public interface ProjectLoadDao {

	/**
	 * 获取指定项目在指定日期范围内的里程碑
	 * @param projectId
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @return Page
	 */
	public Page getPrpLoads (int projectId, Date startDate, Date endDate, int pageNum, int pageSize) throws DaoException;
	
	/**
	 * 获取指定项目在指定日期范围内的所有里程碑的工作量汇总
	 * @param projectId
	 * @param startDate
	 * @param endDate
	 * @return Load
	 */
	public Load getPrpLoadSummary (int projectId, Date startDate, Date endDate) throws DaoException;
	
	/**
	 * 获取指定项目在指定日期范围内的各个里程碑的工作量
	 * @param projectId
	 * @param startDate
	 * @param endDate
	 * @return List<PrpLoad>
	 */
	public List<PrpLoad> getPrpLoads (int projectId, Date startDate, Date endDate) throws DaoException;
	
	/**
	 * 获取指定项目在指定日期范围内的相关部门工作量
	 * @param projectId
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 */
	public Page getDepartmentLoads (int projectId, Date startDate, Date endDate, int pageNum, int pageSize) throws DaoException;
	
	/**
	 * 获取指定项目在指定日期范围内的所有相关部门工作量汇总
	 * @param projectId
	 * @param startDate
	 * @param endDate
	 * @return Load
	 */
	public Load getDepartmentLoadSummary (int projectId, Date startDate, Date endDate) throws DaoException;
	
	/**
	 * 获取指定项目在指定日期范围内的所有相关部门的各个里程碑工作量
	 * @param projectId
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 */
	public Page getDepartmentPrpLoads (int projectId, Date startDate, Date endDate, int pageNum, int pageSize) throws DaoException;

	/**
	 * 获取所有正在运行中的项目
	 * @return List<ProjectVo>
	 */
	public List<ProjectVo> getRunningProjects () throws DaoException;
	
}
