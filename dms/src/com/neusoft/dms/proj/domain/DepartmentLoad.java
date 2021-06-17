package com.neusoft.dms.proj.domain;

/**
 * 
 * 部门工作量
 *
 */
public class DepartmentLoad {
	
	private int departmentId;
	private String departmentName;
	private float load;
	private float extraLoad;
	
	/**
	 * @param departmentId
	 * @param departmentName
	 * @param load
	 * @param extraLoad
	 */
	public DepartmentLoad(int departmentId, String departmentName, float load,
			float extraLoad) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.load = load;
		this.extraLoad = extraLoad;
	}

	public DepartmentLoad() {}
	
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public float getLoad() {
		return load;
	}

	public void setLoad(float load) {
		this.load = load;
	}

	public float getExtraLoad() {
		return extraLoad;
	}

	public void setExtraLoad(float extraLoad) {
		this.extraLoad = extraLoad;
	}

}
