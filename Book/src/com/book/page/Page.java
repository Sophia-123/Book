package com.book.page;

import java.util.ArrayList;
import java.util.List;

public class Page<T> extends ArrayList<T> {

	private int pageNum;
	private int pageSize;
	private int total;

	public Page(List<? extends T> list) {
		super(list);
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
