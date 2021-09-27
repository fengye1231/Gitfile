package com.project.web.dao;

import java.sql.Date;
import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.OutBean;

/**
 * 出库持久层接口
 * @author 45388
 *
 */
public interface IOutDao {

	/**
	 * 动态查询用户的出库集合
	 * @param num 当前显示的页码
	 * @param each_num 每页显示的条数
	 * @param className 模糊查询的小班名字
	 * @param starDate 查询的起始日期
	 * @param endDate 查询的结束日期
	 * @return 出库集合
	 */
	public List<OutBean> findProject(int num,int each_num,String className,Date starDate,Date endDate);
	
	/**
	 * 通过条件查询统计查询出来的总条数
	 * @param className 模糊查询的小班名字
	 * @param starDate 查询的起始日期
	 * @param endDate 查询的结束日期
	 * @return 查到的总条数
	 */
	public int findProjectNum(String className,Date starDate,Date endDate);
	
	/**
	 * 通过id查询对应的出库对象，
	 * 里面有器械药品出库信息集合
	 * @param id 需要查看的出库信息id
	 * @return 找到的出库对象
	 */
	public OutBean findById(int id);
	/**
	 * 添加出库记录
	 * @param bean 包含所有出库信息、以及出库信息的集合
	 */
	public void addOut(OutBean bean);
}
