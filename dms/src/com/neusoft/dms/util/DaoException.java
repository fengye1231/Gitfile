package com.neusoft.dms.util;

/**
 * 
 * DAO层异常类型
 *
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException(String msg){
		super(msg);
	}

	public DaoException(Throwable e){
		super(e);
	}
	
	public DaoException(String msg, Throwable e){
		super(msg, e);
	}

}
