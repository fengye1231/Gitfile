package com.neusoft.dms.dept.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.neusoft.dms.dept.dao.DeptDao;
import com.neusoft.dms.dept.dao.DeptDaoImpl;
import com.neusoft.dms.dept.dao.Nodes;
import com.neusoft.dms.domain.Dept;
import com.neusoft.dms.domain.Page;

public class DeptServiceImpl implements DeptService{
	private DeptDao deptDao = new DeptDaoImpl();
	
	/**
	 * 获取部门id为deptId的所有子部门
	 * @param deptId
	 * @return
	 */
	public ArrayList<Dept> getChildList(int deptId) {
		ArrayList<Dept> childList = null;
		
		childList = deptDao.getDeptBySeniorDeptId(deptId);
		
		return childList;
	}
	
	/**
	 * 设置node节点的state.selected
	 * @param nodeList
	 * @return
	 */
	public Nodes setSelectedNode(int deptId,Nodes rootNode) {
		Nodes root = rootNode;
		if(deptId == root.getId()){
			HashMap<String,Boolean> state = root.getState();
			state.put("selected", true);
			root.setState(state);
		}
		else{
			List<Nodes> nodeList = rootNode.getNodes();
			if(nodeList != null && nodeList.size() > 0){
				for(Nodes node : nodeList){
					setSelectedNode(deptId,node);
				}
			}
		}
		
		return root;
	}
	
	/**
	 * 设置node节点的state.checked
	 * @param deptID
	 * @param rootNode
	 * @return
	 */
	public Nodes setCheckedNodes(List<Integer> deptID,Nodes rootNode){
		Nodes nodes = rootNode;
		List<Nodes> nodeList = rootNode.getNodes();
		
		if(deptID != null && deptID.size() > 0){
			for(Integer deptId : deptID){
				if(deptId.intValue() == rootNode.getId()){
					HashMap<String,Boolean> state = rootNode.getState();
					state.put("checked", true);
					rootNode.setState(state);
				}
			}
			
			if(nodeList != null && nodeList.size() > 0){
				for(Integer deptId : deptID){
					for(Nodes node : nodeList){
						if(deptId.intValue() == node.getId()){
							HashMap<String,Boolean> state = node.getState();
							state.put("checked", true);
							node.setState(state);
						}
						else{
							setCheckedNodes(deptID,node);
						}
					}
				}
			}
		}
		
		return nodes;
	}
	
	/**
	 * 把子部门list封装成Page传到前台
	 * @param deptId
	 * @return
	 */
	public Page getChildListPage(int deptId,int pageNum){
		Page childListPage = null;
		ArrayList<Dept> childList = null;
		
		childList = deptDao.getDeptBySeniorDeptId(deptId);
		if(childList != null && childList.size() > 0){
			int totalNum = childList.size();
			int pageSize = 2;
			int totalPage = totalNum / pageSize + totalNum % pageSize;
			int pageNumber = Math.min(totalPage, pageNum);
			int offset = (pageNumber - 1) * pageSize;
			//int end = Math.min(totalNum, offset + pageSize);
			
			List<Dept> loads = deptDao.getDeptBySeniorDeptId(deptId,offset,pageSize);
			
			childListPage = new Page(loads, totalNum, pageNumber, totalPage, pageSize);
		}
		
		return childListPage;
	}
	
	/**
	 * 添加子部门
	 * @param deptId
	 * @return
	 */
	public int insert(Dept dept) {
		int updateCount = deptDao.insert(dept);
		return updateCount;
	}
	
	/**
	 * 删除子部门
	 * @param deptId
	 */
	public int deleteChild(int deptId) {
		int updateCount = 0;
		//获得所有的子部门
		
		ArrayList<Dept> seniorDeptList = deptDao.getDeptBySeniorDeptId(deptId);
		
		//删除当前部门
		if(deptId != 1){
			if(seniorDeptList == null || seniorDeptList.size() < 1){
				updateCount = deptDao.delete(deptId);
			}
			else
			{
				updateCount = -1;
			}
		}
		
		return updateCount;
	}

