package com.project.web.test;

import java.util.List;

import org.junit.Test;

import com.project.web.bean.OutBean;
import com.project.web.bean.OutMachineBean;
import com.project.web.dao.IOutDao;
import com.project.web.dao.impl.OutDaoImpl;
import com.project.web.service.IOutMachineService;
import com.project.web.service.IOutService;
import com.project.web.service.impl.OutMachineServiceImpl;
import com.project.web.service.impl.OutServiceImpl;
import com.project.web.util.DateChange;

public class TestJxm2 {
	private IOutDao od = new OutDaoImpl();
	
	
	@Test
	public void findAll() {
		
//		System.out.println(od.findProject(1, 4, "成都", DateChange.getDate("2018-06-01"),DateChange.getDate("2018-06-20")));
//		System.out.println(od.findProjectNum("成都", DateChange.getDate("2018-06-01"),DateChange.getDate("2018-06-20")));
//		System.out.println(od.findById(2));
//		od.addOut(new OutBean(2, "成都一班", 2, "呵呵"));
		
	}
	/**业务层测试*/
	
	IOutService os = new OutServiceImpl();
	IOutMachineService oms = new OutMachineServiceImpl();
	@Test
	public void findProject() {
//		System.out.println(os.findProject(1,"", DateChange.getDate("2018-06-01"),DateChange.getDate("2018-06-20")));
//		System.out.println(os.findById(3));
	
//		OutBean bean  = new OutBean(2, "成都一班", 2, "呵呵");
//		
//		List<OutMachineBean>list = oms.findByOut(1, 3).getList();
//		bean.setList(list);
//		os.addOut(bean);
		
		
		
	}
}
