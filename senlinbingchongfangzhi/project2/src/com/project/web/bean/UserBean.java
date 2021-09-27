package com.project.web.bean;

/**
 * 用户实体类
 * @author TE
 *
 */
public class UserBean {
	/**
	 * 用户id
	 */
   private int id;
/**
 * 用户名
 */
   private String userName;
/**
 * 密码
 */
   private String pwd;
/**
 * 真实姓名
 */
   private String name;
/**
 * 用户等级
 */
   private String level;
@Override
public String toString() {
	return "UserBean [id=" + id + ", userName=" + userName + ", pwd=" + pwd + ", name=" + name + ", level=" + level
			+ "]";
}
public UserBean() {
	super();
	// TODO Auto-generated constructor stub
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
public UserBean(String userName, String pwd) {
	super();
	this.userName = userName;
	this.pwd = pwd;
}
public UserBean(String userName, String pwd, String name, String level) {
	super();
	this.userName = userName;
	this.pwd = pwd;
	this.name = name;
	this.level = level;
}
public UserBean(int id, String pwd, String level) {
	super();
	this.id = id;
	this.pwd = pwd;
	this.level = level;
}

}