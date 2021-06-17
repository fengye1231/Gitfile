package com.neusoft.dms.dept.domain;

public class EmployeeProjectDailyDo {
	
	private int employeeId;
	private int projectId;
	private int count;
	
	/**
	 * @param employeeId
	 * @param projectId
	 * @param count
	 */
	public EmployeeProjectDailyDo(int employeeId, int projectId, int count) {
		this.employeeId = employeeId;
		this.projectId = projectId;
		this.count = count;
	}

	public EmployeeProjectDailyDo() {}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
