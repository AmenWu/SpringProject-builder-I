package com.risenb.manage.service;

import java.util.List;

import com.risenb.base.BaseService;
import com.risenb.manage.bean.Permission;

public interface PermissionService extends BaseService<Permission, Integer> {

	/**
	 * @param manageId
	 * @return
	 */
	List<Permission> getPermissionByManagerId(Integer manageId);

}
