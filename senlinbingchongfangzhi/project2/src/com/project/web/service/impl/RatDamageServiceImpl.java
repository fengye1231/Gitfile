package com.project.web.service.impl;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.RatDamageBean;
import com.project.web.dao.IRatDamageDao;
import com.project.web.dao.impl.RatDamageDaoImpl;
import com.project.web.service.IRatDamageService;

public class RatDamageServiceImpl implements IRatDamageService{
     private IRatDamageDao irs = new RatDamageDaoImpl();
	@Override
	public void addRatDamage(RatDamageBean bean) {
		irs.addRatDamage(bean);
		
	}

	@Override
	public RatDamageBean findById(int id) {
		// TODO Auto-generated method stub
		return irs.findById(id);
	}

	@Override
	public CutPageBean<RatDamageBean> showAllRatDamage(int num, String ratDamageName) {
		// TODO Auto-generated method stub
		return irs.showAllRatDamage(num, ratDamageName);
	}

}
