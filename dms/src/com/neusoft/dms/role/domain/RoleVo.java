package com.neusoft.dms.role.domain;

public class RoleVo extends Role {
    private int roleId;
    private String roleName;
    
    public RoleVo(){
    	
    }
	public RoleVo(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
