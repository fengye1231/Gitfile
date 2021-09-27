package com.project.web.dao;

import java.util.List;
import com.project.web.bean.ConsultationBean;
import com.project.web.bean.EventBean;

/**
 * 专家会商持久层接口类
 * @author GF
 *
 */
public interface IConsultationDao {
	
	/**
	 * 添加会商记录（通过事件id）
	 * @param bean 会商实体
	 */
	public void addConsultation(ConsultationBean bean);
		
	/**
	 * 通过事件id查找所有会商信息
	 * @param id 事件id
	 * @return 会商信息集合
	 */
	public List<ConsultationBean> findAll(int id);
	
	
	/**
	 * 找到所有需要会商的事件
	 * @return 事件集合
	 */
	public List<EventBean> findEventNeed();
	
	/**
	 * 通过id查找事件
	 * @param id
	 * @return 事件bean
	 */
	public EventBean findEventById(int id);
	
	
}
