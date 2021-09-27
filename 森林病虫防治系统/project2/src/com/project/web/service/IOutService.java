package com.project.web.service;
/**
 * 出库业务层接口
 * @author 45388
 *
 */

import java.sql.Date;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.OutBean;

public interface IOutService {
	/**
	 * 每次显示的条数
	 */
	static final int EACH_PAGE_NUM = 4;
	/**
	 * 动态查询用户的出库分页对象
	 * @param num 当前页码
	 * @param className 模糊查询的小班名字
	 * @param starDate 查询起始日期
	 * @param endDate 查询结束日期
	 * @return 包含当前页面出库集合的的分页对象
	 */
	public CutPageBean<OutBean> findProject(int num,String className,Date starDate,Date endDate);
	
	/**
	 * 通过id查询对应的出库对象、里面包含带有Id和名字的班级以及包含id和名字的用户对象，
	 * 药剂信息在表现层获得分页对象
	 * @param id 需要查看的出库信息id
	 * @return 找到的出库对象
	 */
	public OutBean findById(int id);
	
	/**
	 * 添加出库记录，业务层实现类需要调用出库信息的添加记录，通过for循环一次调用add
	 * 里面必须包含班级名字和id以及用户名字id
	 * @param bean 包含所有出库信息、以及出库信息的集合
	 */
	public void addOut(OutBean bean);
	
}
