package com.project.web.test;

import java.util.List;

import org.junit.Test;

import com.project.web.bean.MachineBean;
import com.project.web.dao.IMachineDao;
import com.project.web.dao.IOutDao;
import com.project.web.dao.impl.MachineDaoImpl;
import com.project.web.dao.impl.OutDaoImpl;
import com.project.web.service.IMachineService;
import com.project.web.service.impl.MachineServiceImpl;

/**
 * 蒋晰明单点测试类
 * @author 45388
 *
 */
public class TestJxm {

	private IMachineDao mc = new MachineDaoImpl();

	
	
//	@Test
//	public void MachineTest() {
//		
//		List<MachineBean> list = mc.findProject(1,4, "鼠", "鼠害", "药剂");
//		
//		for (MachineBean machineBean : list) {
//			System.out.println(machineBean);
//		}
//		
//		
//	}
//	@Test
//	public void findNum() {
//		
//		System.out.println(mc.findNum("", "鼠害", "药剂"));
//		
//	}
//	@Test
//	public void addaas() {
//		MachineBean bean = new MachineBean("敌敌畏4","虫害","药剂","毒杀害虫");
//		mc.addMachine(bean);
//		
//	}
//	@Test
//	public void findByid() {
//		System.out.println(mc.findById(6));
//	}

	/**业务层测试*/
	IMachineService ms = new MachineServiceImpl();
//	@Test
//	public void findProject() {
//		
//		System.out.println(ms.findProject(1, "", "鼠害", ""));
//	}
	@Test
	public void add() {
		MachineBean bean = new MachineBean("敌敌畏5号", "虫害", "药剂", "初代加强版");
		ms.addMachine(bean);
	}
}
