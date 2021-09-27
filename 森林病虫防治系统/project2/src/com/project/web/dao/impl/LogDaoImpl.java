package com.project.web.dao.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.LogBean;
import com.project.web.dao.ILogDao;
import com.project.web.util.MyBatisUtil;
import com.project.web.util.pageNumCount;

public class LogDaoImpl implements ILogDao{

	@Override
	public CutPageBean<LogBean> findByCondition(int pageNo, Date startDate, Date endDate) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", (pageNo-1)*pageNumCount.EACH_NUM);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("eachNum", pageNumCount.EACH_NUM);
		CutPageBean<LogBean> cut = new CutPageBean<LogBean>();
		cut.setTotalPageNum(pageNumCount.countPage(findByCount(startDate, endDate)));
		List<LogBean> list = session.selectList("com.project.web.mapping.logMapper.findByCondition", map);
		cut.setList(list);
		session.close();
		return cut;
	}

	@Override
	public void addLog(LogBean bean) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("log", bean.getLog());
		map.put("date", bean.getDate());
		session.insert("com.project.web.mapping.logMapper.addLogMapper", map);
	}

	@Override
	public int findByCount(Date startDate, Date endDate) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		int num = session.selectOne("com.project.web.mapping.logMapper.logPageNo", map);
		return num;
	}

}
