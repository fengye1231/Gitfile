package com.project.web.service;

import java.util.List;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.ExpertBean;

/**
 * 专家业务层接口类
 * @author GF
 *
 */
public interface IExpertService {
	/**
	 * 分页显示条数
	 */
	static final int EACH_PAGE_NUM = 5;
	
	/**
	 * 添加专家
	 * @param bean 专家对象
	 */
	public void addExpert(ExpertBean bean);
	
	/**
	 * 通过专家id查找专家对象
	 * @param id 专家id
	 * @return 专家对象
	 */
	public ExpertBean findExpertById(int id);
	
	/**
	 * 按条件修改
	 * @param id 专家id
	 * @param position 专家职位
	 * @param phoneNum 专家电话
	 * @param workPlace 专家工作地址
	 * @param email 专家邮箱
	 */
	public void updateExpert(int id,String position,String phoneNum,String workPlace,String email);
	
	/**
	 * 通过专家Id删除专家
	 * @param id 专家id
	 */
	public void delExpert(int id);
	
	/**
	 * 显示所有专家，并按需查找
	 * @param num 分页当前页数
	 * @param name 专家姓名
	 * @param special 专家特长
	 * @param workPlace 专家工作地址
	 * @return 分页对象
	 */
	public CutPageBean<ExpertBean> showAllExpert(int num,String name,String special,String workPlace);
	
	/**
	 * 显示所有专家姓名
	 * @return 专家姓名集合
	 */
	public List<String> showAllName();
}
