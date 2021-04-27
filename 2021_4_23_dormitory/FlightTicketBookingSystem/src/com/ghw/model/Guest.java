package com.ghw.model;

//import java.util.Date;
import java.time.LocalDateTime;

public class Guest {
	private String name;
	private String IDCard;
	private String adress;
	private int id;
	private LocalDateTime StartDate;
	private String Telephone;
	public LocalDateTime getStartDate() {
		return StartDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		StartDate = startDate;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
