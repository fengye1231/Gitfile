package com.neusoft.dms.personal.domain;

public class EmployeeVo extends Employee {
	  
    private int empid;  
    private String empname;

    /**
     * 
     * @param id
     * @param name
     */
	public EmployeeVo(int empid, String empname) {
		this.setEmpid(empid);
		this.setEmpname(empname);
	}
	
	public EmployeeVo() {}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpname() {
		return empname;
	}
     
}
