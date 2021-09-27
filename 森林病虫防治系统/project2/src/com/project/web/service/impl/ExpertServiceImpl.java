package com.project.web.service.impl;

import java.util.List;
import com.project.web.bean.CutPageBean;
import com.project.web.bean.ExpertBean;
import com.project.web.dao.IExpertDao;
import com.project.web.dao.impl.ExpertDaoImpl;
import com.project.web.service.IExpertService;

/**
 * 专家业务层实现类
 * @author GF
 *
 */
public class ExpertServiceImpl implements IExpertService{
	
	IExpertDao dao = new ExpertDaoImpl();
	
	@Override
	public void addExpert(ExpertBean bean) {
		// TODO Auto-generated method stub
		dao.addExpert(bean);		
	}

	@Override
	public ExpertBean findExpertById(int id) {
		// TODO Auto-generated method stub
		return dao.findExpertById(id);
		
	}

	@Override
	public void updateExpert(int id,String position,String phoneNum,String workPlace,String email) {
		// TODO Auto-generated method stub
		dao.updateExpert(id, position, phoneNum, workPlace, email);		
	}

	@Override
	public void delExpert(int id) {
		// TODO Auto-generated method stub
		dao.delExpert(id);
	}

	@Override
	public CutPageBean<ExpertBean> showAllExpert(int num, String name, String special, String workPlace) {
		CutPageBean<ExpertBean> cut = new CutPageBean<ExpertBean>();
		int totalNum = dao.findAllExpert(name, special, workPlace);	
		if(totalNum == 0) {
			cut.setTotalPageNum(1);
		}
		if(totalNum%EACH_PAGE_NUM==0) {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM);
		}else {
			cut.setTotalPageNum(totalNum/EACH_PAGE_NUM + 1);
		}
		
		int indexNum = (num-1)*EACH_PAGE_NUM;	
		List<ExpertBean> list = dao.showAllExpert(indexNum, EACH_PAGE_NUM, name, special, workPlace);
		cut.setList(list);		
		return cut;
	}

	@Override
	public List<String> showAllName() {
		// TODO Auto-generated method stub		
		return dao.showAllName();
	}

}
