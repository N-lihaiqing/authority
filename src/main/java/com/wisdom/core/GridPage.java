package com.wisdom.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GridPage<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**总页码数*/
	private long total = 1;
	/**数据集合*/
	private List<T> rows = new ArrayList<T>();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public GridPage(long total, List<T> list) {
		setTotal(total);
		setRows(list);
	}

	public GridPage() {
		super();
	}
}
