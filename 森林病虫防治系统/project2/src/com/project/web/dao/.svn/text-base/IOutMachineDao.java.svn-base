package com.project.web.dao;

import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.OutMachineBean;

/**
 * 药剂器械出库信息持久层接口
 * @author 45388
 *
 */
public interface IOutMachineDao {

	/**
	 * 通过出库Id查看所有的出库信息
	 * @param id 出库id
	 * @return 当前id的出库信息集合
	 */
	public List<OutMachineBean> findByOutId(int id);
	/**
	 * 添加出库信息记录
	 * @param bean 新添加的出库信息实体
	 */
	public void addOutMachine(OutMachineBean bean);
	

	/**
	 * 根据出库Id找出对应的出库信息集合
	 * @param num 当前需要查看的页码
	 * @param each_num 每页显示的条数
	 * @param f_outId 需要查看的出库Id
	 * @return 找到的出库信息集合
	 */
	public List<OutMachineBean> findByOut(int num,int each_num,int f_outId);
	/**
	 * 统计每个出库id的出库信息的条数
	 * @param f_outId 需要统计的出库id
	 * @return 找到的总条数
	 */
	public int findByOutNum(int f_outId);
	
}
