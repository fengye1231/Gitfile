package com.project.web.dao;

import java.util.List;
import com.project.web.bean.ExpertBean;

/**
 * 专家持久层接口类
 * @author GF
 *
 */
public interface IExpertDao {
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
	 * 按条件修改专家信息
	 * @param id 专家id
	 * @param position 专家职位
	 * @param phoneNum 专家电话
	 * @param workPlace 专家工作单位
	 * @param email 专家邮箱
	 */
	public void updateExpert(int id,String position,String phoneNum,String workPlace,String email);
	
	/**
	 * 通过专家Id删除专家
	 * @param id 专家id
	 */
	public void delExpert(int id);
	
	/**
	 * 通过条件查找专家人数
	 * @param name 专家姓名
	 * @param special 专家特长
	 * @param workPlace 专家工作单位
	 * @return 找到的人数
	 */
	public int findAllExpert(String name, String special, String workPlace);
	
	/**
	 * 通过条件查找所有专家信息
	 * @param num 当前页数
	 * @param name 专家姓名
	 * @param special 专家特长
	 * @param workPlace 专家工作单位
	 * @return 专家集合
	 */
	public List<ExpertBean> showAllExpert(int indexNum,int eachPageNum,String name,String special,String workPlace);

	/**
	 * 显示所有专家姓名
	 * @return 专家姓名集合
	 */
	public List<String> showAllName();
}
