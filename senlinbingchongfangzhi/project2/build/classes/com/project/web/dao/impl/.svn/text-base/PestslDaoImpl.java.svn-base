package com.project.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.project.web.bean.CutPageBean;
import com.project.web.bean.PestslBean;
import com.project.web.dao.IPestslDao;
import com.project.web.util.MyBatisUtil;

public class PestslDaoImpl implements IPestslDao {

	@Override
	public void addPestsl(PestslBean bean) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		session.insert("com.project.web.mapper.pestslMapper.addPestsl",bean);
		session.close();
	}

	@Override
	public PestslBean findById(int id) {
		SqlSession session = MyBatisUtil.getSqlSession(true);
		PestslBean bean = session.selectOne("com.project.web.mapper.pestslMapper.findById",id);
		session.close();
		return bean;
	}

	
	@Override
	public CutPageBean<PestslBean> showAllPestsl(int num, String pestslName, String hostName) {
		CutPageBean<PestslBean> cut = new CutPageBean<PestslBean>();

		SqlSession session1 = MyBatisUtil.getSqlSession(true);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("pestslName",pestslName);
		map1.put("each_page", EACH_PAGE_NUM);
		map1.put("hostName",hostName);
		int totalNum = session1.selectOne("com.project.web.mapper.pestslMapper.countAll", map1);
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
		map2.put("pestslName",pestslName);
		map2.put("hostName",hostName);
		List<PestslBean> list = session2.selectList("com.project.web.mapper.pestslMapper.showAllPestsl", map2);
		cut.setList(list);      
        session2.close();
		return cut;
	}
}
