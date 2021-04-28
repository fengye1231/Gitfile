package com.ghw.util;

import java.sql.*;

/*
 * JDBC����SQLServer2005������ 
 */
public class DBUtil {
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SqlServer���ݿ�����
	private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=FlightTicketBookingSystem";//���ʵ�ַ
	private static final String USERNAME = "sa";//�û���
	private static final String PASSWORD = "Lx123321";//����
	private static DBUtil instance = null;//��ǰ�����ʵ��
	public DBUtil(){
		try {
			Class.forName(DRIVER);//��ʼ��ʱ����SQLServer����
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
	 * ����������ģʽ
	 */
	public static DBUtil getInstance(){
		if(instance == null){
			synchronized (DBUtil.class) {
				if(instance == null){
					instance = new DBUtil();
				}
			}
		}
		return instance;
	}
	//�������
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	//�ر�����
	public void closeConnection(Connection conn) {
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("���ݿ��޷��ر�");
		}
	}
	//�رս����
	public void closeResultSet(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//�ر����
	public void closeStatement(Statement state){
		try {
			if(state != null){
				state.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
