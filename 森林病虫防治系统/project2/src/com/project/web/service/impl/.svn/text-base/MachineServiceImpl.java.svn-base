package com.project.web.service.impl;

import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.MachineBean;
import com.project.web.dao.IMachineDao;
import com.project.web.dao.impl.MachineDaoImpl;
import com.project.web.service.IMachineService;

public class MachineServiceImpl implements IMachineService{
	private IMachineDao mc = new MachineDaoImpl();
	@Override
	public CutPageBean<MachineBean> findProject(int num, String name, String defeat, String kind) {
		CutPageBean<MachineBean>cut = new CutPageBean<MachineBean>();
		
		List<MachineBean>list = mc.findProject(num,EACH_PAGE_NUM, name, defeat, kind);
		int totalNum = mc.findNum(name, defeat, kind);
		if(totalNum%EACH_PAGE_NUM==0) {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM);
		}else {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM+1);
		}
		cut.setList(list);
		
		return cut;
	}

	@Override
	public void addMachine(MachineBean bean) {
		mc.addMachine(bean);
		
	}

	@Override
	public MachineBean findById(int id) {
		// TODO Auto-generated method stub
		return mc.findById(id);
	}



}
