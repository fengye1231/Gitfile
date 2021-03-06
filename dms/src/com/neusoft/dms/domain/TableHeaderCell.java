package com.neusoft.dms.domain;

public class TableHeaderCell {
	
	private String field;
	private String title;
	
	/**
	 * @param field
	 * @param title
	 */
	public TableHeaderCell(String field, String title) {
		this.field = field;
		this.title = title;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
