package com.ghw.util;

public class AirlineTicketBookingSystemException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自定义异常类
	public AirlineTicketBookingSystemException(String message) {
		super(message);
		System.out.println("自定义异常");
	}
	
}
