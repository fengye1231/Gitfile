package com.neusoft.dms.prp.service;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.prp.domain.Prp;
import com.neusoft.dms.prp.domain.PrpVo;
import com.neusoft.dms.util.ServiceException;

public interface PrpService {
	//添加prp
	public boolean addPrp(Prp prp)throws ServiceException;
	//删除prp
	public boolean deletePrp(String name)throws ServiceException;
	//查找prp
	public PrpVo getPrpByName(String name,String abbr)throws ServiceException;
	//修改prp
	public boolean updatePrp(Prp prp)throws ServiceException;
	//获取prp列表
	//public Page listPrp(Prp prp,int pageNum)throws ServiceException;
	//根据prp名字查询分页
	public Table queryPrpByName(PageQuery query) throws ServiceException;
	
}
