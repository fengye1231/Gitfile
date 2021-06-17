package com.neusoft.dms.domain;

public class Load {

	/**
	 * 工作量
	 */
	private float load;
	
	/**
	 * 加班工作量
	 */
	private float extraLoad;

	/**
	 * @param load
	 * @param extraLoad
	 */
	public Load(float load, float extraLoad) {
		this.load = load;
		this.extraLoad = extraLoad;
	}
	
	public Load() {}

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
