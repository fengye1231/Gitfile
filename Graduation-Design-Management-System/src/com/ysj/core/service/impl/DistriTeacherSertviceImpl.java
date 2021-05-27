package com.ysj.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ysj.core.service.DistriTeacherSertvice;
import com.ysj.core.po.Distribution;
import com.ysj.core.dao.DistriTeacherMapper;


@Service
public class DistriTeacherSertviceImpl implements DistriTeacherSertvice {
	
	
	@Autowired
	private DistriTeacherMapper distriTeacherMapper;
	


	@Override
	public int createDistribution(String sId,String sName,String tId,String tName) {
		return distriTeacherMapper.insert(sId,sName,tId,tName);
	}	
	
	
	@Override
	public  int getDistribution(String sId) {		
		return distriTeacherMapper.selectByPrimaryKey(sId);
		
		
	}
	
	
	
}

