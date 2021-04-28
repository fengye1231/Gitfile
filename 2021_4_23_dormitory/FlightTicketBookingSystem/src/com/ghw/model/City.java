package com.ghw.model;

public class City {
	private int id;
	private String name;
	
	private double lat=0;
	private double lon=0;


	private int s=0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	
	public double getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	
	
	
	public City(String name){
		this.name = name;
	}
}
