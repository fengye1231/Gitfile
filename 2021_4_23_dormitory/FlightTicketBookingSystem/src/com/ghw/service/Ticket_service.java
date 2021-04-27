package com.ghw.service;

import com.ghw.model.*;
public class Ticket_service {
	private int id;
	public int count;
	
	public static int count_total;
	
	
	
	private Flight_service flight;
	private Order order;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Flight_service getFlight() {
		return flight;
	}
	public void setFlight(Flight_service flight) {
		this.flight = flight;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
