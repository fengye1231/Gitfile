package com.neusoft.dms.dept.domain;

import java.util.List;

/**
 * 
 * 员工ID和其所参加的项目ID
 *
 */
public class EmployeeProject {
	
	private int employeeId;
	private List<Integer> projectIds;
	
	/**
	 * @param employeeId
	 * @param projectIds
	 */
	public EmployeeProject(int employeeId, List<Integer> projectIds) {
		this.employeeId = employeeId;
		this.projectIds = projectIds;
	}

	public EmployeeProject() {}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public List<Integer> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<Integer> projectIds) {
		this.projectIds = projectIds;
	}

}
