package com.ghw.util;

public class AirlineTicketBookingSystemException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//�Զ����쳣��
	public AirlineTicketBookingSystemException(String message) {
		super(message);
		System.out.println("�Զ����쳣");
	}
	
}
