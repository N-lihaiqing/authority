package com.wisdom.entity.vo;

import java.io.Serializable;

public class PageVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**当前页码*/
	private int page=1;
	/**每页条数*/
	private int rows=15;
	/**排序字段*/
	private String sidx;
	/**排序方式 asc desc*/
	private String sord;
	/**起始行*/
	private Integer startRow;
	
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
}
