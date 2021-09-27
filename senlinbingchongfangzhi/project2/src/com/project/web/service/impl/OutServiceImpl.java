package com.project.web.service.impl;

import java.sql.Date;
import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.OutBean;
import com.project.web.bean.OutMachineBean;
import com.project.web.dao.IOutDao;
import com.project.web.dao.IOutMachineDao;
import com.project.web.dao.impl.OutDaoImpl;
import com.project.web.dao.impl.OutMachineDaoImpl;
import com.project.web.service.IOutService;

public class OutServiceImpl implements IOutService{
	private IOutDao od = new OutDaoImpl();
	private IOutMachineDao oo = new OutMachineDaoImpl();
	@Override
	public CutPageBean<OutBean> findProject(int num, String className, Date starDate, Date endDate) {
		CutPageBean<OutBean>cut = new CutPageBean<OutBean>();
		List<OutBean>list = od.findProject(num, EACH_PAGE_NUM, className, starDate, endDate);
		
		int totalNum = od.findProjectNum(className, starDate, endDate);
		if(totalNum%EACH_PAGE_NUM==0) {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM);
		}else {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM+1);
		}
		cut.setList(list);
		return cut;
	}

	@Override
	public OutBean findById(int id) {
		OutBean bean = od.findById(id);
		return bean;
	}

	@Override
	public void addOut(OutBean bean) {

		od.addOut(bean);
		/**提出里面包含的出库信息集合*/
		
		List<OutMachineBean>list = bean.getList();
		for (OutMachineBean outMachineBean : list) {
			outMachineBean.setF_outId(bean.getId());
			oo.addOutMachine(outMachineBean);
		}
		
	}

}
