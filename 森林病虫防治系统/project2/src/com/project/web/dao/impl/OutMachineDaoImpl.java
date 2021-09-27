package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.OutMachineBean;
import com.project.web.dao.IOutMachineDao;
import com.project.web.util.MyBatisUtil;

public class OutMachineDaoImpl implements IOutMachineDao{

	@Override
	public List<OutMachineBean> findByOutId(int id) {
		SqlSession session = MyBatisUtil.getSqlSession();
		List<OutMachineBean>list = session.selectList("com.project.web.mapper.outMachineMapper.fingByOutId",id);
		
		session.close();
		return list;
	}

	@Override
	public void addOutMachine(OutMachineBean bean) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.insert("com.project.web.mapper.outMachineMapper.addoutMachine", bean);
		
		session.close();
	}

	
	@Override
	public List<OutMachineBean> findByOut(int num, int each_num, int f_outId) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("num", (num-1)*each_num);
		map.put("each_num", each_num);
		map.put("f_outId", f_outId);
		
		List<OutMachineBean>list = session.selectList("com.project.web.mapper.outMachineMapper.findProject", map);
		
		session.close();
		return list;
	}

	@Override
	public int findByOutNum(int f_outId) {
		SqlSession session = MyBatisUtil.getSqlSession();
		int num = session.selectOne("com.project.web.mapper.outMachineMapper.findProjectNum", f_outId);
		
		session.close();
		return num;
	}

}
