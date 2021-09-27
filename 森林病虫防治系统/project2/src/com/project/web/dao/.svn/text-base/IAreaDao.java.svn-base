package com.project.web.dao;

import java.util.List;

import com.project.web.bean.AreaBean;
import com.project.web.bean.CutPageBean;




public interface IAreaDao {
	
	/**
	 * 添加区域对象
	 * @param bean 区域对象
	 */
     public void addArea(AreaBean bean);
     
     /**
      * 动态查询区域信息
      * @param indexNum 起始页码
      * @param num 每页显示条数
      * 
      * @param name 区域名称
      * @param species 林种
      * @param className 负责小班名称
      * @return Area分页对象
      */
     public CutPageBean<AreaBean> showAllArea(int indexNum,int num,String name,String species,String classbean);
	 
    /**
     * 通过ID查询对象
     * @param id 区域ID
     * @return AreaBean对象
     */
     public  AreaBean findId(int id);
     /**
      * 通过名字查询ID
      * @param name 区域名称
      * @return 区域ID
      */
     public  int findName(String name);
     
   
    
	 
	 
}
