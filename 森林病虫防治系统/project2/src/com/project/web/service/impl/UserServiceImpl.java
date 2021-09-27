package com.project.web.service.impl;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.UserBean;
import com.project.web.dao.IUserDao;
import com.project.web.dao.impl.UserDaoImpl;
import com.project.web.service.IUserService;

public class UserServiceImpl implements IUserService{
	private IUserDao iud = new UserDaoImpl();
	@Override
	public CutPageBean<UserBean> findByCondition(int pageNo, String level) {
		return iud.findByCondition(pageNo, level);
	}

	@Override
	public void addUser(UserBean user) {
		iud.addUser(user);
		
	}

	@Override
	public void updateUser(UserBean user) {
		iud.updateUser(user);
		
	}

	@Override
	public void delUser(int id) {
		iud.delUser(id);
		
	}

	@Override
	public int findCountPageNo(String level) {
		return iud.findCountPageNo(level);
	}

	@Override
	public UserBean findById(int id) {
		return iud.findById(id);
	}
	
}
