package com.project.web.service.impl;

import java.util.List;

import com.project.web.bean.ClassBean;
import com.project.web.bean.CutPageBean;
import com.project.web.dao.IClassDao;
import com.project.web.dao.impl.ClassDaoImpl;
import com.project.web.service.IClassService;

public class ClassServiceImpl implements IClassService {
          static final int each_num=5;
           IClassDao impl=new ClassDaoImpl();
		@Override
		public ClassBean addClass(ClassBean bean) {
			      return impl.addBean(bean);
		}
	
		@Override
		public void UpdateClassBean(int id,String name, String tel) {
			 
			   impl.updateClass(id, name, tel);
			
		}
	
		@Override
		public ClassBean FindClassBean(int id) {
			// TODO Auto-generated method stub
			return impl.showClass(id);
		}
	
		@Override
		public CutPageBean<ClassBean> cutpage(int page,String name, String area) {
			 CutPageBean<ClassBean> bean=impl.CutPage(page, each_num, name, area);
			return bean;
		}

		@Override
		public List<ClassBean> FindClassAll() {
			// TODO Auto-generated method stub
			return impl.findALL();
		}

		@Override
		public String getClassNameByArea(int id) {
			// TODO Auto-generated method stub
			return impl.findClassName(id);
		}

}
