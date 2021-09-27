package com.project.web.dao;

import java.sql.Date;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.EventBean;

public interface IEventDao {
	    /**
	     *  @param bean 是事件的对象
	     */
        public void AddEventBean(EventBean bean) ;
        /**
         * 
         * @param bean 事件对象
         * @return EventBean 事件
         */
        public EventBean findEventBean(int id);
        
        public void UpdatePhase(int id);
        
        /**
         * 
         * @param phase 事件的的阶段
         * @param plan 防治的措施
         */
        public void UpdateEvent(int id,String phase,String plan);
        
        /**
         * 
         * @param page 页码
         * @param each_num 每页的最大数目
         * @param name 名字
         * @param plan 计划
         * @param location 地点
         * @param start 开始日期
         * @param end  结束日期
         * @return
         */
		public CutPageBean<EventBean> cutPage(int page, int each_num, String name, String plan, String area, Date start,
				Date end);
        
}
