package com.project.web.test;



import java.sql.Date;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.Test;

import com.project.web.bean.ClassBean;
import com.project.web.bean.CutPageBean;
import com.project.web.bean.EventBean;
import com.project.web.service.impl.ClassServiceImpl;
import com.project.web.service.impl.EventServiceImpl;
import com.project.web.util.DateChange;

public class TestYh {
	         EventServiceImpl imp=new EventServiceImpl();
	         ClassServiceImpl imp2=new ClassServiceImpl();
             @Test
	         public void Test1() {
//                 	 imp.chooseEvent(1);
//            	     imp.applyProfessor(1);
//            	   CutPageBean<EventBean> bean=imp.Cutbean(1,"田","","", Date.valueOf("2017-01-30"), Date.valueOf("2019-01-01"));
//                   List<EventBean>list =bean.getList();
//                   int num=bean.getTotalPageNum();
//                   System.out.println(num);
             }
             @Test
             public void Test2() {
//            	 CutPageBean<ClassBean> bean=imp2.cutpage(1,"成都","成都");
//            	 System.out.println(bean);
//            	List<ClassBean>bean2=bean.getList();
//            	List<ClassBean>list=bean.getList();
//            	for (ClassBean classBean : list) {
//					       System.out.println(classBean.getAreaBean().getName());
//				}
//            	List<ClassBean>list= imp2.FindClassAll();
//            	System.out.println(list);
            	 ClassBean bean=imp2.FindClassBean(1);
            	 System.out.println(bean.getName());
             }
}         
