package com.fcore.boot.entity;

import java.io.Serializable;

/**
* @TableName: sys_role 
* @Package: com.fcore.boot.entity
* @Title:SysRole.java 
* @Description:  
* @author: zhangjukai
* @date: 2016-09-26 16:39:52
* @version V1.0    
* create by codeFactory
*/
public class SysRole extends BaseEntity implements Serializable{
	/**
	*@Fields roleName :角色名称
	*/
	private String roleName;
	/**
	*@Fields description :角色描述
	*/
	private String description;
		public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	
	public String getRoleName(){
		return roleName;
	}
		public void setDescription(String description){
		this.description=description;
	}
	
	public String getDescription(){
		return description;
	}
}

