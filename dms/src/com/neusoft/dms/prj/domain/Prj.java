package com.neusoft.dms.prj.domain;

import java.util.Date;

public class Prj {
	private int prjID;
	private String prjCode;
	private String prjName;
	private Date startDate;
	private Date endDate;
	private String remark;
	private String status;
	
	
	/**
	 * @param prjCode
	 * @param prjName
	 */
	
	
	public Prj() {}
	
	public Prj(int prjID, String prjCode, String prjName, Date startDate,
			Date endDate, String remark, String status) {
		super();
		this.prjID = prjID;
		this.prjCode = prjCode;
		this.prjName = prjName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remark = remark;
		this.status = status;
	}




	public Prj(String prjCode, String prjName, Date startDate, Date endDate,String remark) {
		super();
		this.prjCode = prjCode;
		this.prjName = prjName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remark = remark;
	}

	
	
	public Prj(String prjCode, String prjName, Date startDate, Date endDate,
			String remark, String status) {
		super();
		this.prjCode = prjCode;
		this.prjName = prjName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remark = remark;
		this.status = status;
	}


	public int getPrjID() {
		return prjID;
	}
	public void setPrjID(int prjID) {
		this.prjID = prjID;
	}
	public String getPrjCode() {
		return prjCode;
	}
	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
		System.out.println("set code");
	}
	public String getPrjName() {
		return prjName;
	}
	public void setPrjName(String prjName) {
		this.prjName = prjName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setState(String status) {
		this.status = status;
	}
	
	

}
