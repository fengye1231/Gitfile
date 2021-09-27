package com.project.web.service;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.UserBean;

/**
 * 用户业务层接口
 * @author TE
 *
 */
public interface IUserService {
	/**
 	 * 根据表现层传入的信息查询用户信息集合
	 * @param pageNo 页码
	 * @param level 用户权限
	 * @return 符合条件的用户集合
	 */
	public CutPageBean<UserBean> findByCondition(int pageNo, String level);
	/**
	 * 添加用户
	 * @param user 需要添加的用户对象
	 */
	public void addUser(UserBean user);
	/**
	 * 修改用户信息
	 * @param user 需要修改的用户及修改信息
	 */
	public void updateUser(UserBean user);
	/**
	 * 更具表现层提供的用户id删除用户
	 * @param id用户id
	 */
	public void delUser(int id);
	/**
	 * 条件查询总条数
	 * @param level 查询条件
	 * @return 查询结果总条数
	 */
	public int findCountPageNo(String level);
	/**
	 * 根据id查询用户信息用于修改前的展示
	 * @param id 需要修改的用户id
	 * @return 返回需要修改的用户原信息
	 */
	public UserBean findById(int id);
	
}
