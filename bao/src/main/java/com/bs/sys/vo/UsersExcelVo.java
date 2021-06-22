package com.bs.sys.vo;

import lombok.Data;

/**
 * 和excel表一一映射
 */
@Data
public class UsersExcelVo {
    //登陆名称
    private String name;
    //用户名
    private String loginname;
    //所属部门
    private String deptname;
    //直属领导
    private String mgrname;
    //用户备注
    private String remark;
    //用户地址
    private String address;
    //角色
    private String rolename;
    
    
    
    //li添加
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
    public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
    
    public String getMgrname() {
		return mgrname;
	}

	public void setMgrname(String mgrname) {
		this.mgrname = mgrname;
	}
	
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
    public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
	
	
	
	
	
}
