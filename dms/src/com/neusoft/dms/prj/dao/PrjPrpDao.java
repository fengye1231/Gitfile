package com.neusoft.dms.prj.dao;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.Prp;
import com.neusoft.dms.prj.domain.PrjPrp;
import com.neusoft.dms.prj.domain.PrjPrpVo;
import com.neusoft.dms.util.DaoException;

public interface PrjPrpDao {
	//添加项目与PRP阶段的联系
	public void addPrjPrp(PrjPrp pp) throws DaoException;
	
	//删除联系根据项目id和prp阶段id
	public void delprjPrp(int prjID,int prpID) throws DaoException;
	
	//根据项目id和prp阶段id查找
	public PrjPrpVo getPPByprjIDprpID(int prjID,int prpID) throws DaoException;
	
	//根据项目id查询与之关联的PRP阶段id
	public List<PrjPrpVo> getPPByPrjID(int pid) throws DaoException;

	//删除项目所有prp
	public boolean deltAllPrjPrp(int prjID)throws DaoException;


	//查找项目配置的prp
	public List<Integer> getPrpByPrjID(int prjID)throws DaoException;

	//查找所有prp
	public Page getAllPrp(int pagenum,int pageSize)throws DaoException;
}
