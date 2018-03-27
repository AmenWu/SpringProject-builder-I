package com.risenb.manage.dao;

import org.springframework.stereotype.Repository;

import com.risenb.base.BaseMapper;
import com.risenb.manage.bean.SysLog;

@Repository
public interface SysLogMapper extends BaseMapper<SysLog, Integer>{
	
	SysLog queryDetail(SysLog sysLog);
}