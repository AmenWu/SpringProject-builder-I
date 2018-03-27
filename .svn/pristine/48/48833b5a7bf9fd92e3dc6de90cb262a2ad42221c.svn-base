package com.risenb.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risenb.base.BaseMapper;
import com.risenb.base.impl.BaseServiceImpl;
import com.risenb.bean.Page;
import com.risenb.manage.bean.PermissionRole;
import com.risenb.manage.bean.Role;
import com.risenb.manage.dao.ManagerMapper;
import com.risenb.manage.dao.RoleMapper;
import com.risenb.util.DateUtil;

@Service(value = "roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

	@Autowired
	RoleMapper roleMapper;
	@Autowired
	PermissionService permissionService;
	@Autowired
	ManagerMapper managerMapper;
	@Autowired
	PermissionRoleService permissionRoleService;

	@Override
	public BaseMapper<Role, Integer> getBaseMapper() {
		return roleMapper;
	}

	@Override
	public Role create(Role record) {
		int num = roleMapper.create(record);
		if (num > 0) {
			return record;
		} else {
			return null;
		}
	}

	@Override
	public Role getById(Integer id) {
		Role role = roleMapper.getById(id);
		if (role != null) {
			PermissionRole permissionRole = new PermissionRole();
			permissionRole.setRoleId(role.getRoleId());
			role.setPermissionRoleList(permissionRoleService.queryPermissionRoleList(permissionRole));
		}
		return role;
	}

	@Override
	public Page<Role> getListByPage(Page<Role> page) {
		List<Role> roles = roleMapper.getListByPage(page);
		for (Role role : roles) {
			role.setOperatorManage(managerMapper.getById(role.getOperator()));
			PermissionRole permissionRole = new PermissionRole();
			permissionRole.setRoleId(role.getRoleId());
			List<PermissionRole> permissionRoleList = permissionRoleService.queryPermissionRoleList(permissionRole);
			role.setPermissionRoleList(permissionRoleList);
			role.setOperaTimeStr(DateUtil.format(role.getOperaTime()));
		}
		page.setResults(roles);
		return page;
	}

}
