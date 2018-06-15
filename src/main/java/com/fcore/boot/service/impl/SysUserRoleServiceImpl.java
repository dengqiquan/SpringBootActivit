package com.fcore.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcore.boot.bean.Pager;
import com.fcore.boot.domain.SysUserRoleDao;
import com.fcore.boot.entity.SysUserRole;
import com.fcore.boot.service.SysUserRoleService;

 /**   
* @Title: SysUserRoleServiceImpl.java 
* @Package com.fcore.boot.service
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:21:33
* @version V1.0   
* create by codeFactory
*/
@Service("SysUserRoleServiceImpl")
public class SysUserRoleServiceImpl  extends BaseServiceImpl<SysUserRole,Long> implements SysUserRoleService{
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	public void setBaseDao() {
		super.setBaseDao(sysUserRoleDao);
	}
	
	@Override
	public Pager findForPager(SysUserRole sysUserRole) {
		Pager pager = new Pager();
		if (sysUserRole.getPageSize() == 0) {
			sysUserRole.setPageSize(pager.getPageSize());
		}
		if (sysUserRole.getPageNumber() == 0) {
			sysUserRole.setPageNumber(1);
		}
		List<SysUserRole> list = sysUserRoleDao.getList(sysUserRole);
		int count = sysUserRoleDao.getCount(sysUserRole);
		pager.setList(list);
		pager.setTotalCount(count);
		return pager;
	}
	
}