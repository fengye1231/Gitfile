package com.ghw.model;

//import java.util.Date;

import java.time.LocalDateTime;

import com.ghw.model.*;

public class Flight {
	private int id;//锟斤拷锟斤拷锟斤拷
	private City start;//锟斤拷始锟截碉拷
	private City end;//锟斤拷锟斤拷锟秸碉拷
	
	
//	private City LocationLat;//锟斤拷始锟截碉拷
//	private City LocationLon;//锟斤拷锟斤拷锟秸碉拷
	
	private LocalDateTime startTime;//锟斤拷锟绞憋拷锟�
	private LocalDateTime endTime;//锟斤拷锟斤拷时锟斤拷
	private int superPrice;//头锟饺诧拷票锟斤拷
	private int generalPrice;//锟斤拷通票锟斤拷
	private int economicPrice;//锟斤拷锟矫诧拷票锟斤拷
	private int seats=1;
//	private int superSeats;//头锟饺诧拷锟斤拷位锟斤拷
//	private int generalSeats;//锟斤拷通锟斤拷锟斤拷位锟斤拷
//	private int economicSeats;//锟斤拷锟矫诧拷锟斤拷位锟斤拷
	
//	private int D_to;//目锟侥碉拷位锟斤拷锟斤拷锟斤拷系锟斤拷
//	private int D_from;//锟斤拷始位锟斤拷锟斤拷锟斤拷系锟斤拷
	

	
//	private double lat1;
//	private double lon1;
//	private double lat2;
//	private double lon2;

//	private Ticket ticket;
	
	private int ticketNum1=1;
	
	public void setTicketAcount(int ticketNum) {

		this.ticketNum1=ticketNum;
			
	}
	
	
	public int getTicketAcount() {
		return ticketNum1;
	}
	
	public int getSuperSeats() {
		return seats;
	}
	public void setSuperSeats(int seats) {
		this.seats = seats;
	}
	
//	Ticket ticket=new Ticket();
	//private double x_load_factor=ticket.count/ Ticket.count_total;//锟斤拷位锟斤拷
//	private double x_load_factor=ticketNum1/ seats;
	
	private double x_load_factor=ticketNum1/seats;
	
	
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
	
//	public City getLocationLat() {
//		return end;
//	}
//	public void setLocationLat(City end) {
//		this.end = end;
//	}
//	
//	
//	public City getLocationLon() {
//		return end;
//	}
//	public void setLocationLon(City end) {
//		this.end = end;
//	}
	
	
	
	
	
	
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	
	
	//private double getDistanceFromLatLonInKm(double lat1,double lon1,double lat2,double lon2) {
	private double getDistanceFromLatLonInKm() {
		
		 int R = 6371;		
//		  
//		public static final int R = 6371; // Radius of the earth in km		  
		  	  
//		  double dLat = deg2rad(lat2-lat1);  // deg2rad below
		  double dLat = deg2rad(end.getLat()-start.getLat());
		  
		  
//		  double dLon = deg2rad(lon2-lon1); 
		  double dLon=deg2rad(end.getLon()-start.getLon());
		  
		  
	      double a = 
		    Math.sin(dLat/2) * Math.sin(dLat/2) +
		    Math.cos(deg2rad(start.getLat())) * Math.cos(deg2rad(end.getLat())) * 
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
			
		this.superPrice=(int)(y*getDistanceFromLatLonInKm()*(30+4*(end.getS()-start.getS())));		
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







