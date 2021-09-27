package com.project.web.service;

import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.RatDamageBean;
/**
 * 鼠害业务接口
 * @author 袁福祥
 *
 */
public interface IRatDamageService {
	/**
	 * 分页显示条数
	 */
	static final int EACH_PAGE_NUM = 5;
	/**
	 * 添加新鼠害
	 * @param bean 虫害对象
	 */
	public void addRatDamage(RatDamageBean bean);
	/**
	 * 查看鼠害信息
	 * @param id 鼠害的id
	 * @return 鼠害对象
	 */
	public RatDamageBean findById(int id);
	/**
	 * 显示所有鼠害信息，并且也可按需查询
	 * @param num 查询的页数
	 * @param ratDamageName 鼠害的字
	 * @return 分页对象
	 */
	public CutPageBean<RatDamageBean> showAllRatDamage(int num,String ratDamageName);
	
}
