package com.ghw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ghw.model.PlaneCompany;

public class TravelAgentDAO {

	public PlaneCompany selectById(int id) {
		PlaneCompany ta = null;
		String sql = "select * from TravelAgency where travelId = " + id;
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			if(rs.next()){
				ta = new PlaneCompany();
				ta.setId(rs.getInt("travelId"));
				ta.setName(rs.getString("travelName"));
				ta.setAdress(rs.getString("travelAddress"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ta;
	}

}
