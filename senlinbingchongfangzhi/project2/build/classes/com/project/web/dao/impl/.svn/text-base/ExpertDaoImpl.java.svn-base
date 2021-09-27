package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.ExpertBean;
import com.project.web.dao.IExpertDao;
import com.project.web.util.MyBatisUtil;

/**
 * 专家持久层实现类
 * @author GF
 *
 */
public class ExpertDaoImpl implements IExpertDao {

	@Override
	public void addExpert(ExpertBean bean) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.insert("com.project.web.mapper.expertMapper.addExpert", bean);
		session.close();
	}

	@Override
	public ExpertBean findExpertById(int id) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSession(true);
		ExpertBean bean = session.selectOne("com.project.web.mapper.expertMapper.findExpertById", id);
		session.close();
		return bean;
		
	}

	@Override
	public void updateExpert(int id, String position, String phoneNum, String workPlace, String email) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("position", position);
		map.put("phoneNum", phoneNum);
		map.put("workPlace", workPlace);
		map.put("email", email);
		session.update("com.project.web.mapper.expertMapper.updateExpert", map);
		session.close();
	}

	@Override
	public void delExpert(int id) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.delete("com.project.web.mapper.expertMapper.delExpert", id);
		session.close();			
	}

	@Override
	public int findAllExpert(String name, String special, String workPlace) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map = new HashMap<String,Object>();	
		map.put("name", name);
		map.put("special", special);
		map.put("workPlace", workPlace);
		int totalNum = session.selectOne("com.project.web.mapper.expertMapper.countAll",map);
		session.close();
		return totalNum;		
	}

	@Override
	public List<ExpertBean> showAllExpert(int indexNum,int eachPageNum,String name, String special, String workPlace) {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSession(true);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("indexNum", indexNum);
		map.put("eachPageNum", eachPageNum);
		map.put("name", name);
		map.put("special", special);
		map.put("workPlace", workPlace);
		List<ExpertBean> list = session.selectList("com.project.web.mapper.expertMapper.findByPage", map);
		session.close();	
		return list;
	}

	@Override
	public List<String> showAllName() {
		// TODO Auto-generated method stub
		SqlSession session = MyBatisUtil.getSqlSession(true);
		List<String> list = session.selectList("com.project.web.mapper.expertMapper.findAllName");
		session.close();
		return list;
	}
	

}
