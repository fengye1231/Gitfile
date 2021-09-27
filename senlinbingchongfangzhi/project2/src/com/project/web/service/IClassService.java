package com.project.web.service;

import java.util.List;

import com.project.web.bean.ClassBean;
import com.project.web.bean.CutPageBean;

public interface IClassService {
	                  /**
	                   * 
	                   * @param bean 需要添加的对象
	                   * @return 一个classbean
	                   */
                      public ClassBean addClass(ClassBean bean);
                      /**
                       * 
                       * @param name 名字
                       * @param tel 电话
                       */
                      public void UpdateClassBean(int id,String name,String tel);
                      /**
                       * 
                       * @param id id 
                       * @return ClassBean  
                       */
                      public ClassBean FindClassBean(int id);
                     
                      /**
                       * 
                       * @param page 分页
                       * @param each_num 每页显示的
                       * @param name 名字
                       * @param area 区域
                       * @return
                       */
                      public CutPageBean<ClassBean> cutpage(int page,String name,String area);
                      
                      public List<ClassBean> FindClassAll();
                      
					  public String getClassNameByArea(int id);
                      
}
