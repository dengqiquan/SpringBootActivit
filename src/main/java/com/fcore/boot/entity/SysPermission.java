package com.fcore.boot.entity;

import java.io.Serializable;

public class SysPermission extends BaseEntity implements Serializable {
	
	/**
	*@Fields name :权限名称
	*/
	private String name;
	/**
	*@Fields value :权限值
	*/
	private String value;
	/**
	*@Fields href :url路径
	*/
	private String href;
	/**
	*@Fields parentId :上级菜单ID
	*/
	private long parentId;
	/**
	*@Fields flag :0:否 1:是
	*/
	private int isLast;
	
	
	private String parentName;
	
	private String parentIds;
	//等级
	private int levelCode;
	//排序编号
	private int orderCode;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	public int getIsLast() {
		return isLast;
	}
	public void setIsLast(int isLast) {
		this.isLast = isLast;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public int getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(int levelCode) {
		this.levelCode = levelCode;
	}
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
}

