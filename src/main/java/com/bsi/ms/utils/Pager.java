package com.bsi.ms.utils;

import java.util.List;

public class Pager {

	private Integer pagerSize = 10;
	private Integer currentPage = 1;
	private List<Object> data;
	private Integer totalCount;
	private Integer pageNum;

	public Pager(List<Object> list, int cpage) {
		setCurrentPage(cpage);
		int total = list.size();
		int pageNum = total % pagerSize == 0 ? total / pagerSize : total / pagerSize + 1;
		int fromIndex = (currentPage - 1) * pagerSize;
		int toIndex = currentPage * pagerSize < total ? currentPage * pagerSize : total;
		List<Object> sub = list.subList(fromIndex, toIndex);
		pageNum = pageNum == 0 ? 1 : pageNum;
		setData(sub);
		setTotalCount(total);
		setPageNum(pageNum);
	}

	public Integer getPagerSize() {
		return pagerSize;
	}

	public void setPagerSize(Integer pagerSize) {
		this.pagerSize = pagerSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	@Override
	public String toString() {
		return "Pager [pagerSize=" + pagerSize + ", currentPage=" + currentPage + ", data=" + data + ", totalCount="
				+ totalCount + ", pageNum=" + pageNum + "]";
	}
}
