package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.MachineBean;
import com.project.web.dao.IMachineDao;
import com.project.web.util.MyBatisUtil;
/**
 * 药剂器械类持久层实体类
 * @author 45388
 *
 */
public class MachineDaoImpl implements IMachineDao{

	@Override
	public List<MachineBean> findProject(int num, int each_num, String name, String defeat, String kind) {
		SqlSession  session = MyBatisUtil.getSqlSession();
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("num", (num-1)*each_num);
		map.put("each_num", each_num);
		map.put("name", name);
		map.put("defeat", defeat);
		map.put("kind", kind);
		
		
		
		List<MachineBean>list = session.selectList("com.project.web.mapper.machineMapper.findProject",map);
		session.close();
		return list;
	}

	@Override
	public void addMachine(MachineBean bean) {
		SqlSession  session = MyBatisUtil.getSqlSession(true);
		session.insert("com.project.web.mapper.machineMapper.addMachine", bean);
		session.close();
	
		
		
	}

	@Override
	public MachineBean findById(int id) {
		SqlSession session = MyBatisUtil.getSqlSession();
		MachineBean bean = session.selectOne("com.project.web.mapper.machineMapper.findByid",id);
		session.close();
		return bean;
	}

	@Override
	public int findNum(String name, String defeat, String kind) {

		SqlSession  session = MyBatisUtil.getSqlSession();
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("defeat", defeat);
		map.put("kind", kind);
		
		int num =session.selectOne("com.project.web.mapper.machineMapper.findNum",map);
		session.close();
		return num;
	}

}
