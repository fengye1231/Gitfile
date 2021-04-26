package com.ghw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ghw.model.*;

public class OrderDAO {
	private PlaneCompany planecompany;

	public void add(Order o){
		String sql = "insert into OrderPapper(guestId,travelId,producetime,Numbers,TotalPays)" +
				" values('" + o.getGuest().getId() + "','"
				+ o.getPlanecompany().getId() + "',getdate(),'"
				+ o.getNumber() + "','" + o.getTotalPays() + "')";
		TemplateDAO.save(sql);
	}
	
	public void delete(int id){
		String sql = "delete from OrderPapper where orderId = " + id;
		TemplateDAO.save(sql);
	}
	
	public void edit(Order o){
		String sql = "update OrderPapper set ";
		TemplateDAO.save(sql);
	}
	
	public Order selectById(int id){
		Order o = null;
		String sql = "select * from OrderPapper where orderId = " + id;
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			if(rs.next()){
				o = new Order();
				o.setId(rs.getInt("orderId"));
				o.setProduceTime(rs.getDate("producetime"));
				o.setNumber(rs.getInt("Numbers"));
				o.setTotalPays(rs.getFloat("TotalPays"));
				GuestDAO guestDAO = new GuestDAO();
				Guest guest = guestDAO.selectById(rs.getInt("guestId"));
				o.setGuest(guest);
				TravelAgentDAO travelAgentDAO = new TravelAgentDAO();
				PlaneCompany travelAgent = travelAgentDAO.selectById(rs.getInt("travelId"));
				o.setPlanecompany(planecompany);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public Order selectByGuestId(int guestId){
		Order o = null;
		String sql = "select * from OrderPapper where guestId = " + guestId;
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			if(rs.next()){
				o = new Order();
				o.setId(rs.getInt("orderId"));
				o.setProduceTime(rs.getDate("producetime"));
				o.setNumber(rs.getInt("Numbers"));
				o.setTotalPays(rs.getFloat("TotalPays"));
				GuestDAO guestDAO = new GuestDAO();
				Guest guest = guestDAO.selectById(rs.getInt("guestId"));
				o.setGuest(guest);
				TravelAgentDAO travelAgentDAO = new TravelAgentDAO();
				PlaneCompany travelAgent = travelAgentDAO.selectById(rs.getInt("travelId"));
				o.setPlanecompany(planecompany);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public ArrayList<Order> selectByProduceTime(Date date){
		ArrayList<Order> orders = new ArrayList<Order>();
		Order o = null;
		String sql = "select * from OrderPapper where producetime = " + date;
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			if(rs.next()){
				o = new Order();
				o.setId(rs.getInt("orderId"));
				o.setProduceTime(rs.getDate("producetime"));
				o.setNumber(rs.getInt("Numbers"));
				o.setTotalPays(rs.getInt("TotalPays"));
				GuestDAO guestDAO = new GuestDAO();
				Guest guest = guestDAO.selectById(rs.getInt("guestId"));
				o.setGuest(guest);
				TravelAgentDAO travelAgentDAO = new TravelAgentDAO();
				PlaneCompany travelAgent = travelAgentDAO.selectById(rs.getInt("travelId"));
				o.setPlanecompany(planecompany);
				orders.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public ArrayList<Order> selectByIdCard(String idCard){
		return null;
	}
	
	public ArrayList<Order> selectAll(){
		ArrayList<Order> orders = new ArrayList<Order>();
		Order o = null;
		String sql = "select * from OrderPapper ";
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			if(rs.next()){
				o = new Order();
				o.setId(rs.getInt("orderId"));
				o.setProduceTime(rs.getDate("producetime"));
				o.setNumber(rs.getInt("Numbers"));
				o.setTotalPays(rs.getInt("TotalPays"));
				GuestDAO guestDAO = new GuestDAO();
				Guest guest = guestDAO.selectById(rs.getInt("guestId"));
				o.setGuest(guest);
				TravelAgentDAO travelAgentDAO = new TravelAgentDAO();
				PlaneCompany travelAgent = travelAgentDAO.selectById(rs.getInt("travelId"));
				o.setPlanecompany(planecompany);
				orders.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static void main(String[] args) {
		new OrderDAO().selectByGuestId(7);
	}
}
