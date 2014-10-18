package com.util;

public class Pagination {
	//开始位置
	private int start=1;
	//一次取得的数量
	private int size=6;
	//要取得的页数
	private int currentPage = 1;
	//总的记录页数
	private int totalPage = 0;
	//总的记录条数
	private int totalRecord = 0;
	
	//获得开始记录
	public int getStart() {
		this.start = (currentPage - 1) * size;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		if(currentPage < 1)
			this.currentPage = 1;
		if(currentPage >= this.totalPage)
			this.currentPage = this.totalPage;
		this.currentPage = currentPage;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage() {
		this.totalPage = totalRecord % size == 0 ? totalRecord/size : totalRecord/size + 1;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	//设置所有记录，并计算出所有页码
	public void setTotalRecord(int totalRecord) {
		if(totalRecord < 1)
			totalRecord=0;
		this.totalRecord = totalRecord;
		//获取页数
		this.totalPage = totalRecord % size == 0 ? totalRecord/size : totalRecord/size + 1;
		setTotalPage();
	}
	
	public Pagination(int size) {
		super();
		this.size = size;
	}
	@Override
	public String toString() {
		return "Pagination [start=" + start + ", size=" + size
				+ ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", totalRecord=" + totalRecord + "]";
	}
	
}
