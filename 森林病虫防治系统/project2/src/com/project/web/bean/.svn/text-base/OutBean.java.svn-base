package com.project.web.bean;

import java.sql.Date;
import java.util.List;
/**
 * 	出库实体类
 * @author TE
 *
 */
public class OutBean {
	/**
	 * 出库类id
	 */
   private int id;
   /**班级外键*/
   private int classId;
   /**班级名字*/
   private String className;
   /**用户外键*/
   private int userId;
   /**用户名字*/
   private String userName;
   
	/**
	 * 药剂器械信息集合
	 */
   private List<OutMachineBean> list;
	/**
	 * 出库时间，默认为当前时间
	 */
   private Date date = new Date(System.currentTimeMillis());;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<OutMachineBean> getList() {
		return list;
	}
	public void setList(List<OutMachineBean> list) {
		this.list = list;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public OutBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OutBean [id=" + id + ", classId=" + classId + ", className=" + className + ", userId=" + userId
				+ ", userName=" + userName + ", list=" + list + ", date=" + date + "]";
	}
	public OutBean(int classId, String className, int userId, String userName) {
		super();
		this.classId = classId;
		this.className = className;
		this.userId = userId;
		this.userName = userName;
	}
	public OutBean(int classId, String className, int userId, String userName, List<OutMachineBean> list) {
		super();
		this.classId = classId;
		this.className = className;
		this.userId = userId;
		this.userName = userName;
		this.list = list;
	}
	

	
   
   
	
}