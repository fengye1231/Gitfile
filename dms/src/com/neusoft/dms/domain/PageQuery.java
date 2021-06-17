package com.neusoft.dms.domain;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.dms.util.Constant;

public class PageQuery {
	
	private int pageNum;
	private int pageSize;
	private Map<String, Object> params;
	
	
	/**
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param param
	 */
	public PageQuery(int pageNum, int pageSize) {
		this.params = new HashMap<String, Object>();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	/**
	 * 
	 * @param pageNum
	 * @param param
	 */
	public PageQuery(int pageNum) {
		this(pageNum, Constant.PAGE_SIZE);
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public PageQuery setPageNum(int pageNum) {
		this.pageNum = pageNum;
		return this;
	}
	public int getPageSize() {
		return pageSize;
	}
	public PageQuery setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	
	/**
	 * 获取参数
	 * @param key
	 * @return Object
	 */
	public Object getParam(String key) {
		return this.params.get(key);
	}
	
	/**
	 * 设置参数
	 * @param key
	 * @param value
	 */
	public PageQuery setParam(String key, Object value) {
		this.params.put(key, value);
		return this;
	}

}
