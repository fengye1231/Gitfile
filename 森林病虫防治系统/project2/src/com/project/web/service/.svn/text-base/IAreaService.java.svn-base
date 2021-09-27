package com.project.web.service;

import java.util.List;

import com.project.web.bean.AreaBean;
import com.project.web.bean.CutPageBean;

/**
 * 区域业务接口
 * @author hp
 *
 */
public interface IAreaService {
    /**
     * 更加表现层传入的区域对象添加新的区域对象
     * @param bean 区域对象
     */
	 public void AddArea(AreaBean bean );
	 
	 /**
      * 动态查询区域信息
      * @param indexNum 起始页码
      * @param num 每页显示条数
      * @param name 区域名称
      * @param species 林种
      * @param className 负责小班名称
      * @return Area分页对象
      */
     public CutPageBean<AreaBean> showAllArea(int indexNum,int num,String name,String species,String classbean);
	 
	
	  
}
