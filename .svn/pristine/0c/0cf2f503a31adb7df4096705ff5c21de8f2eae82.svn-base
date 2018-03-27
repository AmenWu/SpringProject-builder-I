package com.risenb.manage.service;

import java.util.List;
import java.util.Map;

import com.risenb.base.BaseService;
import com.risenb.manage.bean.Manager;
import com.risenb.manage.bean.Role;

public interface ManagerService extends BaseService<Manager, Integer> {

	Manager getManageByUserName(Manager manager);

	int assignRole(Map<String, Object> map);

	List<Role> queryExistingRoleByMid(Integer mid);
}
