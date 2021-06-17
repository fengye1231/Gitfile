package com.neusoft.dms.daily.entry.service;

import java.util.Date;
import java.util.List;

import com.neusoft.dms.daily.entry.domain.Daily;
import com.neusoft.dms.daily.entry.domain.DailyCon;
import com.neusoft.dms.daily.entry.domain.DailyVo;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.prj.domain.PrjEmpVo;
import com.neusoft.dms.prj.domain.PrjPrpVo;
import com.neusoft.dms.proj.domain.ProjectVo;
import com.neusoft.dms.util.ServiceException;

public interface DailyService {
	
	//添加日报
	public boolean addDaily(Daily daily) throws ServiceException;
	
	//根据条件查询日报
	public Page listDaily(Daily daily,Date sdate,Date edate, int pageNum,int PageSize) throws ServiceException;
	public Page listDaily(DailyCon dailyCon) throws ServiceException;
	//根据条件查询日报
	public Page listCheckDaily(Daily daily,Date sdate,Date edate, int pageNum,int PageSize) throws ServiceException;
	public Page listCheckDaily(DailyCon dailyCon) throws ServiceException;
	//删除日报
	public boolean delDaily(int dailyId) throws ServiceException;

	//根据ID获取日报信息
	public DailyVo getDailyById(int dailyId)throws ServiceException;

	//修改日报信息
	public boolean updateDaily(Daily daily)throws ServiceException;
	//审核日报
	public boolean checkDaily(Daily daily) throws ServiceException;
	//批量通过日报
	public boolean passDaily(Daily daily) throws ServiceException;
	//批量不通过
	public boolean failDaily(Daily daily) throws ServiceException;
	//根据用户id获取所在的项目
	public List<PrjEmpVo> getPrjsEmp(int empID) throws ServiceException;
	public PrjEmpVo getPrjEmp(int empID) throws ServiceException;
	//根据用户所选的项目获取PRP阶段
	public List<PrjPrpVo> getPrp(int pId) throws ServiceException;
	
	//根据项目编号获取日报信息
	public List<DailyVo> getDailyByPrj(int prjId)throws ServiceException;
}
