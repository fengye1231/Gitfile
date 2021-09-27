package com.project.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 分页实体类
 * @author TE
 *
 * @param <T>
 */
public class CutPageBean<T> implements Serializable {
	//每一次都查出来的List集合
	private List<T> list = new ArrayList<T>() ;
	//总页数
	private int totalPageNum;
	
	
	public CutPageBean() {
		// TODO Auto-generated constructor stub
	}
	public List<T> getList() {
		return list;
	}
	
	
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	@Override
	public String toString() {
		return "CutPageBean [list=" + list + ", totalPageNum=" + totalPageNum + "]";
	}
}
