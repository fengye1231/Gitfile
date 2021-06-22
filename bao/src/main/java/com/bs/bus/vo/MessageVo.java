package com.bs.bus.vo;

import com.bs.bus.entity.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MessageVo extends Message {

    private Integer page=1;

    private Integer limit=10;

    //接受多个ID
    private Integer[] ids;

    private String toname;

    private String fromname;
    
    private String message;
    
    
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
	
    public String getMessage() {
		return message;
	}

	public void settMessage(String message) {
		this.message = message;
	}
	
    public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	
	
	
    public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	
	
    public String getToname() {
		return toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}
	
	
	
	
	
	
}

