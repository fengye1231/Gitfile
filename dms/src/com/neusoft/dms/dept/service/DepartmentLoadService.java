package com.neusoft.dms.dept.service;

import java.util.Date;
import java.util.List;

import com.neusoft.dms.dept.domain.DepartmentVo;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.util.ServiceException;
import org.apache.poi.ss.usermodel.Workbook;

public interface DepartmentLoadService {

	/**
	 * 按项目查询
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table queryByProject(PageQuery query) throws ServiceException;
	
	/**
	 * 项目投入汇总
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table summary(PageQuery query) throws ServiceException;
	
	/**
	 * 获取部门工作量
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table getDepartmentLoads(PageQuery query) throws ServiceException;
	
	/**
	 * 获取部门工作量的表格对象
	 * @param query
	 * @return Workbook
	 * @throws ServiceException
	 */
	public Workbook getDepartmentLoadsWorkBook(int deptId, Date startDate, Date endDate) throws ServiceException;
	
	/**
	 * 获取所有部门
	 * @return List<DepartmentVo>
	 * @throws ServiceException
	 */
	public List<DepartmentVo> getDepartments() throws ServiceException;
	
	/**
	 * 获取所有最低级部门
	 * @return List<DepartmentVo>
	 * @throws ServiceException
	 */
	public List<DepartmentVo> getLowestLevelDepartments() throws ServiceException;
	
	/**
	 * 获取所有最低级部门的上一级部门
	 * @return List<DepartmentVo>
	 * @throws ServiceException
	 */
	public List<DepartmentVo> getLowestSecondLevelDepartments() throws ServiceException;

}
