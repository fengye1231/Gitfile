package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.jcp.xml.dsig.internal.MacOutputStream;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.UserBean;
import com.project.web.dao.IUserDao;
import com.project.web.util.MyBatisUtil;
import com.project.web.util.pageNumCount;
import com.sun.swing.internal.plaf.basic.resources.basic;

public class UserDaoImpl implements IUserDao{
	@Override
	public UserBean login(UserBean bean) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", bean.getUserName());
		map.put("pwd", bean.getPwd());
		UserBean user = session.selectOne("com.project.web.mapping.userMapper.findByUserName", map);
		return user;
	}

	@Override
	public CutPageBean<UserBean> findByCondition(int pageNo, String level) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", (pageNo-1)*pageNumCount.EACH_NUM);
		map.put("level", level);
		map.put("eachNum", pageNumCount.EACH_NUM);
		CutPageBean<UserBean> cut = new CutPageBean<UserBean>();
		List<UserBean> list = session.selectList("com.project.web.mapping.userMapper.findByCondition", map);
		int totalNum = findCountPageNo(level);
		cut.setList(list);
		cut.setTotalPageNum(totalNum);
		return cut;
	}

	@Override
	public void addUser(UserBean user) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", user.getUserName());
		map.put("pwd", user.getPwd());
		map.put("name", user.getName());
		map.put("level", user.getLevel());
		session.insert("com.project.web.mapping.userMapper.addMapper", map);
	}

	@Override
	public void updateUser(UserBean user) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user.getId());
		map.put("pwd", user.getPwd());
		map.put("level", user.getLevel());
		session.update("com.project.web.mapping.userMapper.upadteMapper", map);
		
		
	}

	@Override
	public void delUser(int id) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.delete("com.project.web.mapping.userMapper.delMapper", id);
		
	}

	@Override
	public UserBean findById(int id) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		UserBean user = session.selectOne("com.project.web.mapping.userMapper.findById", id);
		return user;
	}

	@Override
	public int findCountPageNo(String level) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level", level);
		int num = session.selectOne("com.project.web.mapping.userMapper.findCountPageNo", map);
		return num;
	}
}
