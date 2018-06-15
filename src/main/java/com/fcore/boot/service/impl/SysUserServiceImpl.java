package com.fcore.boot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcore.boot.bean.Pager;
import com.fcore.boot.domain.SysUserDao;
import com.fcore.boot.entity.SysUser;
import com.fcore.boot.service.SysUserService;

 /**   
* @Title: SysUserServiceImpl.java 
* @Package com.fcore.boot.service
* @Description: 
* @author zhangjukai
* @date 2016-09-26 16:16:52
* @version V1.0   
* create by codeFactory
*/
@Service("SysUserServiceImpl")
@Transactional
public class SysUserServiceImpl  extends BaseServiceImpl<SysUser,Long> implements SysUserService{

	@Autowired
	private SysUserDao userDao;

	@Autowired
	public void setBaseDao() {
		super.setBaseDao(userDao);
	}

	@Override
	public SysUser getUserByLoginName(String loginName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginName", loginName);
		List<SysUser> users = userDao.getByParams(params);
		SysUser user = null;
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public Pager findForPager(SysUser user) {
		Pager pager = new Pager();
		if (user.getPageSize() == 0) {
			user.setPageSize(pager.getPageSize());
		}
		if (user.getPageNumber() == 0) {
			user.setPageNumber(1);
		}
		List<SysUser> list = userDao.getList(user);
		int count = userDao.getCount(user);
		pager.setList(list);
		pager.setTotalCount(count);
		return pager;
	}
	
}