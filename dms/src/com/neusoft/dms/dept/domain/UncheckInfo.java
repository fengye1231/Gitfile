package com.neusoft.dms.dept.domain;

import java.util.Date;

public class UncheckInfo {
	
	private int departmentId;
	private String departmentName;
	private int employeeId;
	private String employeeName;
	private int apprId;
	private String apprName;
	private String apprEmail;
	private Date date;
	
	/**
	 * @param departmentId
	 * @param departmentName
	 * @param employeeId
	 * @param employeeName
	 * @param apprId
	 * @param apprName
	 * @param apprEmail
	 * @param date
	 */
	public UncheckInfo(int departmentId, String departmentName, int employeeId,
			String employeeName, int apprId, String apprName, String apprEmail,
			Date date) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.apprId = apprId;
		this.apprName = apprName;
		this.apprEmail = apprEmail;
		this.date = date;
	}
	
	public UncheckInfo() {}

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

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getApprId() {
		return apprId;
	}

	public void setApprId(int apprId) {
		this.apprId = apprId;
	}

	public String getApprName() {
		return apprName;
	}

	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	public String getApprEmail() {
		return apprEmail;
	}

	public void setApprEmail(String apprEmail) {
		this.apprEmail = apprEmail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
