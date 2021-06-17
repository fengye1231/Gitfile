package com.neusoft.dms.proj.domain;

import java.util.List;

import com.neusoft.dms.domain.Load;

/**
 * 
 * 部门各个里程碑的工作量
 *
 */
public class DepartmentPrpLoad {

	private int departmentId;
	private String departmentName;
	private List<PrpLoad> prpLoads;
	
	
	/**
	 * @param departmentId
	 * @param departmentName
	 * @param prpLoads
	 */
	public DepartmentPrpLoad(int departmentId, String departmentName,
			List<PrpLoad> prpLoads) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.prpLoads = prpLoads;
	}
	
	public DepartmentPrpLoad() {}
	
	public Load getSummary() {
		float load = 0, extraLoad = 0;
		for (PrpLoad mLoad : this.prpLoads) {
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
	public List<PrpLoad> getPrpLoads() {
		return prpLoads;
	}
	public void setPrpLoads(List<PrpLoad> prpLoads) {
		this.prpLoads = prpLoads;
	}
	
}
