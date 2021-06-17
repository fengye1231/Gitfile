package com.neusoft.dms.personal.domain;

import com.neusoft.dms.employee.dao.EmployeeDaoImpl;
import com.neusoft.dms.prj.dao.PrjDeptDao;

public class DailyVo extends Daily{
	private String empName;
	private String prjName;
	private String prpName;
	private String reviewEmpName;
	
	public DailyVo(String empName, String prjName, String prpName,
			String reviewEmpName) {
		this.empName = empName;
		this.prjName = prjName;
		this.prpName = prpName;
		this.reviewEmpName = reviewEmpName;
	}
	
	public DailyVo() {}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPrjName() {
		return prjName;
	}
	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}
	public String getPrpName() {
		return prpName;
	}
	public void setPrpName(String prpName) {
		this.prpName = prpName;
	}
	public String getReviewEmpName() {
		return reviewEmpName;
	}
	public void setReviewEmpName(String reviewEmpName) {
		this.reviewEmpName = reviewEmpName;
	}

	
}
