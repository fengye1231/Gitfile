package com.ghw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ghw.model.Guest;

public class GuestDAO {
	public void add(Guest g) {
		String sql = "insert into guest(guestName,guestAddress,guestIdCard,StartDate," +
				"guestTelephone) values('" + g.getName() + "','"
				+ g.getAdress() + "','" + g.getIDCard() + "','"
				+ g.getStartDate() + "','" + g.getTelephone() + "')";
		TemplateDAO.save(sql);
	}

	public void delete(int id) {
		String sql = "delete from guest where guestId = " + id;
		TemplateDAO.save(sql);
	}

	public void edit(Guest g) {
		String sql = "update guest set guestname = '" + g.getName()
				+ "',guestaddress = '" + g.getAdress() + "',guestidcard = '"
				+ g.getIDCard() + "',startdate = '" + g.getStartDate()
				+ "',guesttelephone = '" + g.getTelephone()
				+ "' where guestid = " + g.getId();
		TemplateDAO.save(sql);
	}

	public Guest selectById(int id) {
		Guest g = null;
		String sql = "select * from guest where guestId=" + id;

		try {
			ResultSet rs = TemplateDAO.select(sql);
			if (rs.next()) {
				g = new Guest();
				g.setId(rs.getInt("guestId"));
				g.setName(rs.getString("guestName"));
				g.setAdress(rs.getString("guestAddress"));
				g.setIDCard(rs.getString("guestIdCard"));
				g.setStartDate(rs.getDate("StartDate"));
				g.setTelephone(rs.getString("guestTelephone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}

	public Guest selectByIdCard(String idCard) {
		Guest g = null;
		String sql = "select * from guest where guestIdCard = " + idCard;

		try {
			ResultSet rs = TemplateDAO.select(sql);
			if (rs.next()) {
				g = new Guest();
				g.setId(rs.getInt("guestId"));
				g.setName(rs.getString("guestName"));
				g.setAdress(rs.getString("guestAddress"));
				g.setIDCard(rs.getString("guestIdCard"));
				g.setStartDate(rs.getDate("StartDate"));
				g.setTelephone(rs.getString("guestTelephone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}

	public ArrayList<Guest> selectByName(String name) {
		ArrayList<Guest> guests = new ArrayList<Guest>();
		Guest g = null;
		String sql = "select * from guest where guestname = " + name;

		try {
			ResultSet rs = TemplateDAO.select(sql);
			while (rs.next()) {
				g = new Guest();
				g.setId(rs.getInt("guestId"));
				g.setName(rs.getString("guestName"));
				g.setAdress(rs.getString("guestAddress"));
				g.setIDCard(rs.getString("guestIdCard"));
				g.setStartDate(rs.getDate("StartDate"));
				g.setTelephone(rs.getString("guestTelephone"));
				guests.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guests;
	}

	public ArrayList<Guest> selectAll() {
		ArrayList<Guest> guests = new ArrayList<Guest>();
		Guest g = null;
		String sql = "select * from guest ";

		try {
			ResultSet rs = TemplateDAO.select(sql);
			while (rs.next()) {
				g = new Guest();
				g.setId(rs.getInt("guestId"));
				g.setName(rs.getString("guestName"));
				g.setAdress(rs.getString("guestAddress"));
				g.setIDCard(rs.getString("guestIdCard"));
				g.setStartDate(rs.getDate("StartDate"));
				g.setTelephone(rs.getString("guestTelephone"));
				guests.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guests;
	}
}
