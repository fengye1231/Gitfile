package com.neusoft.dms.permission.domain;

public class PermissionVo {

	private int perId;
	private String perName;
	private String perPath;
	private String leaderPermissionId;
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
	public String getLeaderPermissionId() {
		return leaderPermissionId;
	}
	public void setLeaderPermissionId(String leaderPermissionId) {
		this.leaderPermissionId = leaderPermissionId;
	}
	
	
}
