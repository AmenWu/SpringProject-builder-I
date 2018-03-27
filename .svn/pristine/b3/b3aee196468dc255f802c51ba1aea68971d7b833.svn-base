package com.risenb.controller.manage;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.risenb.annotation.Log;
import com.risenb.bean.Page;
import com.risenb.common.CommonControllor;
import com.risenb.manage.bean.SysLog;
import com.risenb.manage.service.SysLogService;

/**
 * <p>
 * Title:操作日志控制类
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @version 1.0
 */
@Controller("sysLogController")
@RequestMapping("syslog")
public class SysLogController extends CommonControllor {
	@Resource
	SysLogService sysLogService;
	@Autowired
	ThreadPoolTaskExecutor threadPool;

	/**
	 * 操作记录列表
	 * 
	 * @param params
	 * @param idkey
	 * @return
	 */
	@Log(isLog = false)
	@RequestMapping(value = "/manage")
	public ModelAndView list(@RequestParam Map<String, Object> params, Integer idkey, Integer pageSize, Integer pageCurrent) {
		ModelAndView mv = new ModelAndView("manage/syslog_manage");
		Page<SysLog> page = new Page<SysLog>().setPageNo(pageCurrent == null ? 1 : pageCurrent).setPageSize(pageSize == null ? 15 : pageSize).setParams(params);
		page = sysLogService.queryLogList(page);
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 操作记录详情
	 * 
	 * @param logId
	 * @return
	 */
	@Log(isLog = false)
	@RequestMapping(value = "/detail")
	public ModelAndView detail(String logId) {
		ModelAndView mv = new ModelAndView("manage/syslog_detail");
		SysLog sysLog = sysLogService.getById(Integer.parseInt(logId));
		mv.addObject("sysLog", sysLog);
		return mv;
	}
}
