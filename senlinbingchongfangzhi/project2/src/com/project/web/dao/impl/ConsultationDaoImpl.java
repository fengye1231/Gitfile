package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.project.web.bean.ConsultationBean;
import com.project.web.bean.EventBean;
import com.project.web.dao.IConsultationDao;
import com.project.web.util.MyBatisUtil;

/*
 * 专家会商持久层实现类
 */
public class ConsultationDaoImpl implements IConsultationDao{

	@Override
	public void addConsultation(ConsultationBean bean) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("content", bean.getContent());
		map.put("result", bean.getResult());
		map.put("id", bean.getEventBean().getId());
		map.put("date", bean.getDate());
		session.insert("com.project.web.mapper.consultationMapper.addConsultation",map);
		session.close();
	}

	@Override
	public List<ConsultationBean> findAll(int id) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSession(true);
		List<ConsultationBean> list = session.selectList("com.project.web.mapper.consultationMapper.findAllConsultation", id);
		session.close();
		return list;
	}

	@Override
	public List<EventBean> findEventNeed(){
		SqlSession session = MyBatisUtil.getSqlSession(true);
		List<EventBean> list = session.selectList("com.project.web.mapper.eventMapper.selectMap");
		session.close();
		return list;	
	}

	@Override
	public EventBean findEventById(int id) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		EventBean bean = session.selectOne("com.project.web.mapper.eventMapper.selectOne", id);
		session.close();
		return bean;
	}

}
