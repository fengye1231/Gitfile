package com.project.web.dao.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.OutBean;
import com.project.web.dao.IOutDao;
import com.project.web.util.MyBatisUtil;

public class OutDaoImpl implements IOutDao{

	@Override
	public List<OutBean> findProject(int num, int each_num, String className, Date starDate, Date endDate) {
		SqlSession session = MyBatisUtil.getSqlSession();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("num", (num-1)*each_num);
		map.put("each_num", each_num);
		map.put("className", className);
		map.put("starDate", starDate);
		map.put("endDate", endDate);
		List<OutBean> list = session.selectList("com.project.web.mapper.outMapper.findProject", map);
		session.close();
		return list;
	}

	@Override
	public int findProjectNum(String className, Date starDate, Date endDate) {
		SqlSession session = MyBatisUtil.getSqlSession();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("className", className);
		map.put("starDate", starDate);
		map.put("endDate", endDate);
		
		int num = session.selectOne("com.project.web.mapper.outMapper.findProjectNum",map);
		session.close();
		return num;
	}

	@Override
	public OutBean findById(int id) {
		SqlSession session = MyBatisUtil.getSqlSession();
		OutBean bean = session.selectOne("com.project.web.mapper.outMapper.findByid", id);
		session.close();
		return bean;
	}

	@Override
	public void addOut(OutBean bean) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.insert("com.project.web.mapper.outMapper.addOut", bean);
		
		session.close();
		
	}

}
