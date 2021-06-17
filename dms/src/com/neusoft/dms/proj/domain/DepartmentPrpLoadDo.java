package com.neusoft.dms.proj.domain;

import java.util.List;

import com.neusoft.dms.domain.Load;

/**
 * 
 * 部门里程碑工作量数据库元素对象
 *
 */
public class DepartmentPrpLoadDo {

	private int departmentId;
	private String departmentName;
	private int prpId;
	private String prpName;
	private float load;
	private float extraLoad;
	
	/**
	 * @param departmentId
	 * @param departmentName
	 * @param prpId
	 * @param prpName
	 * @param load
	 * @param extraLoad
	 */
	public DepartmentPrpLoadDo(int departmentId, String departmentName,
			int prpId, String prpName, float load, float extraLoad) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.prpId = prpId;
		this.prpName = prpName;
		this.load = load;
		this.extraLoad = extraLoad;
	}
	
	public DepartmentPrpLoadDo() {}

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

	public int getPrpId() {
		return prpId;
	}

	public void setPrpId(int prpId) {
		this.prpId = prpId;
	}

	public String getPrpName() {
		return prpName;
	}

	public void setPrpName(String prpName) {
		this.prpName = prpName;
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
