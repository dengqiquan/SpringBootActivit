package com.fcore.boot.entity;

import java.io.Serializable;
import java.util.List;

public class SessionUser implements Serializable{
	private Integer userId;
	private String loginName;
	private String userName;
	// 菜单权限
	private List<Object[]> permissionList;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Object[]> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Object[]> permissionList) {
		this.permissionList = permissionList;
	}
}