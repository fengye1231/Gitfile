package com.ghw.service;

import com.ghw.model.*;
public class Ticket {
	private int id;
	public int count;
	
	public static int count_total;
	
	
	
	private Flight flight;
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
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
