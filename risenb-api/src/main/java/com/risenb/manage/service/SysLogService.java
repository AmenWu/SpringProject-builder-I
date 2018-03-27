package com.risenb.manage.service;


import com.risenb.base.BaseService;
import com.risenb.bean.Page;
import com.risenb.manage.bean.SysLog;

public interface SysLogService extends BaseService<SysLog, Integer>{
	
	void create(SysLog sysLog);
	
	Page<SysLog> queryLogList(Page<SysLog> page);

}
