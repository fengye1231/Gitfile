package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.AreaBean;
import com.project.web.bean.ClassBean;
import com.project.web.bean.CutPageBean;
import com.project.web.dao.IAreaDao;
import com.project.web.util.MyBatisUtil;

public class AreaDaoImpl implements IAreaDao {

	@Override
	public void addArea(AreaBean bean) {
	SqlSession session= MyBatisUtil.getSqlSession(true);
	 session.insert("com.project.web.mapper.areaMapper.addArea",bean);
	 session.close();
		
	}

	
	@Override
	public CutPageBean<AreaBean> showAllArea(int indexNum, int num, String name, String species, String className) {
		
		CutPageBean<AreaBean> aren = new CutPageBean<AreaBean>();
		SqlSession session= MyBatisUtil.getSqlSession();
		Map<String,Object> map = new HashMap<String,Object>();
		
		int index_num = (indexNum-1)*num;
		map.put("index_num", index_num);
		map.put("indexNum", num);
		map.put("name", "%"+name+"%");
		map.put("species", "%"+species+"%");
		if(className.equals("")) {
			map.put("className", null);
		}else {
		map.put("className", "%"+className+"%");
		}
		List<AreaBean> list =session.selectList("com.project.web.mapper.areaMapper.findArea",map);
		System.out.println(list);
		int totalNum1 = session.selectOne("com.project.web.mapper.areaMapper.pageNo", map);
		
		if(totalNum1==0) {
			aren.setTotalPageNum(1);
		}else {
			if(totalNum1%num==0) {
				aren.setTotalPageNum(totalNum1/num);
			}else {
				aren.setTotalPageNum(totalNum1/num+1);
			}
		}
		
		
		aren.setList(list);
		session.close();
		return aren;
	}







	@Override
	public AreaBean findId(int id) {
		SqlSession session = MyBatisUtil.getSqlSession();
		AreaBean bean = session.selectOne("com.project.web.mapper.areaMapper.findId", id);
		session.commit();
		return bean;
		
	}

     public int  findName(String name) {
    	 SqlSession session =MyBatisUtil.getSqlSession();
    	 int id=session.selectOne("com.project.web.mapper.areaMapper.findName",name);
		return id;
     }



	

}
