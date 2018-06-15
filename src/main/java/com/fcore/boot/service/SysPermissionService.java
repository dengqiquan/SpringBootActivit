package com.fcore.boot.service;

import org.springframework.transaction.annotation.Transactional;

import com.fcore.boot.entity.SysPermission;

@Transactional
public interface SysPermissionService extends BaseService<SysPermission,Long>{

}