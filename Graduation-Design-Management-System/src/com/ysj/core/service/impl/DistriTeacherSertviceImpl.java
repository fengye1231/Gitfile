package com.ysj.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysj.core.po.Student;
import com.ysj.core.po.Teacher;
import com.ysj.core.po.Title;
import com.ysj.core.service.DistriTeacherSertvice;

@Service
public class DistriTeacherSertviceImpl implements DistriTeacherSertvice {
	


	@Override
	public Teacher distriTeacher(Student student,Teacher teacher) {
		
		return distriTeacherMapper.insert(Student student,Teacher teacher);
//			return Teacher;

		
		

	}






}
