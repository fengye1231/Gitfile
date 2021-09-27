package com.project.web.service.impl;

import com.project.web.bean.UserBean;
import com.project.web.dao.IUserDao;
import com.project.web.dao.impl.UserDaoImpl;
import com.project.web.service.ILoginService;


public class LoginServiceImpl implements ILoginService{
	private IUserDao iud = new UserDaoImpl();
	@Override
	public UserBean login(UserBean bean) {

		return iud.login(bean);
	}
}
