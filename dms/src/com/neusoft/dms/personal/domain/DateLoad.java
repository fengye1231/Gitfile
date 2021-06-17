package com.neusoft.dms.personal.domain;

import java.util.Date;

public class DateLoad {

	private int dailyId;
	private Date submitDate;
	private float load;
	private float extraLoad;
	private int prjId;
	private int prpId;
	private String desc;
	
	/**
	 * @param projectId
	 * @param projectName
	 * @param load
	 * @param extraLoad
	 */
	public DateLoad(int dailyId,Date submitDate, float load,
			float extraLoad,int prjId,int prpId, String desc) {
		this.dailyId = dailyId;
		this.submitDate = submitDate;
		this.load = load;
		this.extraLoad = extraLoad;
		this.prjId = prjId;
		this.prpId = prpId;
		this.desc = desc;
	}
	
	public DateLoad() {}
	
	public DateLoad(int int1, String string, float float1, float float2) {
		
	}

	public DateLoad(int int1, String string, float float1, float float2,
			int int2, int int3, String string2) {
		
	}
			
	public DateLoad(int int1,String string1, String string2,String string3,float float1,float float2,String string4){
		
	}

	public int getdailyId(){
		return dailyId;
	}
	
	public void setdailyId(int dailyId){
		this.dailyId = dailyId;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
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

	public int getPrjId() {
		return prjId;
	}
	
	public void setPrjId(int prjId) {
		this.prjId = prjId;
	}
	
	public int getPrpId(){
		return prpId;
	}
	
	public void setPrpId(int prpId){
		this.prpId = prpId;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public void setDesc(String desc){
		this.desc = desc;
	}

	
}
