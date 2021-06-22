package com.bs.sys.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private Integer id;
    @JsonProperty("parentId")
    private Integer pid;
    private String title;
    private String icon;
    private String href;
    private Boolean spread;
    private List<TreeNode> children = new ArrayList<TreeNode>();

    /**
     * 0为不选中  1为选中
     */
    private String checkArr="0";

    /**
     * 首页左边导航菜单的构造器
     * @param id
     * @param pid
     * @param title
     * @param icon
     * @param href
     * @param spread
     */
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }

    /**
     * 部门 dtree的构造器
     * @param id
     * @param pid
     * @param title
     * @param spread
     */
    public TreeNode(Integer id, Integer pid, String title, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
    }
    /**
     * 查询图表部门下拉框构造器
     */
    public TreeNode(Integer id,String title,Integer pid){
        this.id=id;
        this.title=title;
        this.pid=pid;
    }

    /**
     * 给角色分配权限的构造器
     * @param id
     * @param pid
     * @param title
     * @param spread
     * @param checkArr
     */
    public TreeNode(Integer id, Integer pid, String title, Boolean spread, String checkArr) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
        this.checkArr = checkArr;
    }
    
    
    public Integer getPid() {
		return pid;
	}

	public void pid(Integer pid) {
		this.pid = pid;
	}
	
	
    public Integer getId() {
		return id;
	}

	public void id(Integer id) {
		this.id = id;
	}

	
    public List<TreeNode> getChildren() {
		return children;
	}

	public void children(List<TreeNode> children) {
		this.children = children;
	}
}
