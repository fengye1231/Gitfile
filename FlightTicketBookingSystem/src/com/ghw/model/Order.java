package com.ghw.model;

import java.util.Date;



public class Order {
	private int id;
	private int number;
	private Guest guest;
	private PlaneCompany planecompany;
	private String seatType;
	private String planeClass;
	private String orderStatus;
	private float totalPays;
	private int Seattype; 
	private Date produceTime;//生成时间
	private int numbers;//订单数

	public Date getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	public int getSeattype() {
		return Seattype;
	}
	public void setSeattype(int seattype) {
		Seattype = seattype;
	}
	public float getTotalPays() {
		return totalPays;
	}
	public void setTotalPays(float totalPays) {
		this.totalPays = totalPays;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public PlaneCompany getPlanecompany() {
		return planecompany;
	}
	public void setPlanecompany(PlaneCompany planecompany) {
		this.planecompany = planecompany;
	}
	public String getPlaneClass() {
		return planeClass;
	}
	public void setPlaneClass(String planeClass) {
		this.planeClass = planeClass;
	}


}
