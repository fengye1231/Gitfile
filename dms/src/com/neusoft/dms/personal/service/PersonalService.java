package com.neusoft.dms.personal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.personal.domain.DailyVo;
import com.neusoft.dms.personal.domain.DateLoad;
import com.neusoft.dms.util.ServiceException;

public interface PersonalService {
	
	
	
	//按日期查询
	public Table queryBySubmitDate(PageQuery query) throws ServiceException;
	
	//按部门查询
	public Table queryByProject(PageQuery query) throws ServiceException;
	
	//个人未写日报查询
	public ArrayList<DailyVo> queryUnsubmitDaily(Date startdate) throws ServiceException;
	
	//获取人员
	public List<EmployeeVo> getEmployee(int empId) throws ServiceException;
	
	//获取个人周报
	public Table getWeekDaily(PageQuery query)throws ServiceException;
	
	//导出个人周报
	public Workbook getWeekDailyWorkBook(int empId,Date submitDate)throws ServiceException;
	
	//展示个人日报
	public List<DailyVo> showDailyReport(int empId,Date date)throws ServiceException;

}
