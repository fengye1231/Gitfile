package com.project.web.service.impl;

import com.project.web.bean.AreaBean;
import com.project.web.bean.CutPageBean;
import com.project.web.dao.IAreaDao;
import com.project.web.dao.impl.AreaDaoImpl;
import com.project.web.service.IAreaService;

public class AreaServiceImpl implements IAreaService{
        private IAreaDao dao=new AreaDaoImpl();
	@Override
	public void AddArea(AreaBean bean) {
		dao.addArea(bean);
		
	}

	@Override
	public CutPageBean<AreaBean> showAllArea(int indexNum, int num, String name, String species, String classbean) {
		// TODO Auto-generated method stub
		
		return dao.showAllArea(indexNum, num, name, species, classbean);
	}

}
