package com.neusoft.dms.domain;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	private Page page;
	private List<TableHeaderCell> header;
	private List<TableFooterCell> footer;
	
	/**
	 * @param page
	 * @param columns
	 * @param footer
	 */
	public Table(Page page, List<TableHeaderCell> header,
			List<TableFooterCell> footer) {
		this.page = page;
		this.header = header;
		this.footer = footer;
	}
	
	public Table() {}
	
	/**
	 * 
	 * @param page
	 * @param columns
	 */
	public Table(Page page, List<TableHeaderCell> header) {
		this(page, header, null);
	}
	
	/**
	 * 
	 * @param page
	 */
	public Table(Page page) {
		this(page, null);
	}

	public Table addHeaderCell(TableHeaderCell cell) {
		if (this.header == null) {
			this.header = new ArrayList<TableHeaderCell>();
		}
		this.header.add(cell);
		return this;
	}
	
	public Table addFooterCell(TableFooterCell cell) {
		if (this.footer == null) {
			this.footer = new ArrayList<TableFooterCell>();
		}
		this.footer.add(cell);
		return this;
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<TableHeaderCell> getHeader() {
		return header;
	}

	public void setHeader(List<TableHeaderCell> header) {
		this.header = header;
	}

	public List<TableFooterCell> getFooter() {
		return footer;
	}

	public void setFooter(List<TableFooterCell> footer) {
		this.footer = footer;
	}
	


}
