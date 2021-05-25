package com.ysj.core.service;

import java.util.List;


import com.ysj.core.po.Teacher;
import com.ysj.core.po.Student;

public interface DistriTeacherSertvice {
//	public Teacher findTeacher(String tId, String tPwd);
//	public Teacher findTeacherById(String tId);
//	public int editInfo(Teacher teacher);
//	public List <Teacher> Teacherlist(Teacher teacher);
//	public int createTeacher(Teacher teacher);
//	public int editInfo1(BaseDept baseDept);
//	public int editInfo2(BaseMajor baseMajor);
//	public List<Teacher> findTeacherBydept(String dept);
	
	public Teacher distriTeacher(Student student,Teacher teacher);
	
}
