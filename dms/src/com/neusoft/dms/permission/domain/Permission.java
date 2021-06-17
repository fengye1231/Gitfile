package com.neusoft.dms.permission.domain;

public class Permission {

	private int perId;
	private String perName;
	private String perPath;
	private Integer leaderPermissionId;
	
	public int getPerId() {
		return perId;
	}
	public void setPerId(int perId) {
		this.perId = perId;
	}
	public String getPerName() {
		return perName;
	}
	public void setPerName(String perName) {
		this.perName = perName;
	}
	public String getPerPath() {
		return perPath;
	}
	public void setPerPath(String perPath) {
		this.perPath = perPath;
	}
	public Integer getLeaderPermissionId() {
		return leaderPermissionId;
	}
	public void setLeaderPermissionId(Integer leaderPermissionId) {
		this.leaderPermissionId = leaderPermissionId;
	}
	
	
}
