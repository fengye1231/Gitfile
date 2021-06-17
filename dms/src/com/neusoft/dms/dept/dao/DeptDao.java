package com.neusoft.dms.dept.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONException;

import com.neusoft.dms.domain.Dept;

public interface DeptDao {
	
	/**
	 * 把符合要求的部门放到list
	 * @param sql
	 * @param params
	 * @return
	 */
	public ArrayList<Dept> getList(String sql,Object[] params);
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public ArrayList<Nodes> getNodesList(String sql,Object[] params);
	
	/**
	 * 增加部门
	 * @param dept
	 * @return
	 */
	public int insert(Dept dept);
	
	/**
	 * 删除部门
	 * @param deptId
	 */
	public int delete(int deptId);
	
	/**
	 * 修改部门
	 * @param deptId
	 * @return
	 */
	public int modify(Dept dept);
	
	/**
	 * 通过deptId获取部门
	 * @param deptId
	 * @return
	 */
	public Dept getDeptById(int deptId);
	
	/**
	 * 通过deptCode获取部门
	 * @param deptCode
	 * @return
	 */
	public Dept getDeptByDeptCode(String deptCode);
	
	/**
	 * 通过deptName获取部门
	 * @param deptName
	 * @return
	 */
	public Dept getDeptByDeptName(String deptName);
	
	/**
	 * 获取所有seniorDeptId为seniorDeptId的子部门list
	 * @param seniorDeptId
	 * @return
	 */
	public ArrayList<Dept> getDeptBySeniorDeptId(int seniorDeptId);
	
	/**
	 * 
	 * @param seniorDeptId
	 * @param offset
	 * @param end
	 * @return
	 */
	public ArrayList<Dept> getDeptBySeniorDeptId(int seniorDeptId,int offset,int end);
	
	/**
	 * 获取上级部门deptId
	 * @param deptId
	 * @return
	 */
	public int getSeniorDeptId(int deptId);
	
	/**
	 * 获取根部门
	 * @return
	 */
	public Dept getDeptRoot();
	
	/**
	 * 取得所有的部门
	 * @return
	 */
	public ArrayList<Dept> getAllDept();
	
	/**
	 * 获取上级部门为seniorDeptId的部门，并转化为Nodes
	 * @param seniorDeptId
	 * @return
	 */
	public ArrayList<Nodes> getNodes(int seniorDeptId);
	
	/**
	 * Java数据库ResultSet转json
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws JSONException
	 */
	public String resultSetToJson(ResultSet rs) throws SQLException,JSONException;
	
}
