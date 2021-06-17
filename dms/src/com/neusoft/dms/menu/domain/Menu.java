package com.neusoft.dms.menu.domain;

public class Menu {

	private int menuId;
	private String menuName;
	private String menuCode;
	private Integer parentMenuId;
	private String menuPath;
	
	
	/**
	 * 
	 * @param menuId
	 * @param menuName
	 * @param menuCode
	 * @param parentMenuId
	 * @param menuPath
	 */
	public Menu(int menuId, String menuName, String menuCode,
			Integer parentMenuId, String menuPath) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuCode = menuCode;
		this.parentMenuId = parentMenuId;
		this.menuPath = menuPath;
	}
	
	public Menu() {}
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public Integer getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	
	
}
