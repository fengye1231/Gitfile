package com.project.web.dao;

import java.sql.Date;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.LogBean;
/**
 * 日志持久层接口
 * @author TE
 *
 */
public interface ILogDao {
	/**
	 * 根据业务层传入的数据进行动态条件查询日志集合
	 * @param pageNo 页码
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @return 返回符合条件的日志集合
	 */
public CutPageBean<LogBean> findByCondition(int pageNo,Date startDate,Date endDate);
/**
 * 根据业务层传入的数据添加添加日志信息
 * @param bean 业务层传入的日志信息
 */
public void addLog(LogBean bean);
/**
 * 根据业务层传入的数据查询有多少数据
 * @param startDate
 * @param endDate
 * @return
 */
public int findByCount(Date startDate,Date endDate);

}
