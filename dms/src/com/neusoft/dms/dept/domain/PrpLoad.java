package com.neusoft.dms.dept.domain;

/**
 * 
 * PRP阶段工作量
 *
 */
public class PrpLoad {

	private int prpId;
	private String prpName;
	private float load;
	private float extraLoad;
	
	/**
	 * @param prpId
	 * @param prpName
	 * @param load
	 * @param extraLoad
	 */
	public PrpLoad(int prpId, String prpName, float load,
			float extraLoad) {
		this.prpId = prpId;
		this.prpName = prpName;
		this.load = load;
		this.extraLoad = extraLoad;
	}
	
	public PrpLoad() {}

	public int getPrpId() {
		return prpId;
	}

	public void setPrpId(int prpId) {
		this.prpId = prpId;
	}

	public String getPrpName() {
		return prpName;
	}

	public void setPrpName(String prpName) {
		this.prpName = prpName;
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
