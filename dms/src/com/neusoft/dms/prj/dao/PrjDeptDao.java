package com.neusoft.dms.prj.dao;

import java.util.List;

import com.neusoft.dms.prj.domain.PrjDept;
import com.neusoft.dms.prj.domain.PrjDeptVo;
import com.neusoft.dms.util.DaoException;

public interface PrjDeptDao {
	
	//添加项目与部门的联系
	public void addPrjDept(int prjID,int deptID)throws DaoException;
	
	//删除项目于部门的联系
	public boolean delPrjDept(int prjID) throws DaoException;
	
	//根据项目id查找项目部门联系信息
	public List<PrjDeptVo> getPrjDeptByPID(int pID) throws DaoException;

}
