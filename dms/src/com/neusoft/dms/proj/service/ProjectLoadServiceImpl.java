package com.neusoft.dms.proj.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.domain.TableFooterCell;
import com.neusoft.dms.domain.TableHeaderCell;
import com.neusoft.dms.proj.dao.ProjectLoadDao;
import com.neusoft.dms.proj.dao.ProjectLoadDaoImpl;
import com.neusoft.dms.proj.domain.DepartmentPrpLoad;
import com.neusoft.dms.proj.domain.PrpLoad;
import com.neusoft.dms.proj.domain.ProjectVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

public class ProjectLoadServiceImpl implements ProjectLoadService {

	private static ProjectLoadServiceImpl instance = new ProjectLoadServiceImpl();
	
	public static ProjectLoadServiceImpl getInstance() {
		return instance;
	}
	
	private ProjectLoadServiceImpl() {}

	@Override
	public Table queryByDepartment(PageQuery query) throws ServiceException {
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			int projectId = (Integer) query.getParam("projectId");
			Date startDate = (Date) query.getParam("startDate");
			Date endDate = (Date) query.getParam("endDate");
			
			con = DBUtil.getConnection();
			ProjectLoadDao loadDao = new ProjectLoadDaoImpl(con);
			
			Page loadsPage = loadDao.getDepartmentLoads(projectId, startDate, endDate, pageNum, pageSize);
			Load loadSummary = loadDao.getDepartmentLoadSummary(projectId, startDate, endDate);

			Table result = new Table(loadsPage);
			result.addFooterCell(new TableFooterCell("departmentName", "汇总"))
				.addFooterCell(new TableFooterCell("load", loadSummary.getLoad()))
				.addFooterCell(new TableFooterCell("extraLoad", loadSummary.getExtraLoad()));
			return result;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@Override
	public Table queryByPrp(PageQuery query) throws ServiceException {
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();			
			int projectId = (Integer) query.getParam("projectId");
			Date startDate = (Date) query.getParam("startDate");
			Date endDate = (Date) query.getParam("endDate");
			
			con = DBUtil.getConnection();
			ProjectLoadDao loadDao = new ProjectLoadDaoImpl(con);
			
			Page loadsPage = loadDao.getPrpLoads(projectId, startDate, endDate, pageNum, pageSize);
			Load loadSummary = loadDao.getPrpLoadSummary(projectId, startDate, endDate);

			Table result = new Table(loadsPage);
			result.addFooterCell(new TableFooterCell("prpName", "汇总"))
				.addFooterCell(new TableFooterCell("load", loadSummary.getLoad()))
				.addFooterCell(new TableFooterCell("extraLoad", loadSummary.getExtraLoad()));
			return result;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Table summary(PageQuery query) throws ServiceException {
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();			
			int projectId = (Integer) query.getParam("projectId");
			Date startDate = (Date) query.getParam("startDate");
			Date endDate = (Date) query.getParam("endDate");
			
			con = DBUtil.getConnection();
			ProjectLoadDao loadDao = new ProjectLoadDaoImpl(con);
			
			Page loadsPage = loadDao.getDepartmentPrpLoads(projectId, startDate, endDate, pageNum, pageSize);
			List<PrpLoad> prpLoads = loadDao.getPrpLoads(projectId, startDate, endDate);
			
			//表格适配
			float summary = 0;
			List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();
			for (DepartmentPrpLoad dmLoad : (List<DepartmentPrpLoad>) loadsPage.getList()) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("departmentName", dmLoad.getDepartmentName());
				row.put("summary", dmLoad.getSummary().getLoad());
				for (PrpLoad mLoad : dmLoad.getPrpLoads()) {
					row.put("prp" + mLoad.getPrpId(), mLoad.getLoad());
				}
				rowList.add(row);
			}
			Page page = new Page(rowList, loadsPage.getTotalNum(), loadsPage.getPageNum(), 
					loadsPage.getTotalPage(), loadsPage.getPageSize());
			Table result = new Table(page);
			result.addHeaderCell(new TableHeaderCell("departmentName", "部门"));
			result.addFooterCell(new TableFooterCell("departmentName", "汇总"));
			for (PrpLoad mLoad : prpLoads) {
				result.addHeaderCell(new TableHeaderCell("prp" + mLoad.getPrpId(), mLoad.getPrpName()));
				result.addFooterCell(new TableFooterCell("prp" + mLoad.getPrpId(), mLoad.getLoad()));
				summary += mLoad.getLoad();				
			}
			result.addHeaderCell(new TableHeaderCell("summary", "汇总"));
			result.addFooterCell(new TableFooterCell("summary", summary));
			return result;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@Override
	public List<ProjectVo> getRunningProject() throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			ProjectLoadDao loadDao = new ProjectLoadDaoImpl(con);
			
			List<ProjectVo> projects = loadDao.getRunningProjects();

			return projects;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	


}
