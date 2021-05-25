package com.ysj.core.po;

public class Distribution {
	
	private String sId;
	
	private Student sName;

    private Teacher teacher;
	
    public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	
	

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}