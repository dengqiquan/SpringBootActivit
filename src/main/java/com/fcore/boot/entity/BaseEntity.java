package com.fcore.boot.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	/**
	*@Fields id :ID
	*/
	private Long id;
	/**
	*@Fields createUserId :创建人ID
	*/
	private Long createUserId;
	/**
	*@Fields createTime :创建时间
	*/
	private String createTime;
	/**
	*@Fields updateUserId :修改人ID
	*/
	private Long updateUserId;
	/**
	*@Fields updateTime :修改时间
	*/
	private String updateTime;
	/**
	*@Fields isDelete :删除状态 2：删除 1：正常
	*/
	private Integer isDelete;
	
	private int pageNumber;
	
	private int pageSize;
	
	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
