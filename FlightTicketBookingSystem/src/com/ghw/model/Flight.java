package com.ghw.model;

import java.util.Date;


public class Flight {
	private int id;//航班编号
	private City start;//起始地点
	private City end;//到达终点
	private Date startTime;//起飞时间
	private float flightTime;//飞行时间
	private int superPrice;//头等舱票价
	private int generalPrice;//普通票价
	private int economicPrice;//经济舱票价
	private int superSeats;//头等舱座位数
//	private int generalSeats;//普通舱座位数
//	private int economicSeats;//经济舱座位数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public City getStart() {
		return start;
	}
	public void setStart(City start) {
		this.start = start;
	}
	public City getEnd() {
		return end;
	}
	public void setEnd(City end) {
		this.end = end;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public float getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(float flightTime) {
		this.flightTime = flightTime;
	}
	public int getSuperPrice() {
		return superPrice;
	}
	public void setSuperPrice(int superPrice) {
		this.superPrice = superPrice;
	}
	public int getGeneralPrice() {
		return generalPrice;
	}
	public void setGeneralPrice(int generalPrice) {
		this.generalPrice = generalPrice;
	}
	public int getEconomicPrice() {
		return economicPrice;
	}
	public void setEconomicPrice(int economicPrice) {
		this.economicPrice = economicPrice;
	}
	public int getSuperSeats() {
		return superSeats;
	}
	public void setSuperSeats(int superSeats) {
		this.superSeats = superSeats;
	}
//	public int getGeneralSeats() {
//		return generalSeats;
//	}
//	public void setGeneralSeats(int generalSeats) {
//		this.generalSeats = generalSeats;
//	}
//	public int getEconomicSeats() {
//		return economicSeats;
//	}
//	public void setEconomicSeats(int economicSeats) {
//		this.economicSeats = economicSeats;
//	}
}
