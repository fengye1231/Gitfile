package com.neusoft.dms.prj.service;

import java.sql.Connection;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.domain.Dept;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Prp;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.prj.dao.DeptEmpDao;
import com.neusoft.dms.prj.dao.DeptEmpDaoImpl;
import com.neusoft.dms.prj.dao.PrjDao;
import com.neusoft.dms.prj.dao.PrjDaoImpl;
import com.neusoft.dms.prj.dao.PrjDeptDao;
import com.neusoft.dms.prj.dao.PrjDeptDaoImpl;
import com.neusoft.dms.prj.dao.PrjEmpDao;
import com.neusoft.dms.prj.dao.PrjEmpDaoImpl;
import com.neusoft.dms.prj.dao.PrjPrpDao;
import com.neusoft.dms.prj.dao.PrjPrpDaoImpl;
import com.neusoft.dms.prj.domain.Prj;
import com.neusoft.dms.prj.domain.PrjDept;
import com.neusoft.dms.prj.domain.PrjDeptVo;
import com.neusoft.dms.prj.domain.PrjEmp;
import com.neusoft.dms.prj.domain.PrjEmpVo;
import com.neusoft.dms.prj.domain.PrjPrp;
import com.neusoft.dms.prj.domain.PrjPrpVo;
import com.neusoft.dms.prj.domain.PrjVo;
import com.neusoft.dms.proj.dao.ProjectLoadDao;
import com.neusoft.dms.proj.dao.ProjectLoadDaoImpl;
import com.neusoft.dms.prp.dao.PrpDao;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

public class PrjServiceImpl implements PrjService {
	
	private static PrjServiceImpl instance = new PrjServiceImpl();

	/**
	 * 单例模式取得实例
	 * 
	 * @return 实例对象
	 */
	public static PrjServiceImpl getInstance() {
		return instance;
	}

	private PrjServiceImpl() {

	}
	
	//添加项目
	public boolean addPrj(Prj prj) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;;
		try {
			con = DBUtil.getConnection();
			PrjDao pDao = new PrjDaoImpl(con);
			//查看项目编码是否存在
			PrjVo prjVo = pDao.getPrjVoByPrjCode(prj.getPrjCode());
			if(prjVo==null){
				//查看项目名称是否存在
				prjVo = pDao.getPrjVoByPrjName(prj.getPrjName());
				if(prjVo==null){
					pDao.addProj(prj);
					isSuccess = true;
				}
			}
			return isSuccess;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("添加项目失败", e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
	}
	
	
	
	//删除项目
	public void delPrj(int pid) throws ServiceException{
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjDao pDao = new PrjDaoImpl(con);
			pDao.deltProj(pid);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
	}
	
	
	//修改项目信息
	public boolean updatePrj(Prj prj) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjDao pDao= new PrjDaoImpl(con);
			isSuccess=pDao.updateProj(prj);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return isSuccess;
	}
	
	//给项目添加prp
	public boolean addPRP(PrjPrp pp) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		PrjPrpVo ppVo = null;
		try {
			con = DBUtil.getConnection();
			PrjPrpDao ppDao = new PrjPrpDaoImpl(con);
			ppVo = ppDao.getPPByprjIDprpID(pp.getPrjID(), pp.getPrpID());
			if(ppVo==null){
				ppDao.addPrjPrp(pp);
			}
		} catch (DaoException e) {
			throw new ServiceException("配置prp失败",e);
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return isSuccess;
	}
	
