package com.fcore.boot.entity;

import java.io.Serializable;

/**
* @TableName: sys_user_role 
* @Package: com.fcore.boot.entity
* @Title:SysUserRole.java 
* @Description:  
* @author: zhangjukai
* @date: 2016-09-26 16:21:32
* @version V1.0    
* create by codeFactory
*/
public class SysUserRole extends BaseEntity implements Serializable{
	/**
	*@Fields sysUserId :
	*/
	private Long sysUserId;
	/**
	*@Fields sysRoleId :
	*/
	private Long sysRoleId;
	public void setSysUserId(Long sysUserId){
		this.sysUserId=sysUserId;
	}

	public Long getSysUserId(){
		return sysUserId;
	}
	public void setSysRoleId(Long sysRoleId){
		this.sysRoleId=sysRoleId;
	}

	public Long getSysRoleId(){
		return sysRoleId;
	}

	public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("SysUserRole[");
	if(null != sysUserId && !"".equals(sysUserId) ){
		sb.append("sysUserId= "+sysUserId+",");
	}
	if(null != sysRoleId && !"".equals(sysRoleId) ){
		sb.append("sysRoleId= "+sysRoleId+",");
	}
	sb.append("]");
	String toStr =sb.toString();
	return toStr.substring(0,toStr.indexOf(",]"))+"]";
	}

}

