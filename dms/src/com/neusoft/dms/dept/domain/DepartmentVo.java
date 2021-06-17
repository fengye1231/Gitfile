package com.neusoft.dms.dept.domain;

public class DepartmentVo {
	
	private int id;
	private String name;
	
	/**
	 * @param id
	 * @param name
	 */
	public DepartmentVo(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public DepartmentVo() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
