package com.fcore.boot.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.fcore.boot.entity.SysUser;

public class BaseController {
	public  SysUser getSessionUser(){
		Subject subject = SecurityUtils.getSubject();
		return (SysUser) subject.getSession().getAttribute("sessionUser");
	}
}
