package com.neusoft.dms.dept.domain;

import java.util.Date;

public class SubmitInfo {

	private int employeeId;
	private String employeeName;
	private int projectId;
	private Date date;
	
	/**
	 * @param employeeId
	 * @param employeeName
	 * @param projectId
	 * @param date
	 */
	public SubmitInfo(int employeeId, String employeeName, int projectId,
			Date date) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.projectId = projectId;
		this.date = date;
	}
	
	public SubmitInfo() {}

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

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
