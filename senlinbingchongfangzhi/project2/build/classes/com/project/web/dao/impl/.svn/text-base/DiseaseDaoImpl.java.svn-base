package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.project.web.bean.CutPageBean;
import com.project.web.bean.DiseaseBean;
import com.project.web.dao.IDiseaseDao;
import com.project.web.util.MyBatisUtil;

public class DiseaseDaoImpl implements IDiseaseDao {

	@Override
	public void addDisease(DiseaseBean bean) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.insert("com.project.web.mapper.diseaseMapper.addDisease",bean);
		session.close();
		
	}

	@Override
	public DiseaseBean findById(int id) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		DiseaseBean bean =session.selectOne("com.project.web.mapper.diseaseMapper.findById",id);
		session.close();
		return bean;
	}

	@Override
	public CutPageBean<DiseaseBean> showAllDisease(int num, String diseaseName, String sysmptom) {
		CutPageBean<DiseaseBean> cut = new CutPageBean<DiseaseBean>();
		
		SqlSession session1 = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("diseaseName",diseaseName);
		map1.put("each_page", EACH_PAGE_NUM);
		map1.put("sysmptom",sysmptom);
		int totalNum = session1.selectOne("com.project.web.mapper.diseaseMapper.countAll", map1);
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
		map2.put("diseaseName",diseaseName);
		map2.put("sysmptom",sysmptom);
		List<DiseaseBean> list = session2.selectList("com.project.web.mapper.diseaseMapper.showAllDisease", map2);
		cut.setList(list);      
        session2.close();
		return cut;
	}
}
