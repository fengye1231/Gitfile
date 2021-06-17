package com.neusoft.dms.dept.dao;

import java.util.Date;
import java.util.List;

import com.neusoft.dms.dept.domain.EmployeeProjectDailyDo;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.util.DaoException;

public interface DepartmentDailyWarningDao {

	public enum Type {
		
		UNSUBMIT,
		UNCHECK;
		
		public String toString() {
			switch (this) {
			case UNSUBMIT:
				return "未提交";
			case UNCHECK:
				return "未审核";
			default:
				return null;
			}
		}
	};
	
	
	/**
	 * 获取指定部门所有下属人员在指定时间段内的日报提交情况
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Page getSubmitInfo(int deptId, Date startDate, Date endDate, int pageNum, int pageSize) throws DaoException;
	
	/**
	 * 获取指定部门（包括自身）在指定时间段内的未审核日报情况
	 * @param deptId
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Page getUncheck(int deptId, Date startDate, Date endDate, int pageNum, int pageSize) throws DaoException;

	/**
	 * 添加未提交日报提醒
	 * @param deptIds
	 * @throws DaoException
	 */
	public void addUnsubmitWarning(List<Integer> empIds) throws DaoException;
	
	/**
	 * 添加未审核日报提醒（全时间段）
	 * @param deptId
	 * @throws DaoException
	 */
	public void addUncheckWarning(int deptId) throws DaoException;
	
	/**
	 * 清除提醒
	 * @param empId
	 * @param type
	 * @throws DaoException
	 */
	public void clearWarning(int empId, Type type) throws DaoException;
	
	/**
	 * 检测提醒
	 * @param empId
	 * @param type
	 * @return
	 * @throws DaoException
	 */
	public boolean hasWarning(int empId, Type type) throws DaoException;
	
	/**
	 * 获取指定部门所有下属人员的日报提交数（工作日）
	 * @param deptId
	 * @return
	 * @throws DaoException
	 */
	public List<EmployeeProjectDailyDo> getSubmitCount(int deptId) throws DaoException;
	
}
