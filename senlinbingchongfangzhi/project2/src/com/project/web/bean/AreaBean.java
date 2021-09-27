package com.project.web.bean;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 地区实体类
 * @author TE
 *
 */
@JsonIgnoreProperties(value = {"handler"})
public class AreaBean implements Serializable {
	/**
	 * id
	 */
private int id;
/**
 * 地区名称
 */
private String name;
/**
 * 林种
 */
private String species;
/**
 * 优势林种
 */
private String great;
/**
 * 地类
 */
private String gentle;
/**
 * 负责小班对象
 */
private List<ClassBean> list;



/**
 * 小班名称
 */
private String className;

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
public String getSpecies() {
	return species;
}
public void setSpecies(String species) {
	this.species = species;
}
public String getGreat() {
	return great;
}
public void setGreat(String great) {
	this.great = great;
}
public String getGentle() {
	return gentle;
}
public void setGentle(String gentle) {
	this.gentle = gentle;
}
public List<ClassBean> getList() {
	return list;
}
public void setList(List<ClassBean> list) {
	this.list = list;
}
public String getClassName() {
	return className;
}
public void setClassName(String className) {
	this.className = className;
}

public AreaBean(String name, String species, String great, String gentle) {
	super();
	this.name = name;
	this.species = species;
	this.great = great;
	this.gentle = gentle;
	
}
public AreaBean() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "AreaBean [id=" + id + ", name=" + name + ", species=" + species + ", great=" + great + ", gentle=" + gentle
			+ ", list=" + list + "]";
}


}
