package com.ghw.model;

import com.ghw.*;
public class Ticket_yuanshi {
	private int id;
	private double count;
	private Flight_yuanshi flight;
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
	public void setCount(double count) {
		this.count = count;
	}
	public Flight_yuanshi getFlight() {
		return flight;
	}
	public void setFlight(Flight_yuanshi flight) {
		this.flight = flight;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
