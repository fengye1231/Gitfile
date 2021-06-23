package com.bs.sys.vo;

import com.bs.sys.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {

    private Integer page=1;
    private Integer limit=10;
    
    private String type;
    

    
    public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
    public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
    
    
    
    
}
