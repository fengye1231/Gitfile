package com.neusoft.dms.dept.dao;
import java.sql.*;

public class DoPstmt {
	public static PreparedStatement pstmt = null;
	
	/**
	 * 
	 * @param sql
	 * @param params
	 */
	public static void doPstmt(String sql,Object[] params,Connection conn){
		if(sql != null && !sql.equals("")){
			if(params == null)
				params = new Object[0];
			if(conn != null){
				try{
					pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					for(int i = 0 ; i < params.length ; i++){
						pstmt.setObject(i+1,params[i]);
					}
					pstmt.execute();
				}catch(SQLException e){
					System.out.println("doPstmt()");
					e.printStackTrace();
				}				
			}			
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet getRs() throws SQLException{
		return pstmt.getResultSet();
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static int getUpdateCount() throws SQLException{
		return pstmt.getUpdateCount();
	}
	
	/**
	 * close the pstmt
	 * @throws SQLException
	 */
	public static void close() throws SQLException{
		pstmt.close();
	}
}
