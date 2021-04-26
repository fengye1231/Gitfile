package com.ghw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ghw.util.DBUtil;

public class TemplateDAO {
	/*
	 * 模板数据库访问
	 * 保存，查询
	 */
	public static boolean save(String sql){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = DBUtil.getInstance().getConnection();//获得连接
			Statement state = conn.createStatement();//创建语句			
			int count = state.executeUpdate(sql);//执行更新
			if(count > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static ResultSet select(String sql){
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			Statement state = conn.createStatement();			
			rs = state.executeQuery(sql);//执行查询
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
