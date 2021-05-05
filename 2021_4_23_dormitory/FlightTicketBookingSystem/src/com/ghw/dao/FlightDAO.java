package com.ghw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.time.LocalDateTime;
import com.ghw.model.*;
import java.sql.Timestamp;


public class FlightDAO {

	public Flight selectById(int id) {
		Flight f = null;
		String sql = "select * from flight where flightId = " + id;
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			if(rs.next()){
				f = new Flight();
				f.setId(rs.getInt("flightId"));
				
				int cityId = rs.getInt("flightStartCity");
				CityDAO dao = new CityDAO();
				City start = dao.selectById(cityId);
				f.setStart(start);				
				
				cityId = rs.getInt("flightEndCity");
				City end = dao.selectById(cityId);
				f.setEnd(end);
				
				

				f.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());

				f.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
				f.setSuperPrice(rs.getInt("superPrice"));
				f.setGeneralPrice(rs.getInt("generalPrice"));
				f.setEconomicPrice(rs.getInt("economicPrice"));
				f.setSuperSeats(rs.getInt("superSeats"));
//				f.setGeneralSeats(rs.getInt("generalSeats"));
//				f.setEconomicSeats(rs.getInt("economicSeats"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public ArrayList<Flight> selectDate(LocalDateTime date){
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Flight f = null;
		String sql = "select * from flight where startTime>" + Timestamp.valueOf(date);
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			while(rs.next()){
				f = new Flight();
				f.setId(rs.getInt("flightId"));
				int cityId = rs.getInt("flightStartCity");
				CityDAO dao = new CityDAO();
				City start = dao.selectById(cityId);
				f.setStart(start);
				cityId = rs.getInt("flightEndCity");
				City end = dao.selectById(cityId);
				f.setEnd(end);
				f.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
				f.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
				f.setSuperPrice(rs.getInt("superPrice"));
				f.setGeneralPrice(rs.getInt("generalPrice"));
				f.setEconomicPrice(rs.getInt("economicPrice"));
				f.setSuperSeats(rs.getInt("seats"));
//				f.setGeneralSeats(rs.getInt("generalSeats"));
//				f.setEconomicSeats(rs.getInt("economicSeats"));
				flights.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}

	public ArrayList<Flight> selectDateByStartTimeDuring(LocalDateTime date){
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Flight f = null;

		LocalDateTime date2=date.plusHours(1);
		LocalDateTime date1=date.minusHours(1);
		String sql = "select * from flight where startTime between " + Timestamp.valueOf(date1)+" and " + Timestamp.valueOf(date2);

		try {
			ResultSet rs = TemplateDAO.select(sql);
			while(rs.next()){
				f = new Flight();
				f.setId(rs.getInt("flightId"));
				flights.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}


	public ArrayList<Flight> selectDateByEndTimeDuring(LocalDateTime date){
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Flight f = null;

		LocalDateTime date2=date.plusHours(1);
		LocalDateTime date1=date.minusHours(1);
		String sql = "select * from flight where endTime between " + Timestamp.valueOf(date1)+" and " + Timestamp.valueOf(date2);

		try {
			ResultSet rs = TemplateDAO.select(sql);
			while(rs.next()){
				f = new Flight();
				f.setId(rs.getInt("flightId"));
				flights.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}



	public ArrayList<Flight> selectAll(){
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Flight f = null;
		String sql = "select * from flight";
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			while(rs.next()){
				f = new Flight();
				
				
				
				
				f.setId(rs.getInt("flightId"));
				int cityId = rs.getInt("flightStartCity");
				CityDAO dao = new CityDAO();
				City start = dao.selectById(cityId);
				f.setStart(start);
				cityId = rs.getInt("flightEndCity");
				City end = dao.selectById(cityId);
				f.setEnd(end);
				
				TicketDAO ticketdao = new TicketDAO();
				int ticketNum=ticketdao.getTicketNum(rs.getInt("flightId"));				
				f.setTicketAcount(ticketNum);	
				
				
				f.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
				f.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
				f.setSuperPrice(rs.getInt("superPrice"));
				f.setGeneralPrice(rs.getInt("generalPrice"));
				f.setEconomicPrice(rs.getInt("economicPrice"));
				f.setSuperSeats(rs.getInt("seats"));
//				f.setSuperSeats(rs.getInt("superSeats"));
//				f.setGeneralSeats(rs.getInt("generalSeats"));
//				f.setEconomicSeats(rs.getInt("economicSeats"));
				flights.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}

}
