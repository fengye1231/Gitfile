package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.ClassBean;
import com.project.web.bean.CutPageBean;
import com.project.web.dao.IClassDao;
import com.project.web.util.MyBatisUtil;

public class ClassDaoImpl implements IClassDao{
          static final int each_num=5;
		@Override
		public ClassBean addBean(ClassBean bean) {
			SqlSession session=MyBatisUtil.getSqlSession(true);
			String way="com.project.web.mapper.classMapper.add";
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("name", bean.getName());
			map.put("principal", bean.getPrincipal());
			map.put("phoneNum", bean.getPhoneNum());
			map.put("date",bean.getDate());
			map.put("num", bean.getNum());
			map.put("areaBean", bean.getAreaBean().getId());
			int i=session.insert(way,map);
		   
			return bean;
		}
	
		@Override
		public ClassBean showClass(int id) {
			SqlSession session=MyBatisUtil.getSqlSession();
	        String way="com.project.web.mapper.classMapper.selectOne";
	        ClassBean bean =session.selectOne(way,id);
			return bean;
		}
		@Override
		public void updateClass(int id,String name,String tel) {
			SqlSession session=MyBatisUtil.getSqlSession(true);
			String way="com.project.web.mapper.classMapper.updateOne";
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);
			map.put("name",name);
			map.put("phoneNum",tel);
		   session.update(way,map);
			
		}

		@Override
		public CutPageBean<ClassBean> CutPage(int page, int each_num, String name, String area) {
			SqlSession session=MyBatisUtil.getSqlSession();
			Map<String,Object> map=new HashMap<String,Object>();
			CutPageBean<ClassBean> bean=new CutPageBean<ClassBean>();
			map.put("page", page);
			int index_num=each_num*(page-1);
			map.put("index_num",index_num);
			map.put("name", "%"+name+"%");
			map.put("area", "%"+area+"%");
			map.put("each_num", each_num);
			String way="com.project.web.mapper.classMapper.selectAll";
		    List<ClassBean>list=session.selectList(way,map);
			bean.setList(list);
			String way2="com.project.web.mapper.classMapper.selectCount";
			int pageNum=session.selectOne(way2,map);
			int totalPageNum=0;
			if(pageNum%each_num==0) {
				totalPageNum=pageNum/each_num;
				bean.setTotalPageNum(totalPageNum);
			}else if(pageNum%each_num>0) {
				totalPageNum=pageNum/each_num+1;
				bean.setTotalPageNum(totalPageNum);
			}
			return bean;
		}

		@Override
		public List<ClassBean> findALL() {
			// TODO Auto-generated method stub
			String way="com.project.web.mapper.classMapper.findAll";
			 SqlSession session=MyBatisUtil.getSqlSession();
			List<ClassBean>list=session.selectList(way);
			return list ;
		}

		@Override
		public String findClassName(int id) {
			// TODO Auto-generated method stub
			String way="com.project.web.mapper.classMapper.findArea";
			 SqlSession session=MyBatisUtil.getSqlSession();
			 String name=session.selectOne(way,id);
			 return name;
		}
	    

}
