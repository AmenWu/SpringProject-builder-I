package com.risenb.manage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risenb.base.BaseMapper;
import com.risenb.base.impl.BaseServiceImpl;
import com.risenb.manage.bean.Manager;
import com.risenb.manage.bean.Role;
import com.risenb.manage.dao.ManagerMapper;

@Service("managerService")
public class ManagerServiceImpl extends BaseServiceImpl<Manager, Integer> implements ManagerService {
	@Autowired
	ManagerMapper managerMapper;
	
	@Override
	public BaseMapper<Manager, Integer> getBaseMapper() {
		return managerMapper;
	}
	
	@Override
	public Manager getManageByUserName(Manager manager) {
		return managerMapper.getManageByUserName(manager);
	}

	@Override
	public int assignRole(Map<String, Object> map) {
		int row = 0;
		row = managerMapper.delRoleByManageId(map);
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) map.get("roleIdList");
		if (list.size() > 0) {
			row = managerMapper.assignRole(map);
		}
		return row;
	}

	@Override
	public List<Role> queryExistingRoleByMid(Integer mid) {
		return managerMapper.queryExistingRoleByMid(mid);
	}

}