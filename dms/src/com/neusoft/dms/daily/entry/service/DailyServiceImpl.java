package com.neusoft.dms.daily.entry.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.neusoft.dms.daily.entry.dao.DailyDao;
import com.neusoft.dms.daily.entry.dao.DailyDaoImpl;
import com.neusoft.dms.daily.entry.domain.Daily;
import com.neusoft.dms.daily.entry.domain.DailyCon;
import com.neusoft.dms.daily.entry.domain.DailyVo;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.prj.dao.PrjEmpDao;
import com.neusoft.dms.prj.dao.PrjEmpDaoImpl;
import com.neusoft.dms.prj.dao.PrjPrpDao;
import com.neusoft.dms.prj.dao.PrjPrpDaoImpl;
import com.neusoft.dms.prj.domain.PrjEmpVo;
import com.neusoft.dms.prj.domain.PrjPrpVo;
import com.neusoft.dms.proj.dao.ProjectLoadDao;
import com.neusoft.dms.proj.dao.ProjectLoadDaoImpl;
import com.neusoft.dms.proj.domain.ProjectVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

public class DailyServiceImpl implements DailyService{
	private static DailyServiceImpl instance = new DailyServiceImpl();

	/**
	 * 单例模式取得实例
	 * 
	 * @return 实例对象
	 */
	public static DailyServiceImpl getInstance() {
		return instance;
	}
	private DailyServiceImpl() {
	}
	//添加日报
	public boolean addDaily(Daily daily) throws ServiceException {
		boolean isSuccess = false;
		Connection con = null;

		// 查看日报编号是否唯一
		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			DailyVo Daily = null;
			Daily = dailyDao.checkAddDaily(daily);
			// 日报编号不存在，添加日报
			if (Daily == null) {
				dailyDao.addDaily(daily);
				isSuccess = true;
			}else{
				isSuccess = false;
			}
		} catch (DaoException e) {
			throw new ServiceException("添加日报错误", e);
		} catch (Exception e) {
			throw new ServiceException("添加日报错误", e);
		} finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("添加日报错误", e);
			}
		}
		return isSuccess;
	}
	//删除日报
	public boolean delDaily(int dailyId) throws ServiceException {
		boolean isSuccess = false;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			dailyDao.delDaily(dailyId);
			isSuccess = true;
		} catch (DaoException e) {
			throw new ServiceException("删除日报错误", e);
		} catch (Exception e) {
			throw new ServiceException("删除日报错误", e);
		} finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除日报错误", e);
			}
		}
		return isSuccess;
	}

	// 根据ID获取日报信息
	public DailyVo getDailyById(int dailyId) throws ServiceException {
		DailyVo daily = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			daily = dailyDao.getDailyById(dailyId);
		} catch (DaoException e) {
			throw new ServiceException("查询日报信息错误", e);
		} catch (Exception e) {
			throw new ServiceException("查询日报信息错误", e);
		} finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除日报错误", e);
			}
		}
		return daily;
	}

	//列出日报信息
	public Page listDaily(Daily daily,Date sdate,Date edate, int pageNum,int PageSize) throws ServiceException {
		Page page = null;
		Connection con = null;

		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			page = dailyDao.listDaily(daily,sdate,edate, pageNum, PageSize);
		} catch (DaoException e) {
			throw new ServiceException("查询部门列表错误", e);
		} catch (Exception e) {
			throw new ServiceException("查询部门列表错误", e);
		} finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("查询部门列表错误", e);
			}
		}
		return page;
	}
	public Page listDaily(DailyCon dailyCon) throws ServiceException {
		Page page = null;
		Connection con = null;

		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			page = dailyDao.listDaily(dailyCon);
		} catch (DaoException e) {
			throw new ServiceException("查询部门列表错误", e);
		} catch (Exception e) {
			throw new ServiceException("查询部门列表错误", e);
		} finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("查询部门列表错误", e);
			}
		}
		return page;
	}
	//列出日报信息
	public Page listCheckDaily(DailyCon dailyCon) throws ServiceException {
		Page page = null;
		Connection con = null;

		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
//			page = dailyDao.listCheckDaily(daily,sdate,edate, pageNum,PageSize);
		} catch (DaoException e) {
			throw new ServiceException("查询部门列表错误", e);
		} catch (Exception e) {
			throw new ServiceException("查询部门列表错误", e);
		} finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("查询部门列表错误", e);
			}
		}
		return page;
	}
	public Page listCheckDaily(Daily daily,Date sdate,Date edate, int pageNum,int PageSize) throws ServiceException {
		Page page = null;
		Connection con = null;

		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			page = dailyDao.listCheckDaily(daily,sdate,edate, pageNum,PageSize);
		} catch (DaoException e) {
			throw new ServiceException("查询部门列表错误", e);
		} catch (Exception e) {
			throw new ServiceException("查询部门列表错误", e);
		} finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("查询部门列表错误", e);
			}
		}
		return page;
	}

	//修改日报信息
	public boolean updateDaily(Daily daily) throws ServiceException {
		boolean isSuccess = false;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			dailyDao.updateDaily(daily);
			isSuccess = true;
		} catch (DaoException e) {
			throw new ServiceException("修改日报错误", e);
		} catch (Exception e) {
			throw new ServiceException("修改日报错误", e);
		} finally {

			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除日报错误", e);
			}
		}
		return isSuccess;
	}
	//审核日报信息
	public boolean checkDaily(Daily daily) throws ServiceException {
		boolean isSuccess = false;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			dailyDao.checkDaily(daily);
			isSuccess = true;
		} catch (DaoException e) {
			throw new ServiceException("审核日报错误", e);
		} catch (Exception e) {
			throw new ServiceException("审核日报错误", e);
		} finally {

			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除日报错误", e);
			}
		}
		return isSuccess;
	}
	@Override
	public boolean failDaily(Daily daily) throws ServiceException {
		boolean isSuccess = false;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			dailyDao.failDaily(daily);
			isSuccess = true;
		} catch (DaoException e) {
			throw new ServiceException("审核日报错误", e);
		} catch (Exception e) {
			throw new ServiceException("审核日报错误", e);
		} finally {
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除日报错误", e);
			}
		}
		return isSuccess;
	}
	@Override
	public boolean passDaily(Daily daily) throws ServiceException {
		boolean isSuccess = false;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			dailyDao.passDaily(daily);
			isSuccess = true;
		} catch (DaoException e) {
			throw new ServiceException("审核日报错误", e);
		} catch (Exception e) {
			throw new ServiceException("审核日报错误", e);
		} finally {

			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除日报错误", e);
			}
		}
		return isSuccess;
	}
	@Override
	public List<PrjEmpVo> getPrjsEmp(int empID) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjEmpDao loadDao = new PrjEmpDaoImpl(con);
			
			List<PrjEmpVo> prjEmps = loadDao.getAllPrjByEmpID(empID);

			return prjEmps;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	@Override
	public PrjEmpVo getPrjEmp(int empID) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjEmpDao loadDao = new PrjEmpDaoImpl(con);
			PrjEmpVo prjEmps = loadDao.getPrjByEmpID(empID);

			return prjEmps;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	@Override
	public List<DailyVo> getDailyByPrj(int prjId) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			DailyDao dailyDao = new DailyDaoImpl(con);
			
			List<DailyVo> dailys = dailyDao. getDailyByPrj(prjId);

			return dailys;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
	@Override
	public List<PrjPrpVo> getPrp(int pId) throws ServiceException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjPrpDao loadDao = new PrjPrpDaoImpl(con);
			
			List<PrjPrpVo> prjPrps = loadDao.getPPByPrjID(pId);

			return prjPrps;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace(); }
		}
	}
}
