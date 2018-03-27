package com.risenb.manage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risenb.base.BaseMapper;
import com.risenb.base.impl.BaseServiceImpl;
import com.risenb.manage.bean.Permission;
import com.risenb.manage.dao.PermissionMapper;

@Service(value = "permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Integer> implements PermissionService {

	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public BaseMapper<Permission, Integer> getBaseMapper() {
		return permissionMapper;
	}

	public List<Permission> getPermissionByManagerId(Integer manageId) {
		List<Permission> list = new ArrayList<Permission>();
		if (manageId == -1) {
			list = permissionMapper.getAll();
		} else {
			list = permissionMapper.getPermissionByManagerId(manageId);
		}
		return list;
	}

}
