package com.ghw.model;

import java.util.Date;


public class Flight {
	private int id;//������
	private City start;//��ʼ�ص�
	private City end;//�����յ�
	private Date startTime;//���ʱ��
	private float flightTime;//����ʱ��
	private int superPrice;//ͷ�Ȳ�Ʊ��
	private int generalPrice;//��ͨƱ��
	private int economicPrice;//���ò�Ʊ��
	private int superSeats;//ͷ�Ȳ���λ��
//	private int generalSeats;//��ͨ����λ��
//	private int economicSeats;//���ò���λ��
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
