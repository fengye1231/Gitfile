package com.neusoft.dms.dept.domain;

import java.util.List;

import com.neusoft.dms.domain.Load;

public class EmployeeProjectLoad {

	private int employeeId;
	private String employeeName;
	private List<ProjectLoad> projectLoads;
	
	public EmployeeProjectLoad(int employeeId, String employeeName,
			List<ProjectLoad> projectLoads) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.projectLoads = projectLoads;
	}
	
	public EmployeeProjectLoad() {}
	
	public Load getSummary() {
		float load = 0, extraLoad = 0;
		for (ProjectLoad pLoad : this.projectLoads) {
			load += pLoad.getLoad();
			extraLoad += pLoad.getExtraLoad();
		}
		return new Load(load, extraLoad);
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

	public List<ProjectLoad> getProjectLoads() {
		return projectLoads;
	}

	public void setProjectLoads(List<ProjectLoad> projectLoads) {
		this.projectLoads = projectLoads;
	}
	
}
