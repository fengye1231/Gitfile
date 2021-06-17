package com.neusoft.dms.employee.domain;

public class EmployeeVo extends Employee {
	  
    private String roleName;  
    private String deptName; 
    private String  superiorName;
    private int empId;
    private String username;
    
    
    /**
     * 
     * @param roleName
     * @param deptName
     * @param superiorName
     * @param deptId
     */
    public EmployeeVo(String roleName, String deptName, String superiorName,
			int deptId) {
		this.roleName = roleName;
		this.deptName = deptName;
		this.superiorName = superiorName;
		
	}

	public EmployeeVo(int empId, String username) {
		this.empId = empId;
		this.username= username;
	}
	
	public EmployeeVo(){}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSuperiorName() {
		return superiorName;
	}
	public void setSuperiorName(String superiorName) {
		this.superiorName = superiorName;
	}
     
}
