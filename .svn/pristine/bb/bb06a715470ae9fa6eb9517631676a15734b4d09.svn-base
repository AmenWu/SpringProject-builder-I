package com.risenb.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.risenb.base.BaseMapper;
import com.risenb.manage.bean.PermissionRole;

@Repository
public interface PermissionRoleMapper extends BaseMapper<PermissionRole, Integer>{

	//查询权限角色信息
	List<PermissionRole> queryPermissionRoleList(PermissionRole permissionRole);
	
	//删除
	int remove(PermissionRole permissionRole);
	
	//批量新增角色权限关联信息
	int batchCreate(@Param("manageId") Integer manageId, @Param("roleId") Integer roleId,@Param("pidList") List<String> pidList); 
	
}