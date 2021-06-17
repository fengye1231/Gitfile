package com.neusoft.dms.menu.service;

import java.util.List;

import com.neusoft.dms.domain.MenuItem;
import com.neusoft.dms.domain.PageQuery;
import com.neusoft.dms.domain.Table;
import com.neusoft.dms.menu.domain.Menu;
import com.neusoft.dms.util.ServiceException;

public interface MenuService {

	/**
	 * 根据URI（相对应用上下文）获取最匹配的菜单项
	 * @param items
	 * @param uri
	 * @return MenuItem
	 */
	public MenuItem getBestMatchMenuItem(List<MenuItem> items, String uri);

	/**
	 * 获取指定菜单项的所有父菜单项
	 * @param item
	 * @param items
	 * @return List<MenuItem>
	 */
	public List<MenuItem> getParentMenuItems(MenuItem item, List<MenuItem> items);
	
	/**
	 * 判断指定菜单项是否有子项
	 * @param item
	 * @param items
	 * @return boolean
	 */
	public boolean hasChild(MenuItem item, List<MenuItem> items);
	
	
	/**********************************************************/
	//TODO 整合queryMenuByName,queryMenuByParentId,queryMenuByAll
	
	/**
	 * 添加菜单
	 * @param menu
	 * @throws ServiceException
	 */
	public boolean addMenu(Menu menu) throws ServiceException;
	
	/**
	 * 检查菜单名是否重复
	 * @param menu
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean checkMenuName(Menu menu) throws ServiceException;
	
	/**
	 * 检查菜单编号是否重复
	 * @param menu
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean checkMenuCode(Menu menu) throws ServiceException;
	
	/**
	 * 删除菜单
	 * @param menuName
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean delMenu(String menuName) throws ServiceException;
	
	/**
	 * 更新菜单
	 * @param menu
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean updateMenu(Menu menu) throws ServiceException;
	
	/**
	 * 通过菜单名获取菜单
	 * @param menuName
	 * @return Menu
	 * @throws ServiceException
	 */
	public Menu getMenuByName(String menuName) throws ServiceException;
	
	/**
	 * 列出所有菜单
	 * @return List<Menu>
	 * @throws ServiceException
	 */
	public List<Menu> listAllMenu() throws ServiceException;
	
	/**
	 * 列出所有父菜单
	 * @return List<Menu>
	 * @throws ServiceException
	 */
	public List<Menu> listParentMenu() throws ServiceException;
	
	/**
	 * 综合分页查询
	 * @param query
	 * @return Table
	 * @throws ServiceException
	 */
	public Table queryMenuByAll(PageQuery query) throws ServiceException;
	
}
