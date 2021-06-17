package com.neusoft.dms.prj.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.prj.domain.Prj;
import com.neusoft.dms.prj.domain.PrjVo;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.DBUtil;

public class PrjDaoImpl implements PrjDao {
	private Connection con;

	public PrjDaoImpl(Connection con) {
		this.con = con;
	}

	//添加项目
	public void addProj(Prj p) throws DaoException{
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into PRJ(PRJCODE,PRJNAME,STARTDATE,ENDDATE,PRJREMARK,PRJSTATUS) values(?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, p.getPrjCode());
			pstmt.setString(2, p.getPrjName());
			pstmt.setDate(3, new java.sql.Date(p.getStartDate().getTime()));
			pstmt.setDate(4, new java.sql.Date(p.getEndDate().getTime()));
			if(p.getRemark()==""){
				pstmt.setString(5, "暂无");
			}else {
				pstmt.setString(5, p.getRemark());
			}
			pstmt.setString(6, p.getStatus());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
		
	}
	
	//根据条件获取项目信息
	public Page getProjByInfo(String code,String name,Date sDate,Date eDate, int pageNum,int pageSize) throws DaoException{
		List<PrjVo> prjs = new ArrayList<PrjVo>();
		PrjVo prjVo = null;
		PreparedStatement pstmt = null, pagePstmt = null;
		ResultSet rs = null;
		StringBuffer sqlBuffer = new StringBuffer();
		try {
			int index = 1;
			String filter = " WHERE TRUE "
					+ (code != null? " AND PRJCODE LIKE ? ": "")
					+ (name != null? " AND PRJNAME LIKE ? ": "")
					+ (sDate != null && eDate != null? " AND ((startDate BETWEEN ? AND ?) OR (? BETWEEN startDate AND endDate)) ": "")	//有开始结束					
					+ (sDate != null && eDate == null? " AND endDate >= ? ": "")	//只有开始
					+ (sDate == null && eDate != null? " AND startDate <= ? ": "");	//只有结束
			
			String pageSql = "SELECT COUNT(*) FROM prj" + filter;
			pagePstmt = con.prepareStatement(pageSql);
			
			if (code != null) pagePstmt.setString(index++, "%" + code + "%");
			if (name != null) pagePstmt.setString(index++, "%" + name + "%");
			if (sDate != null) pagePstmt.setDate(index++, new java.sql.Date(sDate.getTime()));
			if (eDate != null) pagePstmt.setDate(index++, new java.sql.Date(eDate.getTime()));
			if (sDate != null && eDate != null) pagePstmt.setDate(index++, new java.sql.Date(sDate.getTime()));
			
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			
			if (page.getTotalNum() > 0) {
				String sql = "select * from PRJ " + filter + " LIMIT ?, ? ";
				pstmt = con.prepareStatement(sql);
				
				index = 1;
				if (code != null) pstmt.setString(index++, "%" + code + "%");
				if (name != null) pstmt.setString(index++, "%" + name + "%");
				if (sDate != null) pstmt.setDate(index++, new java.sql.Date(sDate.getTime()));
				if (eDate != null) pstmt.setDate(index++, new java.sql.Date(eDate.getTime()));
				if (sDate != null && eDate != null) pstmt.setDate(index++, new java.sql.Date(sDate.getTime()));
				pstmt.setInt(index++, (page.getPageNum() - 1) * page.getPageSize());
				pstmt.setInt(index++, page.getPageSize());

				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					prjVo = new PrjVo();
					prjVo.setPrjID(rs.getInt("PRJID"));
					prjVo.setPrjCode(rs.getString("PRJCODE"));
					prjVo.setPrjName(rs.getString("PRJNAME"));
					prjVo.setStartDate(new Date(rs.getDate("STARTDATE").getTime()));
					prjVo.setEndDate(new Date(rs.getDate("ENDDATE").getTime()));
					prjVo.setRemark(rs.getString("PRJREMARK"));
					prjVo.setState(rs.getString("PRJSTATUS"));
					prjs.add(prjVo);
				}
			}
			page.setList(prjs);
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

	//删除项目
	public void deltProj(int prjID) throws DaoException{
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from PRJ where PRJID=?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if("23000".equals(e.getSQLState())){
				throw new DaoException("该项目已被配置不能删除", e);
			}
			throw new DaoException(e);
		} finally {
			DBUtil.close(pstmt, null);
		}
	}
	
	//修改项目信息
	public boolean updateProj(Prj p) throws DaoException{
		PreparedStatement pstmt = null;
		boolean isSuccess = false;
		try {
			String sql = "update PRJ set PRJCODE=?,PRJNAME=?,STARTDATE=?,ENDDATE=?,PRJREMARK=?,PRJSTATUS=? where PRJID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,p.getPrjCode());
			pstmt.setString(2,p.getPrjName());
			pstmt.setDate(3, new java.sql.Date(p.getStartDate().getTime()));
			pstmt.setDate(4, new java.sql.Date(p.getEndDate().getTime()));
			pstmt.setString(5, p.getRemark());
			pstmt.setString(6, p.getStatus());
			pstmt.setInt(7, p.getPrjID());
			pstmt.executeUpdate();	
			System.out.println("execute update");
			isSuccess = true;
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			DBUtil.close(pstmt, null);
		}	
		return isSuccess;
		
	}
	
	//根据项目id获取项目信息
	public PrjVo getPrjVoByPrjID(int prjID) throws DaoException{
		System.out.println("pdao id:"+prjID);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PrjVo prjVo = new PrjVo();
		String sql = "select * from PRJ where PRJID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prjID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				prjVo = getPrjVo(rs);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return prjVo;
	}
	
	//从数据库获取项目信息
	public PrjVo getPrjVo(ResultSet rs){
		PrjVo prjVo = new PrjVo();
		try {
			prjVo.setPrjID(rs.getInt("PRJID"));
			prjVo.setPrjCode(rs.getString("PRJCODE"));
			prjVo.setPrjName(rs.getString("PRJNAME"));
			prjVo.setStartDate(rs.getDate("STARTDATE"));
			prjVo.setEndDate(rs.getDate("ENDDATE"));
			prjVo.setRemark(rs.getString("PRJREMARK"));
			prjVo.setState(rs.getString("PRJSTATUS"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prjVo;
	}
	
	//根据项目编码获取项目信息
	public PrjVo getPrjVoByPrjCode(String prjCode) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PrjVo prjVo = null;
		String sql = "select * from PRJ where PRJCode =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prjCode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				prjVo = getPrjVo(rs);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return prjVo;
	}
	
	//根据项目名称获取项目信息
	public PrjVo getPrjVoByPrjName(String prjName) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PrjVo prjVo = null;
		String sql = "select * from PRJ where PRJNAME=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prjName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				prjVo = getPrjVo(rs);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return prjVo;
	}
	
}
