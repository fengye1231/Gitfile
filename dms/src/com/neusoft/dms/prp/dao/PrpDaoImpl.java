package com.neusoft.dms.prp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils.Null;

import com.neusoft.dms.domain.Page;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.prp.domain.Prp;
import com.neusoft.dms.prp.domain.PrpVo;
import com.neusoft.dms.util.Constant;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class PrpDaoImpl implements PrpDao {
	private Connection con=null;
	
	public PrpDaoImpl(Connection con)
	{
		this.con=con;
	}
	@Override
	public void addPrp(Prp prp) throws DaoException {
		PreparedStatement pstmt=null;
		String sql="insert into prp(prpAbbr,prpName,remark) values(?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, prp.getPrpAbbr());
			pstmt.setString(2, prp.getPrpName());
			pstmt.setString(3, prp.getRemark());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
		
	}

	@Override
	public PrpVo getPrpByPrpName(String name, String abbr) throws DaoException {
		PrpVo prpVo=null;
		PreparedStatement pstmt=null;
		String sql="select * from Prp where prpName=? and prpAbbr=?";
		ResultSet result=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, abbr);
			result=pstmt.executeQuery();
			if(result.next())
			{
				prpVo=new PrpVo();
				prpVo.setPrpAbbr(result.getString("prpAbbr"));
				prpVo.setPrpName(result.getString("prpName"));
				prpVo.setRemark(result.getString("remark"));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, result);
		}
		return prpVo;
	}

	@Override
	public void updatePrp(Prp prp) throws DaoException {
		PreparedStatement pstmt=null;
		String sql="update prp set prpAbbr=?,prpName=?,remark=? where prpId=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, prp.getPrpAbbr());
			pstmt.setString(2, prp.getPrpName());
			pstmt.setString(3, prp.getRemark());
			pstmt.setInt(4, prp.getPrpId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt,null);
		}
	}
	@Override
	public void deletePrp(String name) throws DaoException {
		PreparedStatement pstmt=null;
		String sql="delete from prp where prpName=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt,null);
		}
		
	}
	//获取列表
//	public Page listPrp(Prp prp,int pageNum)throws DaoException{
//		Page page=null;
//		
//		page=getPageInfo(prp,pageNum);
//		
//		return page;
//	}
	//获取分页信息
//	public Page getPageInfo(Prp prp,int pageNum)throws DaoException{
//		List<Prp> prplist=null;
//		Page page=null;
//		PreparedStatement pstmt=null;
//		ResultSet resultSet=null;
//		int totalNum=0;
//		
//		String sql= "select * from (select prp.* from prp where prpName like ? and prpAbbr like ?) as prp1 limit 0,5";
//		try {
//			pstmt=con.prepareStatement(sql);
//			if(prp.getPrpName()!=null){
//				pstmt.setString(1, "%"+prp.getPrpName()+"%");
//			}else{
//				pstmt.setString(1, "%%");
//			}
//			if(prp.getPrpAbbr()!=null){
//				pstmt.setString(2, "%"+prp.getPrpAbbr()+"%");
//			}else{
//				pstmt.setString(2, "%%");
//			}
//			resultSet=pstmt.executeQuery();
//			if(resultSet != null){
//				prplist=new ArrayList<Prp>();
//				while(resultSet.next()){
//					Prp p=new Prp();
//					p.setPrpName(resultSet.getString("prpName"));
//					p.setPrpAbbr(resultSet.getString("prpAbbr"));
//					p.setRemark(resultSet.getString("remark"));
//					p.setPrpId(Integer.parseInt(resultSet.getString("prpId")));
//					prplist.add(p);
//				}
//			}
//		} catch (SQLException e) {
//			throw new DaoException(e);
//		}
//		
//		totalNum = prplist.size();
//		int pageSize=2;
//		int totalPage=(totalNum%pageSize)>0?totalNum/pageSize+1:totalNum/pageSize;
//		int pageNumber = Math.min(totalPage, pageNum);
//		int offset = (pageNumber - 1) * pageSize;
//		int end = Math.min(totalNum, offset + pageSize);
//		
//		List<Prp> loads=new ArrayList<Prp>();
//		for(int i = offset;i <end;i++ ){
//			loads.add(prplist.get(i));
//		}
//		page = new Page(loads, totalNum, pageNumber, totalPage, pageSize);
//		
//		return page;
//	}
	
	
	
