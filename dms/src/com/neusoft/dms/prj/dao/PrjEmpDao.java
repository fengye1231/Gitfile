package com.neusoft.dms.prj.dao;

import java.util.List;

import com.neusoft.dms.prj.domain.PrjEmp;
import com.neusoft.dms.prj.domain.PrjEmpVo;
import com.neusoft.dms.util.DaoException;

public interface PrjEmpDao {
	//添加人员到项目中
	public void addPrjEmp(PrjEmp pe) throws DaoException;
	
	//从项目中删除人员及角色
	public void delPrjEmp(int pid, int prID) throws DaoException;
	
	//更改角色人员
	public void updatePrjEmp(PrjEmp pe) throws DaoException;
	
	//根据项目ID获取该项目人员
	public List<PrjEmpVo> getPrjEmpByPrjID(int prjID) throws DaoException;
	
	//获取人员目前参与项目信息
	public PrjEmpVo getPrjByEmpID(int empID) throws DaoException;
	
	//获取人员参与的所有项目信息
	public List<PrjEmpVo> getAllPrjByEmpID(int empID) throws DaoException;
}
