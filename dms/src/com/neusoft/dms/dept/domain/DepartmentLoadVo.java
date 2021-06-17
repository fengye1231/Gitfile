package com.neusoft.dms.dept.domain;

public class DepartmentLoadVo {

	private int departmentId;
	private String departmentCode;
	private String departmentName;
	private int dailyNum;	//有效日报数
	private float load;	//有效工作时间
	private float extraLoad;
	private float unacceptedLoad;
	
	
	/**
	 * @param departmentId
	 * @param departmentCode
	 * @param departmentName
	 * @param dailyNum
	 * @param load
	 * @param extraLoad
	 * @param unacceptedLoad
	 */
	public DepartmentLoadVo(int departmentId, String departmentCode, String departmentName,
			int dailyNum, float load, float extraLoad, float unacceptedLoad) {
		this.departmentId = departmentId;
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.dailyNum = dailyNum;
		this.load = load;
		this.extraLoad = extraLoad;
		this.unacceptedLoad = unacceptedLoad;
	}
	
	public DepartmentLoadVo() {}
	
	public String getDepartmentCode() {
		return departmentCode;
	}
	public DepartmentLoadVo setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
		return this;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public DepartmentLoadVo setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
		return this;
	}
	public int getDailyNum() {
		return dailyNum;
	}
	public DepartmentLoadVo setDailyNum(int dailyNum) {
		this.dailyNum = dailyNum;
		return this;
	}
	public float getLoad() {
		return load;
	}
	public DepartmentLoadVo setLoad(float load) {
		this.load = load;
		return this;
	}
	public float getExtraLoad() {
		return extraLoad;
	}
	public DepartmentLoadVo setExtraLoad(float extraLoad) {
		this.extraLoad = extraLoad;
		return this;
	}
	public float getUnacceptedLoad() {
		return unacceptedLoad;
	}
	public DepartmentLoadVo setUnacceptedLoad(float unacceptedLoad) {
		this.unacceptedLoad = unacceptedLoad;
		return this;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public DepartmentLoadVo setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
		return this;
	}
	
	
	
}
