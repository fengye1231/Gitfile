package com.neusoft.dms.util;

/**
 * 
 * 参数解析错误异常类型
 *
 */
public class InvaliedParamException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvaliedParamException(String msg){
		super(msg);
	}

	public InvaliedParamException(Throwable e){
		super(e);
	}
	
	public InvaliedParamException(String msg, Throwable e){
		super(msg, e);
	}
}
