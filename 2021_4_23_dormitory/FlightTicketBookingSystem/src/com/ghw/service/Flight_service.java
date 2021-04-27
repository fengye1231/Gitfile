package com.ghw.service;

import java.util.Date;

import com.ghw.model.*;

public class Flight_service {
	private int id;//������
	private City start;//��ʼ�ص�
	private City end;//�����յ�
	private Date startTime;//���ʱ��
	private float flightTime;//����ʱ��
	private int superPrice;//ͷ�Ȳ�Ʊ��
	private int generalPrice;//��ͨƱ��
	private int economicPrice;//���ò�Ʊ��
	private int superSeats;//ͷ�Ȳ���λ��
	private int generalSeats;//��ͨ����λ��
	private int economicSeats;//���ò���λ��
	
	private int D_to;//Ŀ�ĵ�λ������ϵ��
	private int D_from;//��ʼλ������ϵ��
	
	private double lat1;
	private double lon1;
	private double lat2;
	private double lon2;

	

	
	
	
	Ticket_service ticket=new Ticket_service();
	private double x_load_factor=ticket.count/ Ticket_service.count_total;//��λ��
	
	
	
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
	
	
	
	//private double getDistanceFromLatLonInKm(double lat1,double lon1,double lat2,double lon2) {
	private double getDistanceFromLatLonInKm() {
		
		 int R = 6371;		
//		  
//		public static final int R = 6371; // Radius of the earth in km		  
		  	  
		  double dLat = deg2rad(lat2-lat1);  // deg2rad below
		  double dLon = deg2rad(lon2-lon1); 
	      double a = 
		    Math.sin(dLat/2) * Math.sin(dLat/2) +
		    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
		    Math.sin(dLon/2) * Math.sin(dLon/2)
		    ; 
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		  int d = (int)Math.round(R * c); // Distance in km
		  return d;	
	}	
	private double deg2rad(double deg) {
		  return deg * (Math.PI/180);
		}

	
	public int getSuperPrice() {	
		return superPrice;
	}
	
	public void setSuperPrice(int superPrice) {
		
		double y=0;
		
		if (0<x_load_factor  && x_load_factor<=0.5){
			y =-0.4*x_load_factor+1;			
		} else if (0.5<x_load_factor&& x_load_factor<=0.7) {		
			y=x_load_factor+0.3;
		} else if (0.7<x_load_factor&& x_load_factor<=1) {
			y=0.2 * (1/Math.tan(x_load_factor*20-14))/Math.PI+1;
			
		}
			
		this.superPrice=(int)(y*getDistanceFromLatLonInKm()*(30+4*(D_to-D_from)));		
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
	public int getGeneralSeats() {
		return generalSeats;
	}
	public void setGeneralSeats(int generalSeats) {
		this.generalSeats = generalSeats;
	}
	public int getEconomicSeats() {
		return economicSeats;
	}
	public void setEconomicSeats(int economicSeats) {
		this.economicSeats = economicSeats;
	}
	
	
}







