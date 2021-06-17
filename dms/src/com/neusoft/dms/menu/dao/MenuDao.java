package com.neusoft.dms.menu.dao;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.dms.domain.MenuItem;
import com.neusoft.dms.domain.Page;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.util.DaoException;

public interface MenuDao {

	/**
	 * 获取所有菜单项
	 * @return ArrayList<MenuItem>
	 */
	public ArrayList<MenuItem> getItems();
	
	
	//TODO 整合queryMenuByName,queryMenuByParentId,queryMenuByAll
    /**
     * 添加菜单
     * @param menu
     * @throws DaoException
     */
	public void addMenu(Menu menu) throws DaoException;
	
	/**
	 * 删除菜单
	 * @param menuName
	 * @throws DaoException
	 */
	public void delMenu(String menuName) throws DaoException;
	
	/**
	 * 通过菜单名获取菜单
	 * @param menuName
	 * @return Menu
	 * @throws DaoException
	 */
	public Menu getMenuByName(String menuName) throws DaoException;
	
	/**
	 * 通过菜单编号获取菜单
	 * @param menuCode
	 * @return Menu
	 * @throws DaoException
	 */
	public Menu getMenuByCode(String menuCode) throws DaoException;
	
	/**
	 * 通过菜单id获取菜单
	 * @param menuId
	 * @return Menu
	 * @throws DaoException
	 */
	public Menu getMenuById(int menuId) throws DaoException;
	
	/**
	 * 删除菜单
	 * @param menu
	 * @throws DaoException
	 */
	public void updateMenu(Menu menu) throws DaoException;
	
	/**
	 * 获取所有菜单
	 * @return List<Menu>
	 * @throws DaoException
	 */
	public List<Menu> listAllMenu() throws DaoException;
	
	/**
	 * 获取所有父菜单
	 * @return List<Menu>
	 * @throws DaoException
	 */
	public List<Menu> listParentMenu() throws DaoException;
	
	/**
	 * 获取综合查询的分页
	 * @param menuName
	 * @param parentMenuId
	 * @param pageNum
	 * @param pageSize
	 * @return Page
	 * @throws DaoException
	 */
	public Page listMenuByAll(String menuName,int parentMenuId,int pageNum,int pageSize) throws DaoException;
	
	/**
	 * 获取所有子菜单
	 * @param parentMenuID
	 * @return List<Menu>
	 * @throws DaoException
	 */
	public List<Menu> listChildMenu(int menuId) throws DaoException;

} 
