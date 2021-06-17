package com.neusoft.dms.dept.domain;

public class ProjectLoad {

	private int projectId;
	private String projectName;
	private float load;
	private float extraLoad;
	
	/**
	 * @param projectId
	 * @param projectName
	 * @param load
	 * @param extraLoad
	 */
	public ProjectLoad(int projectId, String projectName, float load,
			float extraLoad) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.load = load;
		this.extraLoad = extraLoad;
	}
	
	public ProjectLoad() {}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
