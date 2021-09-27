package com.project.web.dao;

import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.MachineBean;

/**
 * 药剂器械持久层接口
 * @author 45388
 *
 */
public interface IMachineDao {

	/**
	 * 根据条件查询药剂器械的集合
	 * @param num 需要查看的页码
	 * @param each_num 每页显示的条数
	 * @param name 模糊查询的名字
	 * @param defeat 需要条件查询的药剂器械防治类型
	 * @param kind 需要条件查询的药剂器械类别
	 * @return 带有查询结果集合以及总页数的分页对象
	 */
	public List<MachineBean> findProject(int num,int each_num,String name,String defeat,String kind);

	/**
	 * 新增一个药剂器械
	 * @param bean 需要添加的药剂器械实体对象
	 * 
	 */
	public void addMachine(MachineBean bean);
	
	
	/**
	 * 通过Id来查询对应的药剂器械
	 * @param id 需要查询的药剂器械
	 * @return 找到的药剂器械对象
	 */
	public MachineBean findById(int id);
	/**
	 * 根据条件统计查询的结果总条数
	 * @param name 查询的姓名
	 * @param defeat 需要条件查询的药剂器械防治类型
	 * @param kind 需要条件查询的药剂器械防治类型
	 * @return 查出来的数目
	 */
	public int findNum(String name,String defeat,String kind);
	
}
