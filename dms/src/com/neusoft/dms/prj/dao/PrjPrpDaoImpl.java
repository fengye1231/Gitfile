package com.neusoft.dms.prj.dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.Prp;
import com.neusoft.dms.employee.domain.EmployeeVo;
import com.neusoft.dms.prj.domain.PrjPrp;
import com.neusoft.dms.prj.domain.PrjPrpVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class PrjPrpDaoImpl implements PrjPrpDao {
	private Connection con;

	public PrjPrpDaoImpl(Connection con) {
		this.con = con;
	}

	//添加项目与PRP阶段的联系
	public void addPrjPrp(PrjPrp pp) throws DaoException{
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into PRJ_PRP(PRPID,PRJID) values(?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pp.getPrpID());
			pstmt.setInt(2, pp.getPrjID());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}
	
	//根据项目id和prp阶段id删除联系
	public void delprjPrp(int prjID,int prpID) throws DaoException{
		PreparedStatement pstmt = null;
		String sql = "delete from PRJ_PRP where PRJID=? and PRPID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			pstmt.setInt(2, prpID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			DBUtil.close(pstmt, null);
		}
		
	}
	
	//根据项目id和prp阶段id查找
	public PrjPrpVo getPPByprjIDprpID(int prjID,int prpID) throws DaoException{
		PrjPrpVo ppVo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from PRJ_PRP where PRJID=? and PRPID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			pstmt.setInt(2, prpID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ppVo.setPPID(rs.getInt("PPTD"));
				ppVo.setPrjID(rs.getInt("PRJID"));
				ppVo.setPrpID(rs.getInt("PRPID"));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return ppVo;
	}
	
	//根据项目id查询与之关联的PRP阶段信息
	public List<PrjPrpVo> getPPByPrjID(int pid) throws DaoException{
		List<PrjPrpVo> pps = new ArrayList<PrjPrpVo>();
		PrjPrpVo pp = new PrjPrpVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT ppid,prj_prp.prjid,prjname,prj_prp.prpid,prpname FROM prj,prj_prp,prp WHERE prj.prjid=? and prj.prjId=prj_prp.prjId and prp.prpId=prj_prp.prpId";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				pp = new PrjPrpVo();
				pp.setPPID(rs.getInt("PPID"));
				pp.setPrpID(rs.getInt("PRPID"));
				pp.setPrjID(rs.getInt("PRJID"));
				pp.setPrpName(rs.getString("PRPNAME"));
//				pp.setPrpAbbr(rs.getString("PRPABBR"));
				pps.add(pp);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			DBUtil.close(pstmt, rs);
		}
		return pps;
	}

	@Override
	public boolean deltAllPrjPrp(int prjID)throws DaoException {
		boolean isSuccess = false;
		PreparedStatement pstmt = null;
		String sql = "delete from PRJ_PRP where PRJID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			pstmt.executeUpdate();
			isSuccess=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	//查找项目没有配置的prp
	public List<Prp> getNullPrpByPrjID(int prjID)throws DaoException{
		List<Prp> prps = new ArrayList<Prp>();
		Prp prp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PRP WHERE PRPID NOT IN (SELECT PRPID FROM PRJ_PRP WHERE PRJID=？)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prp = new Prp();
				prp.setPrpID(rs.getInt(1));
				prp.setPrpAbbr(rs.getString(2));
				prp.setPrpName(rs.getString(3));
				prp.setRemark(rs.getString(4));
				prps.add(prp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prps;
	}
	
	//查找项目配置的prpid
		public List<Integer> getPrpByPrjID(int prjID)throws DaoException{
			List<Integer> prps = new ArrayList<Integer>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT PRPID FROM PRJ_PRP WHERE PRJID=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, prjID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					prps.add(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return prps;
		}
		
		//获得所有prp
		public Page getAllPrp(int pageNum,int pageSize)throws DaoException{
			List<Prp> prps= new ArrayList<Prp>();
			Prp prp = null;
			PreparedStatement pstmt = null, pagePstmt = null;
			ResultSet rs = null;
			try {
				String pageSql = " SELECT COUNT(*) from prp " ;
				pagePstmt = con.prepareStatement(pageSql);
				Page page = getPageInfo(pagePstmt, pageNum, pageSize);
				if (page.getTotalNum() > 0) {
					String sql = " select * from prp limit ?,? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, (page.getPageNum() - 1) * page.getPageSize());
					pstmt.setInt(2, page.getPageSize());
					rs = pstmt.executeQuery();
					while(rs.next()){
						prp = new Prp();
						prp.setPrpID(rs.getInt(1));
						prp.setPrpName(rs.getString(2));
						prp.setPrpAbbr(rs.getString(3));
						prp.setRemark(rs.getString(4));
						prps.add(prp);
					}
				}
				page.setList(prps);
				return page;
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				DBUtil.close(pstmt, rs);
			}
			
		}
		
		private Page getPageInfo(PreparedStatement pstmt, int pageNum, int pageSize) throws DaoException {
			ResultSet rs = null;
			try {
				rs = pstmt.executeQuery();
				if (rs.next()) {
					pageSize = Math.max(1, pageSize);
					int totalNum = rs.getInt(1);
					int totalPage = (int) Math.ceil((double)totalNum / pageSize);
					pageNum = Math.max(Math.min(totalPage, pageNum), totalNum == 0? 0: 1);
					
					return new Page(null, totalNum, pageNum, totalPage, pageSize);
				}
				throw new DaoException("分页信息获取失败");
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				DBUtil.close(pstmt, rs);
			}
		}
}
