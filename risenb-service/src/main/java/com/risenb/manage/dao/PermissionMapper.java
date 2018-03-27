package com.risenb.manage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.risenb.base.BaseMapper;
import com.risenb.manage.bean.Permission;

@Repository
public interface PermissionMapper extends BaseMapper<Permission, Integer>{

	//根据管理员ID查询去权限列表
	List<Permission> getPermissionByManagerId(Integer manageId);
	
}