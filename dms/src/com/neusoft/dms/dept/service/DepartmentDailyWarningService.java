package com.neusoft.dms.dept.service;

import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.util.ServiceException;

public interface DepartmentDailyWarningService {

	/**
	 * 未写日报查询
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table queryUnsubmit(PageQuery query) throws ServiceException;
	
	/**
	 * 未审核日报查询
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table queryUncheck(PageQuery query) throws ServiceException;
	
	/**
	 * 未提交日报提醒
	 * @param deptId
	 * @throws ServiceException
	 */
	public void warnUnsubmit(int deptId) throws ServiceException;

	/**
	 * 未审核日报提醒
	 * @param deptId
	 * @throws ServiceException
	 */
	public void warnUncheck(int deptId) throws ServiceException;

	/**
	 * 判断是否有未提交日报提醒
	 * @param empId
	 * @return
	 * @throws ServiceException
	 */
	public boolean hasUnsubmitWarning(int empId) throws ServiceException;
	
	/**
	 * 判断是否有未审核日报提醒
	 * @param empId
	 * @return
	 * @throws ServiceException
	 */
	public boolean hasUncheckWarning(int empId) throws ServiceException;
	
	/**
	 * 清除未提交日报提醒
	 * @param empId
	 * @throws ServiceException
	 */
	public void clearUnsubmitWarning(int empId) throws ServiceException;
	
	/**
	 * 清除未审核日报提醒
	 * @param empId
	 * @throws ServiceException
	 */
	public void clearUncheckWarning(int empId) throws ServiceException;
}
