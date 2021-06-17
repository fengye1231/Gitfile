package com.neusoft.dms.personal.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.neusoft.dms.dept.dao.DepartmentLoadDao;
import com.neusoft.dms.dept.dao.DepartmentLoadDaoImpl;
import com.neusoft.dms.dept.domain.DepartmentLoadVo;
import com.neusoft.dms.domain.Load;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.domain.TableFooterCell;
import com.neusoft.dms.personal.dao.PersonalDao;
import com.neusoft.dms.personal.dao.PersonalDaoImpl;
import com.neusoft.dms.personal.domain.DailyVo;
import com.neusoft.dms.personal.domain.DateLoad;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;


public class PersonalServiceImpl implements PersonalService{
	private static PersonalServiceImpl instance = new PersonalServiceImpl();

	/**
	 * 单例模式取得实例
	 * 
	 * @return 实例对象
	 */
	public static PersonalServiceImpl getInstance() {
		return instance;
	}

	private PersonalServiceImpl() {

	}
	
	//展示个人日报
	public List<DailyVo> showDailyReport(int empId,Date date) throws ServiceException{
		Connection con = null;
		try{		
			con = DBUtil.getConnection();
			PersonalDao showDailyDao = new PersonalDaoImpl(con);
			
			List<DailyVo> showDaily = showDailyDao.showDailyReport(empId,date);
			return showDaily;
		} catch (DaoException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
		return null;
}
		
	

	//获取人员
	public List<EmployeeVo> getEmployee(int empId) throws ServiceException {

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PersonalDao loadDao = new PersonalDaoImpl(con);
			
			List<EmployeeVo> employee = loadDao.getSubEmployee(empId);
			
			return employee;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}

	
	//按日期查询
	@Override
	public Table queryBySubmitDate(PageQuery query){
		Connection con = null;
		Table result = null;
				try {
					int pageNum = query.getPageNum();
					int pageSize = query.getPageSize();
					int empId = (Integer) query.getParam("EmpId");
					Date startDate = (Date) query.getParam("startDate");
					Date endDate = (Date) query.getParam("endDate");
					
					con = DBUtil.getConnection();
					PersonalDao dateloadDao = new PersonalDaoImpl(con);
					
					Page loadsPage = dateloadDao.getDateLoads(empId, startDate, endDate, pageNum, pageSize);
					Load loadSummary;
					loadSummary = dateloadDao.getDateLoadsSummary(empId, startDate, endDate);
					
					result = new Table(loadsPage);
					result.addFooterCell(new TableFooterCell("submitDate", "汇总"))
						.addFooterCell(new TableFooterCell("load", loadSummary.getLoad()))
						.addFooterCell(new TableFooterCell("extraLoad", loadSummary.getExtraLoad()));
				} catch (DaoException e) {
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try { DBUtil.close(con); } 
					catch (DaoException e) { e.printStackTrace(); }
				}
				return result;
	}
	


	//按项目查询
	@Override
	public Table queryByProject(PageQuery query) throws ServiceException {
		
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			int empId = (Integer) query.getParam("EmpId");
			Date startDate = (Date) query.getParam("startDate");
			Date endDate = (Date) query.getParam("endDate");
			
			con = DBUtil.getConnection();
			PersonalDao projectloadDao = new PersonalDaoImpl(con);
			
			Page loadsPage = projectloadDao.getProjectLoads(empId, startDate, endDate, pageNum, pageSize);
			Load loadSummary = projectloadDao.getProjectLoadsSummary(empId, startDate, endDate);
			
			Table result = new Table(loadsPage);
			result.addFooterCell(new TableFooterCell("projectName", "汇总"))
				.addFooterCell(new TableFooterCell("load", loadSummary.getLoad()))
				.addFooterCell(new TableFooterCell("extraLoad", loadSummary.getExtraLoad()));
			return result;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	
	//导出个人周报
	public Workbook getWeekDailyWorkBook(int empId, Date submitDate)
			throws ServiceException {
		Connection con = null;
		try {
			
			con = DBUtil.getConnection();
			PersonalDao personalDao = new PersonalDaoImpl(con);
			
			List<DailyVo> loads = personalDao.getWeekDaily(empId, submitDate);

			return getWorkBook(loads);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	
	
	private Workbook getWorkBook(List<DailyVo> loads) throws IOException {
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

	String[] titles = {"编号", "日期", "工作量", "加班工作量", "项目", "阶段","工作内容"};
	for (int i = 0; i < titles.length; i++) {
		cell = row.createCell(i);
		cell.setCellValue(titles[i]);
		cell.setCellStyle(titleCellStyle);
		//sheet.setColumnWidth(i, titles[i].getBytes().length*2*256);
		sheet.setColumnWidth(i, 7000);
	}
	
	CellStyle cellStyle = workbook.createCellStyle();
	cellStyle.cloneStyleFrom(contentCellStyle);
	DataFormat format= workbook.createDataFormat();
	cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));

	for (int i = 0; i < loads.size(); i++) {
		DailyVo daily = loads.get(i);
		row = sheet.createRow(i + 1);
		row.setHeightInPoints(20);
		cell = row.createCell(0);
		cell.setCellValue(row.getRowNum());
		cell.setCellStyle(contentCellStyle);
		cell = row.createCell(1);
		cell.setCellValue(daily.getSubmitDate());
		cell.setCellStyle(cellStyle);
		cell = row.createCell(2);
		cell.setCellValue(daily.getTotalWorkload());
		cell.setCellStyle(contentCellStyle);
		cell = row.createCell(3);
		cell.setCellValue(daily.getOverTimeLoad());
		cell.setCellStyle(contentCellStyle);
		cell = row.createCell(4);
		cell.setCellValue(daily.getPrjName());
		cell.setCellStyle(contentCellStyle);
		cell = row.createCell(5);
		cell.setCellValue(daily.getPrpName());
		cell.setCellStyle(contentCellStyle);
		cell = row.createCell(6);
		cell.setCellValue(daily.getDesc());
		cell.setCellStyle(contentCellStyle);
	}
	
	workbook.setSheetName(0, "个人周报");

	sheet.setForceFormulaRecalculation(true);
	
	return workbook;
}
	
	//查询个人周报
	public Table getWeekDaily(PageQuery query)throws ServiceException{
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			int empId = (Integer) query.getParam("empId");
			Date submitDate = (Date) query.getParam("submitDate");
			
			con = DBUtil.getConnection();
			PersonalDao loadDao = new PersonalDaoImpl(con);
			
			Page loadsPage = loadDao.getWeekDaily(empId, submitDate, pageNum, pageSize);

			Table result = new Table(loadsPage);
			
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
	public ArrayList<DailyVo> queryUnsubmitDaily(Date startdate) throws ServiceException {
		Connection con = null;
		ArrayList<DailyVo> daily = null;
		try {
			con = DBUtil.getConnection();
			PersonalDao loadDao = new PersonalDaoImpl(con);
			daily = loadDao.getDailyBySubmitDate(startdate);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return daily;
	}

}

