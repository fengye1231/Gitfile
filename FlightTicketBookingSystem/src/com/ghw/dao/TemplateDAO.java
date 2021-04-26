package com.ghw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ghw.util.DBUtil;

public class TemplateDAO {
	/*
	 * ģ�����ݿ����
	 * ���棬��ѯ
	 */
	public static boolean save(String sql){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = DBUtil.getInstance().getConnection();//�������
			Statement state = conn.createStatement();//�������			
			int count = state.executeUpdate(sql);//ִ�и���
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
			rs = state.executeQuery(sql);//ִ�в�ѯ
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
