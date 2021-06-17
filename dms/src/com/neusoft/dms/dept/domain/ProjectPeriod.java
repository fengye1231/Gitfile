package com.neusoft.dms.dept.domain;

import java.util.Date;

public class ProjectPeriod {
	
	private int projectId;
	private Date startDate;
	private Date endDate;
	
	/**
	 * @param projectId
	 * @param startDate
	 * @param endDate
	 */
	public ProjectPeriod(int projectId, Date startDate, Date endDate) {
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public ProjectPeriod() {}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
