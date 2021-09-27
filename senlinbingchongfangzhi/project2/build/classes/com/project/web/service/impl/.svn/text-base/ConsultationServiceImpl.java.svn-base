package com.project.web.service.impl;

import java.util.List;
import com.project.web.bean.ConsultationBean;
import com.project.web.bean.EventBean;
import com.project.web.dao.IConsultationDao;
import com.project.web.dao.impl.ConsultationDaoImpl;
import com.project.web.service.IConsultationService;

/**
 * 专家会商业务层实现类
 * @author GF
 *
 */
public class ConsultationServiceImpl implements IConsultationService{
	IConsultationDao dao = new ConsultationDaoImpl();

	@Override
	public void addConsultation(ConsultationBean bean) {
		// TODO Auto-generated method stub
		dao.addConsultation(bean);
	}

	@Override
	public List<ConsultationBean> findAll(int id) {
		// TODO Auto-generated method stub
		return dao.findAll(id);
	}
	
	@Override
	public List<EventBean> findEventNeed(){
		return dao.findEventNeed();	
	}

	@Override
	public EventBean findEventById(int id) {
		return dao.findEventById(id);
	}

}
