package com.neusoft.dms.prj.dao;

import java.util.Date;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.prj.domain.Prj;
import com.neusoft.dms.prj.domain.PrjVo;
import com.neusoft.dms.util.DaoException;

public interface PrjDao {
	//添加项目
	public void addProj(Prj p) throws DaoException;
	
	//根据条件获取项目信息
	public Page getProjByInfo(String code,String name,Date sDate,Date eDate, int pageNum, int pageSize) throws DaoException;
	
	//删除项目
	public void deltProj(int projID) throws DaoException;
	
	//修改项目信息
	public boolean updateProj(Prj proj) throws DaoException;
	
	//根据项目id获取项目信息
	public PrjVo getPrjVoByPrjID(int prjID) throws DaoException;
	
	//根据项目编码获取项目信息
	public PrjVo getPrjVoByPrjCode(String prjCode) throws DaoException;
	
	//根据项目名称获取项目信息
	public PrjVo getPrjVoByPrjName(String prjName) throws DaoException;
}
