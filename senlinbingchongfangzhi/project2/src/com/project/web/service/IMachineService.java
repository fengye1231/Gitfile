package com.project.web.service;
/**
 * 药剂器械业务层接口
 * @author 45388
 *
 */

import com.project.web.bean.CutPageBean;
import com.project.web.bean.MachineBean;

public interface IMachineService {
	/**
	 * 每页显示的条数
	 */
	static final int EACH_PAGE_NUM = 4;
	
	
	/**
	 * 根据条件查询药剂器械的分页对象
	 * @param num 当前的页码
	 * @param name 需要条件模糊查询的药剂器械名字
	 * @param defeat 需要条件查询的药剂器械防治类型
	 * @param kind 需要条件查询的药剂器械类别
	 * @return 带有查询结果集合的分页对象
	 */
	public CutPageBean<MachineBean> findProject(int num,String name,String defeat,String kind);
	/**
	 * 新增一个药剂器械
	 * @param bean 需要添加的药剂器械实体对象
	 */
	public void addMachine(MachineBean bean);
	/**
	 * 通过id查询出药剂器械对象
	 * @param id 需要查看的id
	 * @return 找到的药剂器械对象
	 */
	public MachineBean findById(int id);

	
}
