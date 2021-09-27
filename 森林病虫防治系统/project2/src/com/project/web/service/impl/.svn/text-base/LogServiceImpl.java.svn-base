package com.project.web.service.impl;

import java.sql.Date;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.LogBean;
import com.project.web.dao.ILogDao;
import com.project.web.dao.IUserDao;
import com.project.web.dao.impl.LogDaoImpl;
import com.project.web.service.ILogServlet;

public class LogServiceImpl implements ILogServlet{
	private ILogDao ild = new LogDaoImpl();
	@Override
	public CutPageBean<LogBean> findByCondition(int pageNo, Date startDate, Date endDate) {
		return ild.findByCondition(pageNo, startDate, endDate);
	}

}
