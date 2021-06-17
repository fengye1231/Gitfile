package com.neusoft.dms.dept.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.neusoft.dms.dept.dao.DepartmentLoadDao;
import com.neusoft.dms.dept.dao.DepartmentLoadDaoImpl;
import com.neusoft.dms.dept.domain.DepartmentLoadVo;
import com.neusoft.dms.dept.domain.DepartmentVo;
import com.neusoft.dms.dept.domain.EmployeeProjectLoad;
import com.neusoft.dms.dept.domain.ProjectLoad;
import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.domain.TableFooterCell;
import com.neusoft.dms.domain.TableHeaderCell;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

@SuppressWarnings("unchecked")
public class DepartmentLoadServiceImpl implements DepartmentLoadService {

	private static DepartmentLoadServiceImpl instance = new DepartmentLoadServiceImpl();
	
	public static DepartmentLoadServiceImpl getInstance() {
		return instance;
	}
	
	private DepartmentLoadServiceImpl() {}
	
	@Override
	public Table queryByProject(PageQuery query) throws ServiceException {
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			int deptId = (Integer) query.getParam("deptId");
			Date startDate = (Date) query.getParam("startDate");
			Date endDate = (Date) query.getParam("endDate");
			
			con = DBUtil.getConnection();
			DepartmentLoadDao loadDao = new DepartmentLoadDaoImpl(con);
			
			Page loadsPage = loadDao.getProjectLoads(deptId, startDate, endDate, pageNum, pageSize);
			Load loadSummary = loadDao.getProjectLoadSummary(deptId, startDate, endDate);

			Table result = new Table(loadsPage);
			result.addFooterCell(new TableFooterCell("projectName", "汇总"))
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
	public Table summary(PageQuery query) throws ServiceException {
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();			
			int deptId = (Integer) query.getParam("deptId");
			Date startDate = (Date) query.getParam("startDate");
			Date endDate = (Date) query.getParam("endDate");
			
			con = DBUtil.getConnection();
			DepartmentLoadDao loadDao = new DepartmentLoadDaoImpl(con);
			
			Page loadsPage = loadDao.getEmployeeProjectLoads(deptId, startDate, endDate, pageNum, pageSize);
			List<ProjectLoad> projectLoads = loadDao.getProjectLoads(deptId, startDate, endDate);

			//表格适配
			float summary = 0;
			List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();
			for (EmployeeProjectLoad epLoad : (List<EmployeeProjectLoad>) loadsPage.getList()) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("employeeName", epLoad.getEmployeeName());
				row.put("summary", epLoad.getSummary().getLoad());
				for (ProjectLoad mLoad : epLoad.getProjectLoads()) {
					row.put("project" + mLoad.getProjectId(), mLoad.getLoad());
				}
				rowList.add(row);
			}
			Page page = new Page(rowList, loadsPage.getTotalNum(), loadsPage.getPageNum(), 
					loadsPage.getTotalPage(), loadsPage.getPageSize());
			Table result = new Table(page);
			result.addHeaderCell(new TableHeaderCell("employeeName", "人员"));
			result.addFooterCell(new TableFooterCell("employeeName", "汇总"));
			for (ProjectLoad pLoad : projectLoads) {
				summary += pLoad.getLoad();
				result.addHeaderCell(new TableHeaderCell("project" + pLoad.getProjectId(), pLoad.getProjectName()));
				result.addFooterCell(new TableFooterCell("project" + pLoad.getProjectId(), pLoad.getLoad()));
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
	public Table getDepartmentLoads(PageQuery query) throws ServiceException {
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			int deptId = (Integer) query.getParam("deptId");
			Date startDate = (Date) query.getParam("startDate");
			Date endDate = (Date) query.getParam("endDate");
			
			con = DBUtil.getConnection();
			DepartmentLoadDao loadDao = new DepartmentLoadDaoImpl(con);
			
			Page loadsPage = loadDao.getSubDepartmentLoads(deptId, startDate, endDate, pageNum, pageSize);
			DepartmentLoadVo loadSummary = loadDao.getSubDepartmentLoadSummary(deptId, startDate, endDate);

			Table result = new Table(loadsPage);
			result.addFooterCell(new TableFooterCell("departmentName", "汇总"))
				.addFooterCell(new TableFooterCell("dailyNum", loadSummary.getDailyNum()))
				.addFooterCell(new TableFooterCell("load", loadSummary.getLoad()))
				.addFooterCell(new TableFooterCell("extraLoad", loadSummary.getExtraLoad()))
				.addFooterCell(new TableFooterCell("unacceptedLoad", loadSummary.getUnacceptedLoad()));
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
	public Workbook getDepartmentLoadsWorkBook(int deptId, Date startDate, Date endDate)
			throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentLoadDao loadDao = new DepartmentLoadDaoImpl(con);
			
			List<DepartmentLoadVo> loads = loadDao.getSubDepartmentLoads(deptId, startDate, endDate);

			return getWorkBook(loads);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	
	@Override
	public List<DepartmentVo> getDepartments() throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentLoadDao loadDao = new DepartmentLoadDaoImpl(con);
			List<DepartmentVo> departments = loadDao.getDepartments();
			return departments;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	@Override
	public List<DepartmentVo> getLowestLevelDepartments()
			throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentLoadDao loadDao = new DepartmentLoadDaoImpl(con);
			List<DepartmentVo> departments = loadDao.getLowestLevelDepartments();
			return departments;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }	
		}
	}