	//给项目配置部门
	public boolean addDept(int prjID,int deptID) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjDeptDao pdDao= new PrjDeptDaoImpl(con);
			pdDao.addPrjDept(prjID,deptID);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return isSuccess;
	}
	
	//从项目中删除某部门
	public boolean delPrjDept(int prjID) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjDeptDao pdDao = new PrjDeptDaoImpl(con);
			isSuccess = pdDao.delPrjDept(prjID);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return isSuccess;
	}
	
	//根据项目ID查找项目下部门
	public List<PrjDeptVo> getPrjDeptBypID(int pID) throws ServiceException{
		List<PrjDeptVo> pds = new ArrayList<PrjDeptVo>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjDeptDao pdDao = new PrjDeptDaoImpl(con);
			pds = pdDao.getPrjDeptByPID(pID);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return pds;
	}
	
	//给项目配置人员
	public void addPrjEmp(PrjEmp pe) throws ServiceException{
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjEmpDao peDao = new PrjEmpDaoImpl(con);
			peDao.addPrjEmp(pe);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
	}

	//根据项目id获取项目信息
	public PrjVo getPrjByID(int pID) throws ServiceException {
		PrjVo pVo = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjDao pDao = new PrjDaoImpl(con);
			pVo = pDao.getPrjVoByPrjID(pID);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return pVo;
	}

	//根据项目id查找prp
	public List<PrjPrpVo> getPrjPrpByID(int pID) throws ServiceException {
		List<PrjPrpVo> prjPrps = new ArrayList<PrjPrpVo>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjPrpDao ppDao = new PrjPrpDaoImpl(con);
			System.out.println("connect ok");
			prjPrps = ppDao.getPPByPrjID(pID);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		
		return prjPrps;
	}

	//更新项目的prp
	public boolean updatePrjPrp(int prjID, int prpID) throws ServiceException{
		boolean isSuccess = false;
		PrjPrp pp = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjPrpDao ppDao = new PrjPrpDaoImpl(con);
			pp = ppDao.getPPByprjIDprpID(prjID, prpID);
			if(pp==null){
				pp = new PrjPrp();
				pp.setPrjID(prjID);
				pp.setPrpID(prpID);
				ppDao.addPrjPrp(pp);
				isSuccess = true;
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return isSuccess;
	}

	//删除项目配置的所有prp
	public boolean deltAllPrjPrp(int prjID) throws ServiceException{
		boolean isSuccess = false; 
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjPrpDao ppDao = new PrjPrpDaoImpl(con);
			isSuccess = ppDao.deltAllPrjPrp(prjID);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return false;
	}

	@Override
	public Table searchProj(PageQuery query) throws ServiceException {
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			String projCode = (String) query.getParam("projCode");
			String projName = (String) query.getParam("projName");
			Date sDate = (Date) query.getParam("sDate");
			Date eDate = (Date) query.getParam("eDate");

			con = DBUtil.getConnection();
			PrjDao pDao = new PrjDaoImpl(con);
			
			Page projPage = pDao.getProjByInfo(projCode, projName, sDate, eDate, pageNum, pageSize);
			Table result = new Table(projPage);
			return result;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
			}
		}

	//查找项目配置人员信息
	public List<PrjEmpVo> getPrjEmp(int prjID) throws ServiceException{
		List<PrjEmpVo> pes = new ArrayList<PrjEmpVo>();
		Connection  con = null;
		try {
			con = DBUtil.getConnection();
			PrjEmpDao peDao = new PrjEmpDaoImpl(con);
			pes = peDao.getPrjEmpByPrjID(prjID);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return pes;
	}
	
	//查找项目配置prp
	public List<Integer> getPrpByPrjID(int prjID) throws ServiceException{
		List<Integer> prps = new ArrayList<Integer>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjPrpDao ppDao = new PrjPrpDaoImpl(con);
			prps = ppDao.getPrpByPrjID(prjID);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
		return prps;
	}
	
	//获取所有prp阶段
	public Table getAllPrp(PageQuery query) throws ServiceException{
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			con = DBUtil.getConnection();
			PrjPrpDao ppDao = new PrjPrpDaoImpl(con);
			
			Page prpPage = ppDao.getAllPrp(pageNum, pageSize);
			Table result = new Table(prpPage);
			return result;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
			}
	}

	//获取部门下没分配的人员
	public Table getNullEmpByDept(PageQuery query) throws ServiceException{
		Connection con = null;
		try {
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			int deptID = (Integer) query.getParam("deptID");
			con = DBUtil.getConnection();
			DeptEmpDao deDao = new DeptEmpDaoImpl(con);
			
			Page dePage = deDao.getNullEmpByDept(deptID,pageNum, pageSize);
			Table result = new Table(dePage);
			return result;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
	}
	
	//删除某角色
	public void deltPrjEmp(int prjID,int prID) throws ServiceException{
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			PrjEmpDao peDao = new PrjEmpDaoImpl(con);
			peDao.delPrjEmp(prjID,prID);
		} catch (DaoException e) {
			e.printStackTrace();
		}finally {
			try { DBUtil.close(con); } 
			catch (DaoException e) { e.printStackTrace();}
		}
	}

	
}