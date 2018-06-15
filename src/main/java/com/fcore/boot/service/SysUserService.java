package com.fcore.boot.service;

import com.fcore.boot.entity.SysUser;

public interface SysUserService extends BaseService<SysUser,Long>{

	/**
	 * 通过用户名查询
	 * @param loginName
	 * @return
	 */
	public SysUser getUserByLoginName(String loginName);

}
