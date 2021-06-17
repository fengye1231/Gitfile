package com.neusoft.dms.dept.service;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.dept.dao.Nodes;
import com.neusoft.dms.domain.Dept;
import com.neusoft.dms.domain.Page;

public interface DeptService {
	/**
	 * 获取部门id为deptId的所有子部门
	 * @param deptId
	 * @return
	 */
	public ArrayList<Dept> getChildList(int deptId);
	
	/**
	 * 设置node节点的state.selected
	 * @param nodeList
	 * @return
	 */
	public Nodes setSelectedNode(int deptId,Nodes node);
	
	/**
	 * 设置node节点的state.checked
	 * @param deptID
	 * @param rootNode
	 * @return
	 */
	public Nodes setCheckedNodes(List<Integer> deptID,Nodes rootNode);
	
	/**
	 * 把子部门list封装成Page传到前台
	 * @param deptId
	 * @return
	 */
	public Page getChildListPage(int deptId,int pageNum);
	
	/**
	 * 添加子部门
	 * @param deptId
	 * @return
	 */
	public int insert(Dept dept);
	
	/**
	 * 删除子部门
	 * @param deptId
	 */
	public int deleteChild(int deptId);
	
	/**
	 * 删除当前部门，包括所有子部门
	 * @param deptId 
	 */
	public int deleteParent(int deptId);
	
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
	public boolean getDeptByDeptCode(String deptCode);
	
	/**
	 * 通过deptName获取部门
	 * @param deptName
	 * @return
	 */
	public boolean getDeptByDeptName(String deptName);
	
	/**
	 * 获得deptId的seniorDeptId
	 * @param deptId
	 * @return
	 */
	public int getSeniorDeptId(int deptId);
	
	/**
	 * 取得所有的dept并按照tree结构组织
	 * @return
	 */
	public ArrayList<Nodes> getAllDept();
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public String objectToJson(Object object);
	
}
