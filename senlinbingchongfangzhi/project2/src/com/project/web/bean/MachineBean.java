package com.project.web.bean;
/**
 * 药剂器械实体类
 * @author TE
 *
 */
public class MachineBean {
	/**
	 * 药剂器械id
	 */
   private int id;
	/**
	 * 药剂器械名称
	 */
   private String name;
	/**
	 * 防治类型
	 */
   private String defeat;
	/**
	 * 类别
	 */
   private String kind;
	/**
	 * 主要用途
	 */
   private String use;
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
	public String getDefeat() {
		return defeat;
	}
	public void setDefeat(String defeat) {
		this.defeat = defeat;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public MachineBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MachineBean(String name, String defeat, String kind, String use) {
		super();
		this.name = name;
		this.defeat = defeat;
		this.kind = kind;
		this.use = use;
	}
	@Override
	public String toString() {
		return "MachineBean [id=" + id + ", name=" + name + ", defeat=" + defeat + ", kind=" + kind + ", use=" + use
				+ "]";
	}
	
   
   
	
}