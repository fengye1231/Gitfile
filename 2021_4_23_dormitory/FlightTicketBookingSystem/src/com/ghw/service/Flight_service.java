package com.ghw.service;

import java.util.Date;

import com.ghw.model.*;

public class Flight_service {
	private int id;//锟斤拷锟斤拷锟斤拷
	private City start;//锟斤拷始锟截碉拷
	private City end;//锟斤拷锟斤拷锟秸碉拷
	private Date startTime;//锟斤拷锟绞憋拷锟�
	private float flightTime;//锟斤拷锟斤拷时锟斤拷
	private int superPrice;//头锟饺诧拷票锟斤拷
	private int generalPrice;//锟斤拷通票锟斤拷
	private int economicPrice;//锟斤拷锟矫诧拷票锟斤拷
	private int seats;
//	private int superSeats;//头锟饺诧拷锟斤拷位锟斤拷
//	private int generalSeats;//锟斤拷通锟斤拷锟斤拷位锟斤拷
//	private int economicSeats;//锟斤拷锟矫诧拷锟斤拷位锟斤拷
	
	private int D_to;//目锟侥碉拷位锟斤拷锟斤拷锟斤拷系锟斤拷
	private int D_from;//锟斤拷始位锟斤拷锟斤拷锟斤拷系锟斤拷
	
	private double lat1;
	private double lon1;
	private double lat2;
	private double lon2;

	

	
	
	
	Ticket_service ticket=new Ticket_service();
	private double x_load_factor=ticket.count/ Ticket_service.count_total;//锟斤拷位锟斤拷
	
	
	
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
		return seats;
	}
	public void setSuperSeats(int superSeats) {
		this.seats = seats;
	}
	
	
	
	
	
	
//	public int getSuperSeats() {
//		return superSeats;
//	}
//	public void setSuperSeats(int superSeats) {
//		this.superSeats = superSeats;
//	}
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







