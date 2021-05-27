package com.ysj.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysj.core.po.Student;
import com.ysj.core.po.Teacher;
import com.ysj.core.po.Title;
import com.ysj.core.service.DistriTeacherSertvice;
import com.ysj.core.po.Distribution;
import com.ysj.core.dao.distriTeacherMapper;

@Service
public class DistriTeacherSertviceImpl implements DistriTeacherSertvice {
	


	@Override
	public int createDistribution(String sId,String sName,String tId,String tName) {
		
		
		return distriTeacherMapper.insert(sId,sName,tId,tName);


	}






}
