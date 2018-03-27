package com.risenb.manage.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risenb.base.BaseMapper;
import com.risenb.base.impl.BaseServiceImpl;
import com.risenb.manage.bean.PermissionRole;
import com.risenb.manage.dao.PermissionRoleMapper;

@Service(value = "permissionRoleService")
public class PermissionRoleServiceImpl extends BaseServiceImpl<PermissionRole, Integer> implements PermissionRoleService {
	
	@Autowired
	PermissionRoleMapper permissionRoleMapper;
	
	@Override
	public BaseMapper<PermissionRole, Integer> getBaseMapper() {
		return permissionRoleMapper;
	}
	
	@Override
	public List<PermissionRole> queryPermissionRoleList(PermissionRole permissionRole) {
		return permissionRoleMapper.queryPermissionRoleList(permissionRole);
	}
	
	//删除
	@Override
	public boolean remove(PermissionRole permissionRole){
    	int num = permissionRoleMapper.remove(permissionRole);
    	return num > 0 ? true : false;
	}
	
	//批量插入
	@Override
	public boolean batchCreate(Integer manageId, Integer roleId, List<String> pidList){
    	int num = permissionRoleMapper.batchCreate(manageId,roleId,pidList);
    	return num > 0 ? true : false;
	}
}
