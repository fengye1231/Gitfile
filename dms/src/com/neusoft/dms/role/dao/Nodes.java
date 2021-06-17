package com.neusoft.dms.role.dao;

import java.util.ArrayList;
import java.util.List;

public class Nodes {
	private int id=0;
	private int pId=0;
	private String text="";
	private List<Nodes> nodes=null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Nodes> getNodes() {
		return nodes;
	}
	public void setNodes(List<Nodes> nodes) {
		this.nodes = nodes;
	}
	public void setNodes(){
		nodes=new ArrayList<Nodes>();
	}
	
	

}
