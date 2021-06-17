package com.neusoft.dms.domain;

import java.util.List;

import com.neusoft.dms.util.Constant;


@SuppressWarnings("unchecked")
public class Page {

	private List list;
	private int totalNum; //总的查询结果数
	private int pageNum; //当前页码
	private int totalPage; //总页数
	private int pageSize = Constant.PAGE_SIZE; //每页显示的结果数
	
	
	/**
	 * @param list
	 * @param totalNum
	 * @param pageNum
	 * @param totalPage
	 * @param pageSize
	 */
	public Page(List list, int totalNum, int pageNum, int totalPage, int pageSize) {
		this.list = list;
		this.totalNum = totalNum;
		this.pageNum = pageNum;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
	}
	
	/**
	 * @param list
	 * @param totalNum
	 * @param pageNum
	 * @param totalPage
	 */
	public Page(List list, int totalNum, int pageNum, int totalPage) {
		this.list = list;
		this.totalNum = totalNum;
		this.pageNum = pageNum;
		this.totalPage = totalPage;
	}
	
	public Page() {}
	
	public int getPageSize() {
		return pageSize;
	}
	public Page setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public Page setTotalNum(int totalNum) {
		this.totalNum = totalNum;
		return this;
	}
	
	public List getList() {
		return list;
	}
	public Page setList(List list) {
		this.list = list;
		return this;
	}
	public int getPageNum() {
		return pageNum;
	}
	public Page setPageNum(int pageNum) {
		this.pageNum = pageNum;
		return this;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public Page setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		return this;
	}
	
}
