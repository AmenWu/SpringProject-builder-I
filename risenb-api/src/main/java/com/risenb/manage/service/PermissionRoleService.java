package com.risenb.manage.service;

import java.util.List;

import com.risenb.base.BaseService;
import com.risenb.manage.bean.PermissionRole;

public interface PermissionRoleService extends BaseService<PermissionRole, Integer>{

	//查询
	List<PermissionRole> queryPermissionRoleList(PermissionRole permissionRole);
	
	//删除
	boolean remove(PermissionRole permissionRole);
	
	/**
	 * 批量插入
	 * 
	 * @param manageId
	 * @param roleId
	 * @param ids
	 * @return
	 */
	boolean batchCreate(Integer manageId, Integer roleId, List<String> ids);

}
