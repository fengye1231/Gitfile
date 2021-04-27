package com.ghw.util;

import java.sql.*;

/*
 * JDBC连接SQLServer2005工具类 
 */
public class DBUtil {
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//SqlServer数据库驱动
	private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=FlightTicketBookingSystem";//访问地址
	private static final String USERNAME = "root";//用户名
	private static final String PASSWORD = "";//密码
	private static DBUtil instance = null;//当前对象的实例
	public DBUtil(){
		try {
			Class.forName(DRIVER);//初始化时加载SQLServer驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 单例懒加载模式
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
	//获得连接
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	//关闭连接
	public void closeConnection(Connection conn) {
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("数据库无法关闭");
		}
	}
	//关闭结果集
	public void closeResultSet(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//关闭语句
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
