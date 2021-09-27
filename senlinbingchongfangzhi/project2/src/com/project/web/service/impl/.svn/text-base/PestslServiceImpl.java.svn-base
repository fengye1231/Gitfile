package com.project.web.service.impl;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.PestslBean;
import com.project.web.dao.IPestslDao;
import com.project.web.dao.impl.PestslDaoImpl;
import com.project.web.service.IPestslService;

public class PestslServiceImpl implements IPestslService{
   private IPestslDao ipd = new PestslDaoImpl();
	@Override
	public void addPestsl(PestslBean bean) {
		ipd.addPestsl(bean);
		
	}

	@Override
	public PestslBean findById(int id) {
		// TODO Auto-generated method stub
		return ipd.findById(id);
	}

	@Override
	public CutPageBean<PestslBean> showAllPestsl(int num, String pestslName, String hostName) {
		// TODO Auto-generated method stub
		return ipd.showAllPestsl(num, pestslName, hostName);
	}

}
