package com.neusoft.dms.menu.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.domain.MenuItem;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.domain.TableFooterCell;
import com.neusoft.dms.menu.dao.MenuDao;
import com.neusoft.dms.menu.dao.MenuDaoImpl;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.permission.domain.Permission;
import com.neusoft.dms.util.DBUtil;
import com.neusoft.dms.util.DaoException;
import com.neusoft.dms.util.ServiceException;

/**
 * 
 * 菜单
 *
 */
public class MenuServiceImpl implements MenuService {
	
	private static MenuServiceImpl instance = new MenuServiceImpl();
	
	private MenuServiceImpl() {}
	
	public static MenuServiceImpl getInstance() {
		return instance;
	}

	/**
	 * 根据URI（相对应用上下文）获取最匹配的菜单项
	 * @param items
	 * @param uri
	 * @return MenuItem
	 */
	public MenuItem getBestMatchMenuItem(List<MenuItem> items, String uri) {
		MenuItem bestMatch = null;
		int index;
		for (MenuItem item : items) {
			if (item.getLink() != null) {
				index = uri.indexOf(item.getLink());
				if (index >= 0 && index <= 1) {
					if (bestMatch == null || item.getLink().length() > bestMatch.getLink().length()) {
						bestMatch = item;
					}
				}
			}
		}
		return bestMatch;
	}

	/**
	 * 获取指定菜单项的所有父菜单项
	 * @param item
	 * @param items
	 * @return List<MenuItem>
	 */
	public List<MenuItem> getParentMenuItems(MenuItem item, List<MenuItem> items) {
		List<MenuItem> parents = new ArrayList<MenuItem>();
		while (item != null && item.hasParent()) {
			for (MenuItem tar : items) {
				if (tar.isParentOf(item)) {
					item = tar;
				}
			}
			parents.add(0, item);
		}
		return parents;
	}
	
	/**
	 * 判断指定菜单项是否有子项
	 * @param item
	 * @param items
	 * @return boolean
	 */
	public boolean hasChild(MenuItem item, List<MenuItem> items) {
		boolean hasChild = false;
		for(MenuItem tar : items) {
			if (item.isParentOf(tar)) {
				hasChild = true;
				break;
			}
		}
		return hasChild;
	}
	
	
	/**********************************************/
	public boolean addMenu(Menu menu) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			MenuDao menuDao = new MenuDaoImpl(con);
			if(menuDao.getMenuByName(menu.getMenuName())==null&&menuDao.getMenuByCode(menu.getMenuCode())==null){
				menuDao.addMenu(menu);
				isSuccess = true;
			}
		}catch(DaoException e){
			throw new ServiceException("添加菜单错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("添加菜单错误"+e);

			}
		}
		return isSuccess;
	}
	
	public boolean checkMenuName(Menu menu) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			MenuDao menuDao = new MenuDaoImpl(con);
			System.out.println("serviceMenuName:"+menu.getMenuName());
			if(menuDao.getMenuByName(menu.getMenuName())==null){
				isSuccess = true;
			}
		}catch(DaoException e){
			throw new ServiceException("添加菜单错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("添加菜单错误"+e);

			}
		}
		System.out.println("serviceIssuccess:"+isSuccess);
		return isSuccess;
	}
	
	public boolean checkMenuCode(Menu menu) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			MenuDao menuDao = new MenuDaoImpl(con);
			if(menuDao.getMenuByCode(menu.getMenuCode())==null){
				isSuccess = true;
			}
		}catch(DaoException e){
			throw new ServiceException("添加菜单错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("添加菜单错误"+e);

			}
		}
		return isSuccess;
	}
	
	
	public boolean delMenu(String menuName) throws ServiceException{
		boolean isSuccess = false;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			MenuDao menuDao = new MenuDaoImpl(con);
			menuDao.delMenu(menuName);
			isSuccess = true;
		}catch(DaoException e){
			throw new ServiceException("删除菜单错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("删除菜单错误"+e);
			}
		}
		return isSuccess;
	}
	public boolean updateMenu(Menu menu) throws ServiceException{
		boolean isSuccess = false;
	    Connection con = null;
	    try{
	    	con = DBUtil.getConnection();
	    	MenuDao menuDao = new MenuDaoImpl(con);
	    	menuDao.updateMenu(menu);
	    	isSuccess = true;
	    }catch(DaoException e){
	    	throw new ServiceException("更新菜单错误"+e);
	    }finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("更新菜单错误"+e);

			}
		}
		return isSuccess;
	}
	
	public Menu getMenuByName(String menuName) throws ServiceException{
		Menu menu = null;
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			MenuDao menuDao = new MenuDaoImpl(con);
			menu = menuDao.getMenuByName(menuName);

		}catch(DaoException e){
			throw new ServiceException("获取菜单名失败"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("获取菜单名失败"+e);
			}
		}
		return menu;
	}


	
	public List<Menu> listAllMenu() throws ServiceException{
		List<Menu> menuList = null;
		Connection con = null; 
		List<Menu> menuList1 = new ArrayList<Menu>();
		try{
			con = DBUtil.getConnection();
			MenuDao menuDao = new MenuDaoImpl(con);
			menuList = menuDao.listAllMenu();
			
			for(int i=0;i<menuList.size();i++){
				if(menuList.get(i).getMenuId()!=0){
					menuList1.add(menuList.get(i));
				}
			}
		}catch(DaoException e){
			throw new ServiceException("列出所有菜单错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("列出所有菜单错误"+e);
			}
		}
		return menuList;
	}
	
	public List<Menu> listParentMenu() throws ServiceException{
		List<Menu> parentList = null;
		Connection con = null; 
		try{
			con = DBUtil.getConnection();
			MenuDao menuDao = new MenuDaoImpl(con);
			parentList = menuDao.listParentMenu();
	
		}catch(DaoException e){
			throw new ServiceException("列出所有菜单错误"+e);
		}finally{
			try {
				DBUtil.close(con);
			} catch (DaoException e) {
				throw new ServiceException("列出所有菜单错误"+e);
			}
		}
		return parentList;
	}

	public Table queryMenuByAll(PageQuery query) throws ServiceException{
		Connection con = null;
		try{
			Table result = null;
			int pageNum = query.getPageNum();
			int pageSize = query.getPageSize();
			String menuName = (String)query.getParam("menuName");
			int parentMenuId = (Integer)query.getParam("parentMenuId");
			
			con = DBUtil.getConnection();
			MenuDao menuDao = new MenuDaoImpl(con);
			Page page = menuDao.listMenuByAll(menuName,parentMenuId, pageNum, pageSize);
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

