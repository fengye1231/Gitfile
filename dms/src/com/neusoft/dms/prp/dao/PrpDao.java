package com.neusoft.dms.prp.dao;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.prp.domain.Prp;
import com.neusoft.dms.prp.domain.PrpVo;
import com.neusoft.dms.util.DaoException;

public interface PrpDao {
	//添加prp
	public void addPrp(Prp prp)throws DaoException;
	//根据prpName和prpAbbr查找prp
	public PrpVo getPrpByPrpName(String name,String abbr)throws DaoException;
	//更新prp
	public void updatePrp(Prp prp)throws DaoException;
	//删除prp
	public void deletePrp(String name)throws DaoException;
	//prp列表
	//public Page listPrp(Prp prp,int pageNum)throws DaoException;
	//prp按名字查询分页
	//public Page listPrpByName(String prpName,int pageNum,int pageSize)throws DaoException;
	
	
	public Page listPrp(String prpName,String prpAbbr,int pageNum,int pageSize)throws DaoException;
}
