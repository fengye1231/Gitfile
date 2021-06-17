package com.neusoft.dms.personal.domain;

import java.util.Date;

public class Daily {
	private int dailyId;
	private int empId;
	private Date submitDate;
	private String desc;
	private float totalWorkload;
	private float overTimeLoad;
	private int prjId;
	private int prpId;
	private String tomorrowPlan;
	private String status;
	private Date reviewDate;
	private int reviewEmpId;
	private String reason;
	public int getDailyId() {
		return dailyId;
	}
	public void setDailyId(int dailyId) {
		this.dailyId = dailyId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getTotalWorkload() {
		return totalWorkload;
	}
	public void setTotalWorkload(float totalWorkload) {
		this.totalWorkload = totalWorkload;
	}
	public float getOverTimeLoad() {
		return overTimeLoad;
	}
	public void setOverTimeLoad(float overTimeLoad) {
		this.overTimeLoad = overTimeLoad;
	}
	public int getPrjId() {
		return prjId;
	}
	public void setPrjId(int prjId) {
		this.prjId = prjId;
	}
	public int getPrpId() {
		return prpId;
	}
	public void setPrpId(int prpId) {
		this.prpId = prpId;
	}
	public String getTomorrowPlan() {
		return tomorrowPlan;
	}
	public void setTomorrowPlan(String tomorrowPlan) {
		this.tomorrowPlan = tomorrowPlan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getReviewdate() {
		return reviewDate;
	}
	public void setReviewdate(Date reviewdate) {
		this.reviewDate = reviewdate;
	}
	public int getReviewEmpId() {
		return reviewEmpId;
	}
	public void setReviewEmpId(int reviewEmpId) {
		this.reviewEmpId = reviewEmpId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
