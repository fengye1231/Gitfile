package com.project.web.service.impl;

import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.OutMachineBean;
import com.project.web.dao.IOutMachineDao;
import com.project.web.dao.impl.OutMachineDaoImpl;
import com.project.web.service.IOutMachineService;

public class OutMachineServiceImpl implements IOutMachineService{
	private IOutMachineDao oo = new OutMachineDaoImpl();
	@Override
	public CutPageBean<OutMachineBean> findByOut(int num, int f_outId) {

		CutPageBean<OutMachineBean>cut = new CutPageBean<OutMachineBean>();
		List<OutMachineBean>list = oo.findByOut(num, EACH_PAGE_NUM, f_outId);
		cut.setList(list);
		int totalNum = oo.findByOutNum(f_outId);
		if(totalNum%EACH_PAGE_NUM==0) {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM);
		}else {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM+1);
		}
		
		return cut;
	}



	@Override
	public void addOutMachine(OutMachineBean bean) {
		oo.addOutMachine(bean);
	}

	
}
