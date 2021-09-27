package com.project.web.service.impl;

import java.sql.Date;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.EventBean;
import com.project.web.dao.IEventDao;
import com.project.web.dao.impl.EventDaoImpl;
import com.project.web.service.IEventService;

public class EventServiceImpl implements IEventService{
     
	final static int each_num=5;
	IEventDao imp=new EventDaoImpl();
	@Override
	public void addEvent(EventBean bean) {
	            imp.AddEventBean(bean);
	}

	@Override
	public EventBean chooseEvent(int id) {
		// TODO Auto-generated method stub
		   
		return imp.findEventBean(id);
	}

	@Override
	public void applyProfessor(int id) {
		// TODO Auto-generated method stub
		imp.UpdatePhase(id);
	}

	@Override
	public void updateEvent(int ID, String phase, String plan) {
		imp.UpdateEvent(ID, phase, plan);
		
	}

	@Override
	public CutPageBean<EventBean> Cutbean(int page,String name, String position, String location,
			Date beagin, Date end) {
		// TODO Auto-generated method stub
		   return imp.cutPage(page, each_num, name, position, location, beagin, end);
	}

	@Override
	public void applyEvent(int id) {
	          imp.UpdatePhase(id);
	}

}
