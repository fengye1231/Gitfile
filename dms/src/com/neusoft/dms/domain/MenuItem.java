package com.neusoft.dms.domain;

import java.io.Serializable;

/**
 * 菜单项
 */
public class MenuItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 没有父菜单项
	public static final int NO_PARENT = -1;
	
	// 编号
	private int id;
	
	// 名称
	private String name;
	
	// 链接
	private String link;
	
	// 父菜单项
	private int parentId;
	
	/**
	 * @param id
	 * @param name
	 * @param link
	 * @param parentId
	 */
	public MenuItem(int id, String name, String link, int parentId) {
		this.id = id;
		this.name = name;
		this.link = link;
		this.parentId = parentId;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param link
	 * @param parent
	 */
	public MenuItem(int id, String name, String link, MenuItem parent) {
		this.id = id;
		this.name = name;
		this.link = link;
		this.parentId = parent.id;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param link
	 */
	public MenuItem(int id, String name, String link) {
		this(id, name, link, NO_PARENT);
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public MenuItem(int id, String name) {
		this(id, name, null);
	}
	
	
	public boolean hasParent() {
		return this.parentId != NO_PARENT;
	}
	
	public boolean isParentOf(MenuItem tar) {
		return (tar != null && this.id == tar.parentId);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + parentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getparentId() {
		return parentId;
	}
	public void setParent(int parentId) {
		this.parentId = parentId;
	}
	
	
}
