package com.risenb.manage.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.risenb.base.BaseMapper;
import com.risenb.base.impl.BaseServiceImpl;
import com.risenb.bean.Page;
import com.risenb.manage.bean.SysLog;
import com.risenb.manage.dao.SysLogMapper;

@Service("sysLogService")
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, Integer> implements SysLogService {
	
	private static final Logger logger = LoggerFactory.getLogger(SysLogServiceImpl.class);
	@Autowired
	SysLogMapper sysLogMapper;

	@Autowired
	ThreadPoolTaskExecutor threadPool;
	
	@Override
	public BaseMapper<SysLog, Integer> getBaseMapper() {
		return sysLogMapper;
	}
	
	@Override
	public void create(SysLog sysLog) {
		threadPool.execute(new Runnable() {
			public void run() {
				sysLog.setCreateTime(new Date());
				int i = sysLogMapper.insert(sysLog);
				if(i > 0){
					logger.info("Insert system log successfully!.......");
				}
			}
		});
	}

    // @Cacheable(cacheNames = "baseCache")
	public Page<SysLog> queryLogList(Page<SysLog> page) {
		List<SysLog> logList = sysLogMapper.getListByPage(page);
		page.setResults(logList);
		return page;
	}

}
