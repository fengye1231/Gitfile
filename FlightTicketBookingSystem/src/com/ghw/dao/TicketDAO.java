package com.ghw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ghw.model.*;


public class TicketDAO {
	public boolean add(Ticket t) {
		boolean flag = false;
		String sql = "insert into Ticket(flightId,StartTime,ticketType,discount,orderId)" +" values(" + t.getFlight().getId() + ",'"
				+ t.getFlight().getStartTime() + "'," + t.getOrder().getSeattype() + ","
				+ t.getCount() + "," + t.getOrder().getId() + ")";
		System.out.println(sql);
		flag = TemplateDAO.save(sql);
		return flag;
	}

	public void delete(int id) {
		String sql = "delete from Ticket where ticketId = " + id;
		TemplateDAO.save(sql);
	}

	public void edit(Ticket o) {
		String sql = "update Ticket set ";
		TemplateDAO.save(sql);
	}

	public ArrayList<Ticket> selectById(int id) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Ticket t = null;
		String sql = "select * from Ticket where ticketId = " + id;
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			while(rs.next()){
				t = new Ticket();
				t.setId(rs.getInt("ticketId"));
				t.setCount(rs.getInt("discount"));
				int flightId = rs.getInt("flightId");
				FlightDAO dao = new FlightDAO();
				Flight flight = dao.selectById(flightId);
				t.setFlight(flight);
				OrderDAO orderDAO = new OrderDAO();
				Order o = orderDAO.selectById(rs.getInt("orderId"));
				t.setOrder(o);
				tickets.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	public ArrayList<Ticket> selectByProduceTime(Date date) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Ticket t = null;
		String sql = "select * from Ticket where producetime = " + date;
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			while(rs.next()){
				t = new Ticket();
				t.setId(rs.getInt("ticketId"));
				t.setCount(rs.getInt("discount"));
				int flightId = rs.getInt("flightId");
				FlightDAO dao = new FlightDAO();
				Flight flight = dao.selectById(flightId);
				t.setFlight(flight);
				OrderDAO orderDAO = new OrderDAO();
				Order o = orderDAO.selectById(rs.getInt("orderId"));
				t.setOrder(o);
				tickets.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	public Ticket selectByOrderId(int id) {
		Ticket t = null;
		String sql = "select * from Ticket where orderId  = " + id;
		System.out.println(sql);
		try {
			ResultSet rs = TemplateDAO.select(sql);
			if(rs.next()){
				t = new Ticket();
				t.setId(rs.getInt("ticketId"));
				t.setCount(rs.getInt("discount"));
				int flightId = rs.getInt("flightId");
				FlightDAO dao = new FlightDAO();
				Flight flight = dao.selectById(flightId);
				t.setFlight(flight);
				OrderDAO orderDAO = new OrderDAO();
				Order o = orderDAO.selectById(rs.getInt("orderId"));
				t.setOrder(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public ArrayList<Ticket> selectAll() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Ticket t = null;
		String sql = "select * from Ticket ";
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			while(rs.next()){
				t = new Ticket();
				t.setId(rs.getInt("ticketId"));
				t.setCount(rs.getInt("discount"));
				int flightId = rs.getInt("flightId");
				FlightDAO dao = new FlightDAO();
				Flight flight = dao.selectById(flightId);
				t.setFlight(flight);
				OrderDAO orderDAO = new OrderDAO();
				Order o = orderDAO.selectById(rs.getInt("orderId"));
				t.setOrder(o);
				tickets.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}
}
