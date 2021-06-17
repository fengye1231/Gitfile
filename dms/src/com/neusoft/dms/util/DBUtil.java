package com.neusoft.dms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {

	/**
	 * 获取连接
	 * @return Connection
	 * @throws DaoException
	 */
	public static Connection getConnection() throws DaoException {
		Connection con = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/dms");
		    con = ds.getConnection();

		} catch (NamingException e) {
			throw new DaoException(e.getMessage(), e);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
		return con;
	}
	
	/**
	 * 关闭连接
	 * @param con
	 * @throws DaoException
	 */
	public static void close(Connection con) throws DaoException {
		try {
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

    /**
     * 关闭资源
     * @param pstmt 
     * @param rs
     * @throws DaoException
     */
	public static void close(PreparedStatement pstmt,ResultSet rs) throws DaoException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param pstmt
	 * @throws DaoException
	 */
	public static void close(PreparedStatement pstmt) throws DaoException {
		close(pstmt, null);
	}
	
	/**
	 * 关闭资源
	 * @param con
	 * @param pstmt
	 * @param rs
	 * @throws DaoException
	 */
	public static void close(Connection con,PreparedStatement pstmt,ResultSet rs) throws DaoException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}
}
