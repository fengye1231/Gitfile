package com.project.web.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.LogBean;
import com.project.web.bean.UserBean;
import com.project.web.dao.ILogDao;
import com.project.web.dao.IUserDao;
import com.project.web.dao.impl.LogDaoImpl;
import com.project.web.dao.impl.UserDaoImpl;
import com.project.web.util.DateChange;
import com.project.web.util.MyBatisUtil;

import sun.util.logging.resources.logging_de;

public class TestYqj {
	private IUserDao ius = new UserDaoImpl();
	private ILogDao ild = new LogDaoImpl();
//	@Test
//	public void findByConditionTest() {
//		CutPageBean<UserBean> cut =  ius.findByCondition(1, null);
//			System.out.println(cut);
//	}
//	@Test
//	public void loginTest() {
//		UserBean user = ius.login(new UserBean("xiaomi", "123"));
//		System.out.println(user);
//	}
//	@Test
//	public void addUserTest() {
//		ius.addUser(new UserBean("nihao", "123", "你好", "超级管理员"));
//	}
//	@Test
//	public void updateUserTest() {
//		ius.updateUser(new UserBean(2, "789", "资料管理员"));
//	}
//	@Test
//	public void delUserTest() {
//		ius.delUser(8);
//	}
//	@Test
//	public void findById() {
//		UserBean user = ius.findById(5);
//		System.out.println(user);
//	}
//	@Test
//	public void findCountPageNo() {
//		System.out.println(ius.findCountPageNo("超级管理员"));
//	}
//	@Test
//	public void logAddTest(){
//		ild.addLog(new LogBean("123456", DateChange.getDate("2018-01-01")));
//	}
	@Test
	public void logtest() {
		CutPageBean<LogBean> list = ild.findByCondition(1, DateChange.getDate("2018-01-01"), DateChange.getDate("2018-07-01"));
		System.out.println(list);
	}
	
	

	
}
