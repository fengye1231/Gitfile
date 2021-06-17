package com.neusoft.dms.domain;

public class Dept {
	private Integer deptId;
	private String deptCode;
	private String deptName;
	private String deptRemark;
	private Integer seniorDeptId;
	
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptRemark() {
		return deptRemark;
	}
	public void setDeptRemark(String deptRemark) {
		this.deptRemark = deptRemark;
	}
	public Integer getSeniorDeptId() {
		return seniorDeptId;
	}
	public void setSeniorDeptId(Integer seniorDeptId) {
		this.seniorDeptId = seniorDeptId;
	}
}
