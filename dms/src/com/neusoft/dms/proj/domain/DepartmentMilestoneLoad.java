package com.neusoft.dms.proj.domain;

import java.util.List;

import com.neusoft.dms.domain.Load;

/**
 * 
 * 部门各个里程碑的工作量
 *
 */
public class DepartmentMilestoneLoad {

	private int departmentId;
	private String departmentName;
	private List<MilestoneLoad> milestoneLoads;
	
	
	/**
	 * @param departmentId
	 * @param departmentName
	 * @param milestoneLoads
	 */
	public DepartmentMilestoneLoad(int departmentId, String departmentName,
			List<MilestoneLoad> milestoneLoads) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.milestoneLoads = milestoneLoads;
	}
	
	public DepartmentMilestoneLoad() {}
	
	public Load getSummary() {
		float load = 0, extraLoad = 0;
		for (MilestoneLoad mLoad : this.milestoneLoads) {
			load += mLoad.getLoad();
			extraLoad += mLoad.getExtraLoad();
		}
		return new Load(load, extraLoad);
	}
	
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
	public List<MilestoneLoad> getMilestoneLoads() {
		return milestoneLoads;
	}
	public void setMilestoneLoads(List<MilestoneLoad> milestoneLoads) {
		this.milestoneLoads = milestoneLoads;
	}
	
}
