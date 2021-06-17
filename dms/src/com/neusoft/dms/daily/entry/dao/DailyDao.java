package com.neusoft.dms.daily.entry.dao;

import java.util.Date;
import java.util.List;

import com.neusoft.dms.daily.entry.domain.Daily;
import com.neusoft.dms.daily.entry.domain.DailyCon;
import com.neusoft.dms.daily.entry.domain.DailyVo;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

public interface DailyDao {
	//添加日报
	public void addDaily(Daily daily) throws DaoException;
	public DailyVo checkAddDaily(Daily daily)throws DaoException;
	//根据条件查询日报列表
	public Page listDaily(Daily daily,Date sdate,Date edate, int pageNum,int PageSize) throws DaoException;
	public Page listDaily(DailyCon dailyCon) throws DaoException;
	public Page listCheckDaily(Daily daily, Date sdate, Date edate, int pageNum,int PageSize)throws DaoException;
	public Page listCheckDaily(DailyCon dailyCon) throws DaoException;
	//删除日报
	public void delDaily(int dailyId) throws DaoException;
	//根据ID查询日报信息
	public DailyVo getDailyById(int dailyId)throws DaoException;
	//修改日报信息
	public void updateDaily(Daily daily)throws DaoException;
	//审核日报
	public void checkDaily(Daily daily)throws DaoException;
	//批量审核
	public void passDaily(Daily daily)throws DaoException;
	public void failDaily(Daily daily)throws DaoException;
	//根据日期查询日报
	public DailyVo getDailyBysubmitDate(Date sdate,Date edate,int empId)throws DaoException;
	//根据项目查询日报
	public DailyVo getDailyByprj(Date sdate,Date edate,int prjId) throws DaoException;
	//个人未写日报查询
	public DailyVo getUnsubmitDaily(int EmpId) throws DaoException;
	//个人周报导出
	public void downloadDaily(Date date,int empId) throws DaoException;
	public List<DailyVo> getDailyByPrj(int prjId)throws DaoException;
	//转化id为名字
	public DailyVo getNameById(DailyVo daily)throws DaoException;
}
