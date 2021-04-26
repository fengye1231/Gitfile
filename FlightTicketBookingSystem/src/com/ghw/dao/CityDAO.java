package com.ghw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ghw.model.City;
import com.ghw.util.FillObject;

public class CityDAO implements FillObject{
	public City selectById(int id) {
		City c = null;
		String sql = "select * from City where cityId= " + id;
		
		try {
			ResultSet rs = TemplateDAO.select(sql);
			if(rs.next()){
				c = (City)fillObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public Object fillObject(ResultSet rs) {
		City c = null; 
		try {
			c = new City(null);
			c.setId(rs.getInt("cityId"));
			c.setName(rs.getString("cityName"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
}
