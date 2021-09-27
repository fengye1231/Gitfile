package com.project.web.service;

import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.PestslBean;

/**
 * 虫害业务接口
 * @author 袁福祥
 *
 */
public interface IPestslService {
	/**
	 * 分页显示条数
	 */
	static final int EACH_PAGE_NUM = 5;
	/**
	 * 添加新虫害
	 * @param bean 虫害对象
	 */
	public void addPestsl(PestslBean bean);
	/**
	 * 查看虫害信息
	 * @param id 虫害的id
	 * @return 虫害对象
	 */
	public PestslBean findById(int id);
	/**
	 * 显示所有虫害信息，并且也可按需查询
	 * @param num 查询的页数
	 * @param pestslName 害虫的字
	 * @param hostName 寄主的名字
	 * @return 分页对象
	 */
	public CutPageBean<PestslBean> showAllPestsl(int num,String pestslName,String hostName);
	
}
