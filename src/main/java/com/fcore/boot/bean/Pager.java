package com.fcore.boot.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页使用bean.
 */
public class Pager implements Serializable {
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7343703255664740446L;
	
	public static final int MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	private int pageNumber = 1;// 当前页码
	private int pageSize = 10;// 每页记录数
	private int totalCount = 0;// 总记录数
	private int pageCount = 0;// 总页数
	private List<?> list;// 数据List

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount ++;
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}