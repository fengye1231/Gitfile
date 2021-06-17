package com.neusoft.dms.proj.service;

import java.util.List;

import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.proj.domain.ProjectVo;
import com.neusoft.dms.util.ServiceException;

public interface ProjectLoadService {

	/**
	 * 按里程碑查询
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table queryByPrp(PageQuery query) throws ServiceException;
	
	/**
	 * 按部门查询
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table queryByDepartment(PageQuery query) throws ServiceException;
	
	/**
	 * 项目投入汇总
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table summary(PageQuery query) throws ServiceException;
	
	/**
	 * 获取所有正在运行中的项目
	 * @return List<ProjectVo>
	 * @throws ServiceException
	 */
	public List<ProjectVo> getRunningProject() throws ServiceException;
}