	/**
	 * 删除当前部门，包括所有子部门
	 * @param deptId 
	 */
	public int deleteParent(int deptId) {
		int updateCount = 0;
		//获得所有的子部门
		ArrayList<Dept> seniorDeptList = deptDao.getDeptBySeniorDeptId(deptId);
		
//		for(int i = 0;i < seniorDeptList.size();i++){
//			//删除所有的子部门
//			updateCount = deptDao.delete(seniorDeptList.get(i).getDeptId());
//		}
		//删除当前父节点部门
		if(deptId != 1){
			if(seniorDeptList == null || seniorDeptList.size() < 1){
				updateCount = deptDao.delete(deptId);
			}
		}
		
		return updateCount;
	}
	
	/**
	 * 修改部门
	 * @param deptId
	 * @return
	 */
	public int modify(Dept dept) {
		int updateCount = deptDao.modify(dept);
		return updateCount;
	}
	
	/**
	 * 通过deptId获取部门
	 * @param deptId
	 * @return
	 */
	public Dept getDeptById(int deptId) {
		Dept dept = null;
		dept = deptDao.getDeptById(deptId);
		if(dept != null && dept.getSeniorDeptId() == 0){
			dept.setSeniorDeptId(deptId);
		}
		return dept;
	}
	
	/**
	 * 通过deptCode获取部门
	 * @param deptCode
	 * @return
	 */
	public boolean getDeptByDeptCode(String deptCode) {
		boolean isExist = false;
		Dept dept = null;
		dept = deptDao.getDeptByDeptCode(deptCode);
		if(dept != null){
			isExist = true;
		}
		
		return isExist;
	}
	
	/**
	 * 通过deptName获取部门
	 * @param deptName
	 * @return
	 */
	public boolean getDeptByDeptName(String deptName) {
		boolean isExist = false;
		Dept dept = null;
		dept = deptDao.getDeptByDeptName(deptName);
		if(dept != null){
			isExist = true;
		}
		
		return isExist;
	}
	
	/**
	 * 获得deptId的seniorDeptId
	 * @param deptId
	 * @return
	 */
	public int getSeniorDeptId(int deptId){
		int seniorDeptId = deptDao.getSeniorDeptId(deptId);
		return seniorDeptId;
	}
	
	/**
	 * 取得所有的dept并按照tree结构组织
	 * @return
	 */
	public ArrayList<Nodes> getAllDept() {
		ArrayList<Nodes> treeList = new ArrayList<Nodes>();
		Nodes root = new Nodes();
		
		//设置根部门
		Dept deptRoot = null;
		deptRoot = deptDao.getDeptRoot();
		root.setId(deptRoot.getDeptId());
		root.setText(deptRoot.getDeptName());
		//设置href属性
		String href = root.getHref();
		href += root.getId();
		root.setHref(href);
		
		root.setNodes();
		treeList.add(root);
		//把所有的部门组织成树形结构
		root = this.makeTree(root);
		removeNullNodes(root);
		
		return treeList;
	}
	
	/**
	 * 设置叶子部门的List<Nodes> nodes 为null，使得在页面中当节点是叶子时左边不显示"+"符号
	 * @param tList
	 * @return
	 */
	private Nodes makeTree(Nodes treeRoot) {
		Nodes root = treeRoot;
		root.setNodes(deptDao.getNodes(root.getId()));
		
		List<Nodes> nodes = root.getNodes();
		if(nodes != null && nodes.size() > 0){
			for(Nodes node : nodes){
				node = this.makeTree(node);
			}
		}
		
		return root;
	}
	
	/**
	 * 
	 * @param treeRoot
	 * @return
	 */
	private Nodes removeNullNodes(Nodes treeRoot) {
		Nodes root = treeRoot;
		
		List<Nodes> nodes = root.getNodes();
		if(nodes == null || nodes.size() < 1){
			root.setNodes(null);
		}
		else{
			for(int i=0;i<nodes.size();i++){
				removeNullNodes(root.getNodes().get(i));
			}
		}
		
		return root;
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public String objectToJson(Object object){
		String jsonString = "";
		
		jsonString = JSON.toJSONString(object);
	    //zTree里面节点一定要用nodes所以要将menus替换为nodes,也可以该前面menus为nodes
//		String st = "\"nodes\":[\\[\\]],";
//	    jsonString.replaceAll(st, "");
	    //System.out.println(jsonString);
		return jsonString;
	}

}
