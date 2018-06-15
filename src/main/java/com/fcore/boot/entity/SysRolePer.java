package com.fcore.boot.entity;

import java.io.Serializable;

/**
* @TableName: sys_role_per 
* @Package: com.fcore.boot.entity
* @Title:SysRolePer.java 
* @Description:  
* @author: zhangjukai
* @date: 2016-09-26 16:21:19
* @version V1.0    
* create by codeFactory
*/
public class SysRolePer extends BaseEntity implements Serializable{
	/**
	*@Fields sysRoleId :
	*/
	private Long sysRoleId;
	/**
	*@Fields sysPerId :
	*/
	private Long sysPerId;
	public void setSysRoleId(Long sysRoleId){
		this.sysRoleId=sysRoleId;
	}

	public Long getSysRoleId(){
		return sysRoleId;
	}
	public void setSysPerId(Long sysPerId){
		this.sysPerId=sysPerId;
	}

	public Long getSysPerId(){
		return sysPerId;
	}

	public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("SysRolePer[");
	if(null != sysRoleId && !"".equals(sysRoleId) ){
		sb.append("sysRoleId= "+sysRoleId+",");
	}
	if(null != sysPerId && !"".equals(sysPerId) ){
		sb.append("sysPerId= "+sysPerId+",");
	}
	sb.append("]");
	String toStr =sb.toString();
	return toStr.substring(0,toStr.indexOf(",]"))+"]";
	}

}

