package com.project.web.bean;

import java.io.Serializable;
import java.sql.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
/**
 * 小班实体类
 * @author TE
 *
 */
@JsonIgnoreProperties(value = {"handler"})
public class ClassBean implements Serializable{
	/**
	 * 小班id
	 */
private int id;
/**
 * 小班名称
 */
private String name;
/**
 * 负责人
 */
private String principal;
/**
 * 负责人电话
 */
private String phoneNum;
/**
 * 小班人数
 */
private int num;
/**
 * 创建时间
 */
private Date date;
/**
 * 所在地区对象
 */
private AreaBean areaBean;

public ClassBean() {
	// TODO Auto-generated constructor stub
}

public AreaBean getAreaBean() {
	return areaBean;
}

public void setAreaBean(AreaBean areaBean) {
	this.areaBean = areaBean;
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
public String getPrincipal() {
	return principal;
}
public void setPrincipal(String principal) {
	this.principal = principal;
}
public String getPhoneNum() {
	return phoneNum;
}
public void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}



@Override
public String toString() {
	return "ClassBean [id=" + id + ", name=" + name + ", principal=" + principal + ", phoneNum=" + phoneNum + ", num="
			+ num + ", date=" + date + ", areaBean=" + areaBean + "]";
}

}
