package com.neusoft.dms.prp.service;

import java.sql.Connection;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.menu.dao.MenuDao;
import com.neusoft.dms.menu.dao.MenuDaoImpl;
import com.neusoft.dms.prp.dao.PrpDao;
import com.neusoft.dms.prp.dao.PrpDaoImpl;
import com.neusoft.dms.prp.domain.Prp;
import com.neusoft.dms.prp.domain.PrpVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

public class PrpServiceImpl implements PrpService {
	//创建单例实例
	private static PrpServiceImpl instance=new PrpServiceImpl();
	public static PrpServiceImpl getInstance()
	{
		return instance;
	}
	private PrpServiceImpl(){
		
	}

	@Override
	public boolean addPrp(Prp prp) throws ServiceException {
		boolean isSuccess=false;
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			PrpDao prpDao=new PrpDaoImpl(con);
			PrpVo prpVo=prpDao.getPrpByPrpName(prp.getPrpName(), prp.getPrpAbbr());
			if(prpVo==null){
				prpDao.addPrp(prp);
				isSuccess=true;
			}
		} catch (DaoException e) {
			throw new ServiceException("添加PRP错误",e);
		}catch (Exception e){
			throw new ServiceException("添加PRP错误",e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("添加部门错误",e);
			}
		}
		return isSuccess;
	}

	@Override
	public boolean deletePrp(String name) throws ServiceException {
		boolean isSuccess=false;
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			PrpDao prpDao=new PrpDaoImpl(con);
			prpDao.deletePrp(name);
			isSuccess=true;
		} catch (DaoException e) {
			throw new ServiceException("删除PRP错误",e);
		}catch (Exception e){
			throw new ServiceException("删除PRP错误",e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除PRP错误",e);
			}
		}
		return isSuccess;
	}

	@Override
	public PrpVo getPrpByName(String name, String abbr) throws ServiceException {
		PrpVo prpVo=new PrpVo();
		try {
			Connection con=DBUtil.getConnection();
			PrpDao prpDao=new PrpDaoImpl(con);
			prpVo=prpDao.getPrpByPrpName(name, abbr);
		} catch (DaoException e) {
			throw new ServiceException("查询PRP信息错误",e);
		}
		return prpVo;
	}

	@Override
	public boolean updatePrp(Prp prp) throws ServiceException {
		boolean isSuccess=false;
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			PrpDao prpDao=new PrpDaoImpl(con);
			prpDao.updatePrp(prp);
			isSuccess=true;
		} catch (DaoException e) {
			throw new ServiceException("修改PRP错误",e);
		}catch(Exception e){
			throw new ServiceException("修改PRP错误",e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("修改PRP错误",e);
			}
		}
		return isSuccess;
	}
	@Override
//	public Page listPrp(Prp prp, int pageNum) throws ServiceException {
//		Page page=null;
//		Connection con=null;
//		try {
//			con=DBUtil.getConnection();
//			PrpDao prpDao=new PrpDaoImpl(con);
//			page=prpDao.listPrp(prp, pageNum);
//		} catch (DaoException e) {
//			throw new ServiceException("查询PRP列表错误", e);
//		} catch (Exception e) {
//			throw new ServiceException("查询PRP列表错误", e);
//		} finally {
//			try {
//				DBUtil.close(con);
//			} catch (DaoException e) {
//				throw new ServiceException("查询PRP列表错误", e);
//			}
//		}
//		return page;
//	}
	
	public Table queryPrpByName(PageQuery query) throws ServiceException{
		Connection con = null;
		try{
			Table result = null;
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			String prpName = (String)query.getParam("prpName");
			String prpAbbr=(String)query.getParam("prpAbbr");
			con = DBUtil.getConnection();
			PrpDao prpDao = new PrpDaoImpl(con);
			Page page = prpDao.listPrp(prpName, prpAbbr, pageNum, pageSize);
		
			result = new Table(page);
			return result;			
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}


}
