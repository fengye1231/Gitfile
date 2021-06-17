package com.neusoft.dms.prj.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import net.sf.json.util.NewBeanInstanceStrategy;

public class AllPrjEmp {
	private int prjID;
	private String manager;
	private String research;
	private String finance;
	private String law;
	private String market;
	private String validate;
	private String product;
	private String application;
	private String purchase;
	private String service;
	private String quality;
	private String other;
	
	public String getOther() {
		return other;
	}

	private List<String> others = new ArrayList<String>();
	public AllPrjEmp(){
		manager = "暂无";
		research = "暂无";
		finance = "暂无";
		law = "暂无";
		market = "暂无";
		validate = "暂无";
		product = "暂无";
		application = "暂无";
		purchase = "暂无";
		service = "暂无";
		quality= "暂无";
		other="暂无";
	}
	public int getPrjID() {
		return prjID;
	}
	public void setPrjID(int prjID) {
		this.prjID = prjID;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getResearch() {
		return research;
	}
	public void setResearch(String research) {
		this.research = research;
	}
	public String getFinance() {
		return finance;
	}
	public void setFinance(String finance) {
		this.finance = finance;
	}
	public String getLaw() {
		return law;
	}
	public void setLaw(String law) {
		this.law = law;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getPurchase() {
		return purchase;
	}
	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	public void setOther() {
		this.other = otherToString();
	}
	
	public void addOther(String o){
		others.add(o);
	}
	
	public String otherToString(){
		String toString = "";
		for(String s:others){
			toString = toString + ", " + s;
		}
		return toString;
	}
}
