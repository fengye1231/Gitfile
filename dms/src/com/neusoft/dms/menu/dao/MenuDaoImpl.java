package com.neusoft.dms.menu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.domain.MenuItem;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.menu.domain.MenuVo;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;

public class MenuDaoImpl implements MenuDao {

	private Connection con;
	public MenuDaoImpl(Connection con){
		this.con = con;
	}
	
	public MenuDaoImpl(){
	}
	
    @Override
    public ArrayList<MenuItem> getItems() {
        ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
        
        // 避免误删
        menuItems.add(new MenuItem(0, "日报设定"));
        menuItems.add(new MenuItem(1, "PRP阶段设定", "daily/setting/prp", 0));
        menuItems.add(new MenuItem(2, "部门设定", "daily/setting/dept", 0));
        menuItems.add(new MenuItem(3, "项目设定", "daily/setting/proj", 0));

        menuItems.add(new MenuItem(4, "日报登录"));
        menuItems.add(new MenuItem(5, "日报管理", "daily/entry/manage", 4));
        menuItems.add(new MenuItem(6, "日报审核", "daily/entry/check", 4));

        menuItems.add(new MenuItem(7, "个人日报查询"));
        menuItems.add(new MenuItem(8, "按日期查询", "daily/query/personal/date", 7));
        menuItems.add(new MenuItem(9, "按项目查询", "daily/query/personal/proj", 7));
        menuItems.add(new MenuItem(10, "未写日报查询", "daily/query/personal/unsubmit", 7));
        menuItems.add(new MenuItem(11, "个人周报导出", "daily/query/personal/export", 7));

        menuItems.add(new MenuItem(12, "部门工作量查询"));
        menuItems.add(new MenuItem(13, "按项目查询", "daily/query/dept/proj", 12));
        menuItems.add(new MenuItem(14, "项目投入汇总", "daily/query/dept/summary", 12));
        menuItems.add(new MenuItem(15, "部门工作量导出", "daily/query/dept/export", 12));
        menuItems.add(new MenuItem(16, "部门未写日报查询", "daily/query/dept/unsubmit", 12));
        menuItems.add(new MenuItem(17, "部门未审核日报查询", "daily/query/dept/uncheck", 12));

        menuItems.add(new MenuItem(18, "项目工作量查询"));
        menuItems.add(new MenuItem(19, "按里程碑查询", "daily/query/proj/milestone", 18));
        menuItems.add(new MenuItem(20, "项目投入汇总", "daily/query/proj/summary", 18));

        menuItems.add(new MenuItem(21, "用户管理"));
        menuItems.add(new MenuItem(22, "个人信息管理", "info/user", 21));
        menuItems.add(new MenuItem(23, "员工信息管理", "info/employee", 21));

        menuItems.add(new MenuItem(24, "系统管理"));
        menuItems.add(new MenuItem(25, "角色管理", "admin/role", 24));
        menuItems.add(new MenuItem(26, "权限管理", "admin/permission", 24));
        menuItems.add(new MenuItem(27, "菜单管理", "admin/menu", 24));
        menuItems.add(new MenuItem(28, "按部门查询", "daily/query/proj/dept", 18));


        return menuItems;

    }
    
    
	@Override
	public void addMenu(Menu menu) throws DaoException {
		PreparedStatement pstmt = null;
		try{
			if(menu.getParentMenuId()!=0){
				String sql = "insert into menu (menuCode,menuName,menuPath,parentMenuid) values(?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,menu.getMenuCode());
				pstmt.setString(2,menu.getMenuName());
				pstmt.setString(3,menu.getMenuPath());
				System.out.println("parentId:"+menu.getParentMenuId());
				pstmt.setInt(4, menu.getParentMenuId());
			
				pstmt.executeUpdate();
			}else{
				String sql = "insert into menu (menuCode,menuName,menuPath) values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,menu.getMenuCode());
				pstmt.setString(2,menu.getMenuName());
				pstmt.setString(3,menu.getMenuPath());
				
				pstmt.executeUpdate();

			}
			
			
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
		
	}

	@Override
	public void delMenu(String menuName) throws DaoException {
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from menu where menuName=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menuName);
			pstmt.execute();
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
	}
	
	
	@Override
	public Menu getMenuByName(String menuName) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Menu menu = null;
		try{
			String sql = "select * from menu where menuName =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menuName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				menu = new Menu();
				menu.setMenuId(rs.getInt("menuId"));
				menu.setMenuCode(rs.getString("menuCode"));
				menu.setMenuName(rs.getString("menuName"));
				menu.setMenuPath(rs.getString("menuPath"));
				menu.setParentMenuId(rs.getInt("parentMenuId"));
			}
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return menu;
	}
	
	public Menu getMenuByCode(String menuCode) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Menu menu = null;
		try{
			String sql = "select * from menu where menuCode =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menuCode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				menu = new Menu();
				menu.setMenuId(rs.getInt("menuId"));
				menu.setMenuCode(rs.getString("menuCode"));
				menu.setMenuName(rs.getString("menuName"));
				menu.setMenuPath(rs.getString("menuPath"));
				menu.setParentMenuId(rs.getInt("parentMenuId"));
			}

		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return menu;
	}

	public Menu getMenuById(int menuId) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Menu menu = null;
		try{
			String sql = "select * from menu where menuId =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, menuId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				menu = new Menu();
				menu.setMenuId(rs.getInt("menuId"));
				menu.setMenuCode(rs.getString("menuCode"));
				menu.setMenuName(rs.getString("menuName"));
				menu.setMenuPath(rs.getString("menuPath"));
				menu.setParentMenuId(rs.getInt("parentMenuId"));
			}

		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return menu;
	}



	@Override
	public void updateMenu(Menu menu) throws DaoException {
		PreparedStatement pstmt = null;
		try{
			if(menu.getParentMenuId()!=0){
				String sql = "update menu set menuCode =?,menuName =?,menuPath =?,parentMenuId =? where menuId =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, menu.getMenuCode());
				pstmt.setString(2, menu.getMenuName());
				pstmt.setString(3, menu.getMenuPath());
				pstmt.setInt(4,menu.getParentMenuId());
				pstmt.setInt(5, menu.getMenuId());
				pstmt.executeUpdate();
			}else{
				String sql = "update menu set menuCode =?,menuName =?,menuPath =? where menuId =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, menu.getMenuCode());
				pstmt.setString(2, menu.getMenuName());
				pstmt.setString(3, menu.getMenuPath());
				pstmt.setInt(4, menu.getMenuId());
				pstmt.executeUpdate();
			}
			
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, null);
		}
		
	}
	
	
	public List<Menu> listAllMenu() throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Menu> menuList = new ArrayList<Menu>();
		Menu menu = null;
		try{
			String sql = "select * from menu ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){	
				menu = new Menu();
				menu.setMenuId(rs.getInt("menuId"));
				menu.setMenuCode(rs.getString("menuCode"));
				menu.setMenuName(rs.getString("menuName"));
				menu.setMenuPath(rs.getString("menuPath"));
				menu.setParentMenuId(rs.getInt("parentMenuId"));
				menuList.add(menu);
			}
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return menuList;
	}
	
	public List<Menu> listParentMenu() throws DaoException{

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Menu> parentList = new ArrayList<Menu>();
		Menu menu = null;
		try{
			String sql = "select * from menu where parentMenuId is null";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				menu = new Menu();
				menu.setMenuId(rs.getInt("menuId"));
				menu.setMenuCode(rs.getString("menuCode"));
				menu.setMenuName(rs.getString("menuName"));
				menu.setMenuPath(rs.getString("menuPath"));
				menu.setParentMenuId(rs.getInt("parentMenuId"));
				parentList.add(menu);
			}
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
		return parentList;
	}

	public Page listMenuByAll(String menuName,int parentMenuId,int pageNum,int pageSize) throws DaoException{
		PreparedStatement pstmt = null,pagePstmt = null;
		ResultSet rs = null;
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		MenuVo menuVo = null;
		System.out.println("menu:"+menuName);
		//System.out.println("menu:"+parentMenuId);
		try{
			String sql = "select * from menu where 1=1";
			String pageSql = "select count(*) from menu where 1 = 1";
			if(menuName!=null){
				sql = sql + " and menuName like '%"+menuName+"%'";
				pageSql = pageSql +" and menuName like '%"+menuName+"%'";
			}
			if(parentMenuId!=0){
				sql = sql + " and parentMenuId ="+ parentMenuId;
				pageSql = pageSql +" and parentMenuId ="+ parentMenuId;
			}
			pagePstmt = con.prepareStatement(pageSql);
			Page page = getPageInfo(pagePstmt, pageNum, pageSize);
			if (page.getTotalNum() > 0) {
				sql=sql+" limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, (page.getPageNum() - 1)*page.getPageSize());
				pstmt.setInt(2, page.getPageSize());
				rs = pstmt.executeQuery();
				while(rs.next()){
					menuVo = new MenuVo();
					menuVo.setMenuId(rs.getInt("menuId"));
					menuVo.setMenuCode(rs.getString("menuCode"));
					menuVo.setMenuName(rs.getString("menuName"));
					menuVo.setMenuPath(rs.getString("menuPath"));
					if(rs.getInt("parentMenuId")==0){
						menuVo.setParentMenuId(null);
					}else{
						menuVo.setParentMenuId(getMenuById(rs.getInt("parentMenuId")).getMenuName());
					}
				
						menuList.add(menuVo);
					
			}
			}
			page.setList(menuList);
			return page;
		}catch(SQLException e){
			throw new DaoException(e);
		}finally{
			DBUtil.close(pstmt, rs);
		}
	}

	public List<Menu> listChildMenu(int menuId) throws DaoException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Menu> childList = new ArrayList<Menu>();
		Menu menu = null;
		try{
			String sql = "select * from menu where parentMenuId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, menuId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				menu = new Menu();
				menu.setMenuId(rs.getInt("menuId"));
				menu.setMenuCode(rs.getString("menuCode"));
				menu.setMenuName(rs.getString("menuName"));
				menu.setMenuPath(rs.getString("menuPath"));
				menu.setParentMenuId(menuId);
				childList.add(menu);
		    }
			}catch(SQLException e){
				throw new DaoException(e);
			}finally{
				DBUtil.close(pstmt, rs);
			}
			return childList;
	}




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
