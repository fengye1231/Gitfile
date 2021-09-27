package com.project.web.service;

import java.sql.Date;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.LogBean;

/**
 * 日志业务层接口
 * @author TE
 *
 */
public interface ILogServlet {
	/**
	 * 根据表现层传入的数据进行动态条件查询日志集合
	 * @param pageNo 页码
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @return 返回符合条件的日志集合
	 */
public CutPageBean<LogBean> findByCondition(int pageNo,Date startDate,Date endDate);
}
