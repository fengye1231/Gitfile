package com.neusoft.dms.dept.domain;

public class EmployeeProjectLoadDo {

	private int employeeId;
	private String employeeName;
	private int projectId;
	private String projectName;
	private float load;
	private float extraLoad;
	
	/**
	 * @param employeeId
	 * @param employeeName
	 * @param projectId
	 * @param projectName
	 * @param load
	 * @param extraLoad
	 */
	public EmployeeProjectLoadDo(int employeeId, String employeeName,
			int projectId, String projectName, float load, float extraLoad) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.projectId = projectId;
		this.projectName = projectName;
		this.load = load;
		this.extraLoad = extraLoad;
	}
	
	public EmployeeProjectLoadDo() {}

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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
