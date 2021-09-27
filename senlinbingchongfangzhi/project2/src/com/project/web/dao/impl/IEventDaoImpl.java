package com.project.web.dao.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.EventBean;
import com.project.web.dao.IEventDao;
import com.project.web.util.MyBatisUtil;

public class IEventDaoImpl implements IEventDao{

	@Override
	public void AddEventBean(EventBean bean) {
		 String way="com.project.web.mapper.eventMapper.addOne";
		SqlSession  session=MyBatisUtil.getSqlSession();
		Map<String,Object> map=new HashMap<String,Object>();
		
		session.insert(way,map);
		
	}

	@Override
	public EventBean findEventBean(int id) {
		String way="com.project.web.mapper.eventMapper.selectOne";
		SqlSession session =MyBatisUtil.getSqlSession();
		EventBean bean=session.selectOne(way,id);
		return bean;
	}

	@Override
	public void UpdatePhase(int id) {
		String way ="com.project.web.mapper.eventMapper.upadateP";
		SqlSession session=MyBatisUtil.getSqlSession(true);
		session.update(way,id);
		
	}

	@Override
	public void UpdateEvent(int id,String phase, String plan) {
	      String way="com.project.web.mapper.eventMapper.updateOne";
	      SqlSession session=MyBatisUtil.getSqlSession(true);
	      Map<String,Object> map=new HashMap<String,Object>();
	      map.put("id", id);
	      map.put("phase", phase);
	      map.put("plan", plan);
	      session.update(way,map);
		
	}

	@Override
	public CutPageBean<EventBean> cutPage(int page, int each_num, String name, String plan,String area, Date start,
			Date end) {
		// TODO Auto-generated method stub
		CutPageBean<EventBean> bean=new CutPageBean<EventBean>();
		String way="com.project.web.mapper.eventMapper.selectList";
		SqlSession session=MyBatisUtil.getSqlSession();
		Map<String,Object> map=new HashMap<String,Object>();
		int index_num=(page-1)*each_num;
		map.put("index_num", index_num);
		map.put("each_num", each_num);
		map.put("name", "\"%"+name+"%\"");
		map.put("phase", "\"%"+plan+"%\"");
		map.put("area","\"%"+area+"%\"");
		map.put("start", start);
		map.put("end", end);
	
	
		List<EventBean>list=session.selectList(way,map);
		System.out.println(list);
		bean.setList(list);
		
		String way1="com.project.web.mapper.eventMapper.selectCount";
		int totalPageNum=1;
		int pageNum= session.selectOne(way1,map);
		if(pageNum%each_num==0) {
			totalPageNum=pageNum/each_num;
			bean.setTotalPageNum(totalPageNum);
		}else if(pageNum%each_num>0) {
			totalPageNum=pageNum/each_num+1;
			bean.setTotalPageNum(totalPageNum);
		}
		return bean;
	}

//	public static void main(String[] args) {
//		CutPageBean<EventBean>page =new IEventDaoImpl().cutPage(1,5,"东南","控制","控",DateChange.getDate("2001-1-1"),DateChange.getDate("2018-1-1"));
//		
//		System.out.println(page);
//	}

	

}
