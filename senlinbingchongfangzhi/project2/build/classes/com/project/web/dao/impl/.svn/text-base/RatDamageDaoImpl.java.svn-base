package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.RatDamageBean;
import com.project.web.dao.IRatDamageDao;
import com.project.web.util.MyBatisUtil;

public class RatDamageDaoImpl implements IRatDamageDao{

	@Override
	public void addRatDamage(RatDamageBean bean) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.insert("com.project.web.mapper.ratDamageMapper.addRatDamage",bean);
		session.close();
	}

	@Override
	public RatDamageBean findById(int id) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		RatDamageBean bean = session.selectOne("com.project.web.mapper.ratDamageMapper.findById",id);
		session.close();
		return bean;
	}

	@Override
	public CutPageBean<RatDamageBean> showAllRatDamage(int num, String ratDamageName) {
		CutPageBean<RatDamageBean> cut = new CutPageBean<RatDamageBean>();

		SqlSession session1 = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("ratDamageName",ratDamageName);
		map1.put("each_page", EACH_PAGE_NUM);
		int totalNum = session1.selectOne("com.project.web.mapper.ratDamageMapper.countAll", map1);
		if (totalNum == 0) {
			cut.setTotalPageNum(1);
		}
		if (totalNum % EACH_PAGE_NUM == 0) {
			cut.setTotalPageNum(totalNum / EACH_PAGE_NUM);
		} else {
			cut.setTotalPageNum(totalNum / EACH_PAGE_NUM + 1);
		}
		session1.close();
		SqlSession session2 = MyBatisUtil.getSqlSession(true);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("indexNum", (num - 1) * EACH_PAGE_NUM);
		map2.put("each_page", EACH_PAGE_NUM);
		map2.put("ratDamageName",ratDamageName);
		List<RatDamageBean> list = session2.selectList("com.project.web.mapper.ratDamageMapper.showAllRatDamage", map2);
		cut.setList(list);      
        session2.close();
		return cut;
	}

}