//	public Page listPrpByName(String prpName,int pageNum,int pageSize)throws DaoException{
//		PreparedStatement pstmt = null,pagePstmt = null;
//		ResultSet rs = null;
//		List<Prp> prpList = new ArrayList<Prp>();
//		Prp prp = null;
//		try{
//			String pageSql = "select count(*) from prp where prpName like ?";
//			pagePstmt = con.prepareStatement(pageSql);
//			pagePstmt.setString(1, "%"+prpName+"%");
//			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
//			if (page.getTotalNum() > 0) {
//				String sql = "select * from prp where prpName like ? limit ?,?";
//				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, "%"+prpName+"%");
//				pstmt.setInt(2, (page.getPageNum() - 1)*page.getPageSize());
//				pstmt.setInt(3, page.getPageSize());
//				rs = pstmt.executeQuery();
//				while(rs.next()){
//					prp = new Prp();
//					prp.setPrpId(rs.getInt("prpId"));
//					prp.setPrpName(rs.getString("prpName"));
//					prp.setPrpAbbr(rs.getString("prpAbbr"));
//					prp.setRemark(rs.getString("remark"));
//					prpList.add(prp);
//			}
//			}
//			page.setList(prpList);
//			return page;
//		}catch(SQLException e){
//			throw new DaoException(e);
//		}finally{
//			DBUtil.close(pstmt, rs);
//		}
//	}
		
	
	public Page listPrp(String prpName,String prpAbbr,int pageNum,int pageSize)throws DaoException{
		PreparedStatement pstmt = null,pagePstmt = null;
		ResultSet rs = null;
		List<Prp> prpList = new ArrayList<Prp>();
		Prp prp = null;
		String sql="select * from (select prp.* from prp where 1=1";
		try{
			String pageSql = "select count(*) from prp where 1=1";
					//"prpName like ? and prpAbbr like ?";
			if(prpName!=null){
				pageSql=pageSql+" and prpName like '%"+prpName+"%'";
				sql=sql+" and prpName like '%"+prpName+"%'";
			}
			if(prpAbbr!=null){
				pageSql=pageSql+" and prpAbbr like '%"+prpAbbr+"%'";
				sql=sql+" and prpAbbr like '%"+prpAbbr+"%'";
			}
			pagePstmt = con.prepareStatement(pageSql);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			if (page.getTotalNum() > 0) {
				//sql = "select * from (select prp.* from prp where prpName like ? and prpAbbr like ?) as prp1 limit ?,?";
				sql=sql+") as prp1 limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, (page.getPageNum() - 1)*page.getPageSize());
				pstmt.setInt(2, page.getPageSize());
				rs = pstmt.executeQuery();
				while(rs.next()){
					prp = new Prp();
					prp.setPrpId(rs.getInt("prpId"));
					prp.setPrpName(rs.getString("prpName"));
					prp.setPrpAbbr(rs.getString("prpAbbr"));
					prp.setRemark(rs.getString("remark"));
					prpList.add(prp);
			}
			}
			page.setList(prpList);
			return page;
		}catch(SQLException e){
			throw new DaoException(e);
			}finally{
				DBUtil.close(pstmt, rs);
			}
	}
	//select count(*) from prp where 1=1 and prpName like '%p%' and prpAbbr like %p%
	/**
	 * 根据数据库里的总条数获取分页信息
	 * @param pstmt
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
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
