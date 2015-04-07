package com.well.sanwei.model;

public class BaseEntity {
	private int page=1;  //页数
	private int pageSize=8;
	private int min;
	private int max;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		this.min = (this.page-1)*this.pageSize;
		this.max = this.page*this.pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getMin() {
		this.min = (this.page-1)*this.pageSize;
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		this.max = this.page*this.pageSize;
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	
	
}