	@Override
	public List<DepartmentVo> getLowestSecondLevelDepartments()
			throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DepartmentLoadDao loadDao = new DepartmentLoadDaoImpl(con);
			List<DepartmentVo> departments = loadDao.getLowestSecondLevelDepartments();
			return departments;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	
	private Workbook getWorkBook(List<DepartmentLoadVo> loads) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();

		CellStyle titleCellStyle = workbook.createCellStyle();
		CellStyle contentCellStyle = workbook.createCellStyle();

		Font titleFont = workbook.createFont();
		Font contentFont = workbook.createFont();
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		titleFont.setFontHeightInPoints((short) 12);
		contentFont.setFontHeightInPoints((short) 12);
		
		titleCellStyle.setFont(titleFont);
		titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		contentCellStyle.setFont(contentFont);
		contentCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		Row row = null;
		Cell cell = null;
		
		row = sheet.createRow(0);
		row.setHeightInPoints(26);

		String[] titles = {"编号", "子部门", "有效日报", "有效工作时间", "加班工作量", "未纳入项目工作量"};
		for (int i = 0; i < titles.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(titleCellStyle);
			//sheet.setColumnWidth(i, titles[i].getBytes().length*2*256);
			sheet.setColumnWidth(i, 7000);
		}

		for (int i = 0; i < loads.size(); i++) {
			DepartmentLoadVo dLoad = loads.get(i);
			row = sheet.createRow(i + 1);
			row.setHeightInPoints(20);
			cell = row.createCell(0);
			cell.setCellValue(row.getRowNum());
			cell.setCellStyle(contentCellStyle);
			cell = row.createCell(1);
			cell.setCellValue(dLoad.getDepartmentName());
			cell.setCellStyle(contentCellStyle);
			cell = row.createCell(2);
			cell.setCellValue(dLoad.getDailyNum());
			cell.setCellStyle(contentCellStyle);
			cell = row.createCell(3);
			cell.setCellValue(dLoad.getLoad());
			cell.setCellStyle(contentCellStyle);
			cell = row.createCell(4);
			cell.setCellValue(dLoad.getExtraLoad());
			cell.setCellStyle(contentCellStyle);
			cell = row.createCell(5);
			cell.setCellValue(dLoad.getUnacceptedLoad());
			cell.setCellStyle(contentCellStyle);
		}
		
		if (loads.size() > 0) {
			row = sheet.createRow(loads.size() + 1);
			row.setHeightInPoints(26);
			cell = row.createCell(0);
			cell.setCellValue(row.getRowNum());
			cell.setCellStyle(titleCellStyle);
			cell = row.createCell(1);
			cell.setCellValue("汇总 ");
			cell.setCellStyle(titleCellStyle);
			String[] colIndex = {"A", "B", "C", "D", "E", "F"};
			for (int i = 2; i < 6; i++) {
				String formula = "SUM(" + colIndex[i] + "2:" + colIndex[i] + row.getRowNum() + ")";
				cell = row.createCell(i);
				cell.setCellFormula(formula);
				cell.setCellStyle(titleCellStyle);
			}
		}
		
		workbook.setSheetName(0, "部门工作量");

		sheet.setForceFormulaRecalculation(true);
		
		return workbook;
	}

}
