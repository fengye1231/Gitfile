package com.ysj.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ysj.core.service.DistriTeacherSertvice;
import com.ysj.core.po.Distribution;
import com.ysj.core.po.DistributionExample;
import com.ysj.core.po.Student;
import com.ysj.core.po.StudentExample;
import com.ysj.core.dao.DistriTeacherMapper;


@Service
public class DistriTeacherSertviceImpl implements DistriTeacherSertvice {
	
	
	@Autowired
	private DistriTeacherMapper distriTeacherMapper;
	


	@Override
	public int createDistribution(Distribution distribution) {
		return distriTeacherMapper.insert(distribution);
	}	
	
	
	@Override
	public  List<Distribution> getDistribution(String sId) {	

		
		DistributionExample example = new DistributionExample();
			//用来封装查询条件的
		DistributionExample.Criteria criteria=example.createCriteria();
			
			criteria.andSIdEqualTo(sId);
			
			List<Distribution> list= distriTeacherMapper.selectByPrimaryKey(sId);
			//select * from Student where sId=? and sPwd=?
			if (list!=null&& list.size()>0) {
				 return list;
			}
			return null;
		}
		
		
	}
	
	
	


