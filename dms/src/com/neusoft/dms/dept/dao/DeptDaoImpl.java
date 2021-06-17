package com.neusoft.dms.dept.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.neusoft.dms.domain.Dept;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class DeptDaoImpl implements DeptDao{
	/**
	 * 把符合要求的部门放到list中
	 * @param sql
	 * @param params
	 * @return
	 */
	public ArrayList<Dept> getList(String sql, Object[] params) {
		ArrayList<Dept> deptList = null;
		
		try {
			Connection conn = DBUtil.getConnection();
			DoPstmt.doPstmt(sql, params, conn);
			
			ResultSet rs = DoPstmt.getRs();
			if(rs != null){
				deptList = new ArrayList<Dept>();
				while(rs.next()){
					Dept temp = new Dept();
					temp.setDeptId(rs.getInt(1));
					temp.setDeptCode(rs.getString(2));
					temp.setDeptName(rs.getString(3));
					temp.setDeptRemark(rs.getString(4));
					temp.setSeniorDeptId(rs.getInt(5));
					
					deptList.add(temp);
				}
			}
			
			DBUtil.close(null,null,rs);
			DoPstmt.close();
			DBUtil.close(conn,null,null);
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deptList;
	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public ArrayList<Nodes> getNodesList(String sql,Object[] params){
		ArrayList<Nodes> nodesList = null;
		
		try {
			Connection conn = DBUtil.getConnection();
			DoPstmt.doPstmt(sql, params, conn);
			
			ResultSet rs = DoPstmt.getRs();
			if(rs != null){
				nodesList = new ArrayList<Nodes>();
				while(rs.next()){
					Nodes temp = new Nodes();
					temp.setId(rs.getInt(1));
					
					String href = temp.getHref();
					href += temp.getId();
					temp.setHref(href);
					
					rs.getString(2);
					temp.setText(rs.getString(3));
					rs.getString(4);
					temp.setpId(rs.getInt(5));
					temp.setNodes();
					
					nodesList.add(temp);
				}
			}
			
			DBUtil.close(null,null,rs);
			DoPstmt.close();
			DBUtil.close(conn,null,null);
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nodesList;
	}
	
	/**
	 * 增加部门
	 * @param dept
	 * @return
	 */
	public int insert(Dept dept) {
		int updateCount = 0;
		String sql = "insert into dept values(null,?,?,?,?)";
		Object[] params = {dept.getDeptCode(),dept.getDeptName(),dept.getDeptRemark(),
				dept.getSeniorDeptId()};
		try {
			Connection conn = DBUtil.getConnection();
			DoPstmt.doPstmt(sql, params, conn);
			
			DoPstmt.close();
			DBUtil.close(conn,null,null);
		} catch (DaoException e) {
			updateCount = -1;
			e.printStackTrace();
		} catch (SQLException e) {
			updateCount = -1;
			e.printStackTrace();
		}
		
		return updateCount;
	}
	
	/**
	 * 删除部门
	 * @param deptId
	 */
	public int delete(int deptId) {
		int updateCount = 0;
		String sql = "delete from dept where deptId=?";
		Object[] params = {deptId};
		
		try {
			Connection conn = DBUtil.getConnection();
			DoPstmt.doPstmt(sql, params, conn);
			
			updateCount = DoPstmt.getUpdateCount();
			DoPstmt.close();
			DBUtil.close(conn,null,null);
		} catch (DaoException e) {
			updateCount = -1;
			e.printStackTrace();
		} catch (SQLException e) {
			updateCount = -1;
			e.printStackTrace();
		}
		
		return updateCount;
	}
	
	/**
	 * 修改部门
	 * @param deptId
	 * @return
	 */
	public int modify(Dept dept) {
		int updateCount = 0;
		String sql = "update dept set deptCode=?,deptName=?,deptRemark=?," +
				"seniorDeptId=? where deptId=?";
		Object[] params = {dept.getDeptCode(),dept.getDeptName(),
				dept.getDeptRemark(),dept.getSeniorDeptId(),dept.getDeptId()};
		
		try {
			Connection conn = DBUtil.getConnection();
			DoPstmt.doPstmt(sql, params, conn);
			
			DoPstmt.close();
			DBUtil.close(conn,null,null);
		} catch (DaoException e) {
			updateCount = -1;
			e.printStackTrace();
		} catch (SQLException e) {
			updateCount = -1;
			e.printStackTrace();
		}
		
		return updateCount;
	}
	
	/**
	 * 通过deptId获取部门
	 * @param deptId
	 * @return
	 */
	public Dept getDeptById(int deptId) {
		Dept dept = null;
		ArrayList<Dept> deptList = null;
		
		String sql = "select * from dept where deptId = ?";
		Object[] params = {deptId};
		
		deptList = this.getList(sql, params);
		if(deptList != null && deptList.size() > 0){
			dept = deptList.get(0);
		}
		
		return dept;
	}
	
	/**
	 * 通过deptCode获取部门
	 * @param deptCode
	 * @return
	 */
	public Dept getDeptByDeptCode(String deptCode) {
		Dept dept = null;
		ArrayList<Dept> deptList = null;
		
		String sql = "select * from dept where deptCode = ?";
		Object[] params = {deptCode};
		
		deptList = this.getList(sql, params);
		if(deptList != null && deptList.size() > 0){
			dept = deptList.get(0);
		}
		
		return dept;
	}
	
	/**
	 * 通过deptName获取部门
	 * @param deptName
	 * @return
	 */
	public Dept getDeptByDeptName(String deptName) {
		Dept dept = null;
		ArrayList<Dept> deptList = null;
		
		String sql = "select * from dept where deptName = ?";
		Object[] params = {deptName};
		
		deptList = this.getList(sql, params);
		if(deptList != null && deptList.size() > 0){
			dept = deptList.get(0);
		}
		
		return dept;
	}
	
	/**
	 * 获取所有seniorDeptId为seniorDeptId的子部门list
	 * @param seniorDeptId
	 * @return
	 */
	public ArrayList<Dept> getDeptBySeniorDeptId(int seniorDeptId){
		ArrayList<Dept> deptList = null;
		
		String sql = "select * from dept where seniorDeptId = ?";
		Object[] params = {seniorDeptId};
		
		deptList = this.getList(sql, params);
		
		return deptList;
	}
	
	/**
	 * 
	 * @param seniorDeptId
	 * @param offset
	 * @param end
	 * @return
	 */
	public ArrayList<Dept> getDeptBySeniorDeptId(int seniorDeptId,int offset,int end){
		ArrayList<Dept> deptList = null;
		
		String sql = "select * from dept where seniorDeptId = ? limit ?,?";
		Object[] params = {seniorDeptId,offset,end};
		
		deptList = this.getList(sql, params);
		
		return deptList;
	}
	
	
	/**
	 * 获取上级部门deptId
	 * @param deptId
	 * @return
	 */
	public int getSeniorDeptId(int deptId){
		int seniorDeptId = deptId;
		Dept dept = null;
		dept = this.getDeptById(deptId);
		
		if(dept != null && dept.getSeniorDeptId() != null && dept.getSeniorDeptId() != 0){
			seniorDeptId = dept.getSeniorDeptId();
		}
		return seniorDeptId;
	}
	
	/**
	 * 获取根部门
	 * @return
	 */
	public Dept getDeptRoot(){
		Dept deptRoot = null;
		ArrayList<Dept> deptList = null;
		
		String sql = "select * from dept where seniorDeptId is null";
		
		deptList = this.getList(sql, null);
		if(deptList != null && deptList.size() > 0){
			deptRoot = deptList.get(0);
		}
		
		return deptRoot;
	}
	
	/**
	 * 取得所有的部门
	 * @return
	 */
	public ArrayList<Dept> getAllDept(){
		ArrayList<Dept> allDeptList = null;
		
		String sql = "select * from dept";
		allDeptList = getList(sql,null);
		
		return allDeptList;
	}
	
	/**
	 * 获取上级部门为seniorDeptId的部门，并转化为Nodes
	 * @param seniorDeptId
	 * @return
	 */
	public ArrayList<Nodes> getNodes(int seniorDeptId){
		ArrayList<Nodes> allDeptList = null;
		
		String sql = "select * from dept where seniorDeptId = ?";
		Object[] params = {seniorDeptId};
		
		allDeptList = this.getNodesList(sql, params);
		
		return allDeptList;
	}
	/**
	 * Java数据库ResultSet转json
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws JSONException
	 */
	@Override
	public String resultSetToJson(ResultSet rs) throws SQLException,
			JSONException {
		// json数组
		JSONArray array = new JSONArray();
		
		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		
		//遍历ResultSet中的每条数据
        while (rs.next()) {
        	JSONObject jsonObj = new JSONObject();
           
        	//遍历每一列
            for (int i = 1; i <= columnCount; i++) {
            	String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            //array.put(jsonObj);
            array.add(jsonObj);
        }
        
        return array.toString();
	}
	
}
