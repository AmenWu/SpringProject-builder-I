package com.risenb.manage.dao;

import org.springframework.stereotype.Repository;

import com.risenb.base.BaseMapper;
import com.risenb.manage.bean.Role;

@Repository
public interface RoleMapper extends BaseMapper<Role, Integer>{
	
    int create(Role role); 
}