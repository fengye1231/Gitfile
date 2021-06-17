package com.neusoft.dms.util;

public interface State {

	/**
	 * 执行失败
	 */
	public static final int FAILED = 0;
	
	/**
	 * 成功
	 */
	public static final int OK = 200;
	
	/**
	 * 错误的请求
	 */
	public static final int BAD_REQUEST = 400;
	
	/**
	 * 服务器内部错误
	 */
	public static final int INTERNAL_SERVER_ERROR = 500;
	
	/**
	 * 未找到
	 */
	public static final int NOT_FOUND = 404;
	
	
	/**
	 * 冲突
	 */
	public static final int CONFLICT = 409;

	
}
