package com.risenb.manage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.risenb.base.BaseMapper;
import com.risenb.manage.bean.Manager;
import com.risenb.manage.bean.Role;

@Repository
public interface ManagerMapper extends BaseMapper<Manager, Integer>{
	
    Manager getManageByUserName(Manager manager);
  
	int assignRole(Map<String, Object> map);

	int delRoleByManageId(Map<String, Object> map);

	List<Role> queryExistingRoleByMid(Integer manageId);
	
}