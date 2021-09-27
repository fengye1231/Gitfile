package com.project.web.service;
/**
 * 药剂器械出库业务层接口
 * @author 45388
 *
 */

import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.OutMachineBean;

public interface IOutMachineService {
	/**
	 * 每次显示的条数
	 */
	static final int EACH_PAGE_NUM = 4;
	/**
	 * 通过用户id查出出库信息的分页对象
	 * @param num 当前的页数
	 * @param f_outId 需要查看的 出库id
	 * @return 出库信息的分页对象
	 */
	public CutPageBean<OutMachineBean> findByOut(int num,int f_outId);


	
	/**
	 * 添加出库信息记录
	 * @param bean 新添加的出库信息实体
	 */
	public void addOutMachine(OutMachineBean bean);
	
}
