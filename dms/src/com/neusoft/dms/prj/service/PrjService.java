package com.neusoft.dms.prj.service;

import java.sql.Date;
import java.util.List;

import com.neusoft.dms.daily.entry.domain.Daily;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Prp;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.prj.domain.Prj;
import com.neusoft.dms.prj.domain.PrjDept;
import com.neusoft.dms.prj.domain.PrjDeptVo;
import com.neusoft.dms.prj.domain.PrjEmp;
import com.neusoft.dms.prj.domain.PrjEmpVo;
import com.neusoft.dms.prj.domain.PrjPrp;
import com.neusoft.dms.prj.domain.PrjPrpVo;
import com.neusoft.dms.prj.domain.PrjVo;
import com.neusoft.dms.util.ServiceException;

public interface PrjService {
	//添加项目
	public boolean addPrj(Prj prj) throws ServiceException;
	
	//删除项目
	public void delPrj(int pid) throws ServiceException;
	
	//修改项目信息
	public boolean updatePrj(Prj prj) throws ServiceException;
	
	//根据条件获取项目信息
	public Table searchProj(PageQuery query) throws ServiceException;
	
	//给项目配置prp
	public boolean addPRP(PrjPrp pp) throws ServiceException;
	
	//给项目配置部门
	public boolean addDept(int prjID,int deptID) throws ServiceException;
	
	//从项目中删除部门
	public boolean delPrjDept(int prjID) throws ServiceException;
	
	//根据项目ID查找项目信息
	public PrjVo getPrjByID(int pID) throws ServiceException;
	
	//根据项目ID查找项目下部门
	public List<PrjDeptVo> getPrjDeptBypID(int pID) throws ServiceException;
	
	//给项目配置人员
	public void addPrjEmp(PrjEmp pe) throws ServiceException;
	
	//删除该项目的所有的prp
	public boolean deltAllPrjPrp(int prjID) throws ServiceException;

	//查找项目下的部门里没有任务的人员
	public Table getNullEmpByDept(PageQuery query) throws ServiceException;

	//获取所有prp阶段
	public Table getAllPrp(PageQuery query) throws ServiceException;
	
	//获得项目人员信息
	public List<PrjEmpVo> getPrjEmp(int prjID) throws ServiceException;

	//获得配置好的prpid
	public List<Integer> getPrpByPrjID(int prjID) throws ServiceException;

	//删除某角色
	public void deltPrjEmp(int prjID,int prp) throws ServiceException;
}
