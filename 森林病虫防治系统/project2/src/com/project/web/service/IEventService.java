package com.project.web.service;

import java.sql.Date;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.EventBean;

public interface IEventService {
        
		/**
		 *  通过事件对象添加事件
		 * @param bean 事件对象
		 */
         public void addEvent(EventBean bean);
	  /**
	   *    
	   * @param id 事件的id
	   * @return 返回事件对象
	   */
	     public EventBean chooseEvent(int id);
	     /**
	      * 帮一个事件申请专家会商
	      * @param id事件的id
	      */
	     public void applyProfessor(int id);
	     
	     /**
	      * 修改一个事件的灾情阶段
	      * @param phase 灾情
	      * @param plan 防治方案
	      * @param id 修改事件的id
	      */
	     public void updateEvent(int ID,String phase,String plan);
	     
         /**
          * 
          * @param page 页码
          * @param each_num 每页的条数
          * @param name 名字
          * @param position 灾情
          * @param location 地点
          * @param beagin 开始时间
          * @param end      结束时间
          * @return
          */
		public CutPageBean<EventBean> Cutbean(int page, String name, String position, String location, Date beagin, Date end);
	     
		
		public void applyEvent(int id);
}
