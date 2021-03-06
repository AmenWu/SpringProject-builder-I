package com.risenb.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.risenb.annotation.Log;
import com.risenb.common.CacheFactory;
import com.risenb.common.Constant;
import com.risenb.manage.bean.Manager;
import com.risenb.manage.bean.Permission;
import com.risenb.manage.bean.SysLog;
import com.risenb.manage.service.SysLogService;
import com.risenb.util.StringUtil;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月17日 下午1:46:09 </p>
 * </pre>
 *
 * @author Li xin
 * @version 1.0
 */
public class MethodConsumeAdvice implements MethodInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodConsumeAdvice.class);

	@Autowired
	SysLogService sysLogService;

	@Autowired
	ThreadPoolTaskExecutor threadPool;

	@Override
	public Object invoke(MethodInvocation invocation) {
		SysLog sysLog = getRequestInfo();
		StopWatch clock = new StopWatch();
		//请求状态
		Integer status = 0;
		// 返回结果
		Object result = null;
		//错误信息
		String errorMsg = null;
		clock.start();
		Date start = new Date();
		try {
			result = invocation.proceed();
		} catch (Throwable e) {
			logger.error("Method invocation error!....." , e);
			errorMsg = e.getMessage();
			status = 1;
		}
		Date end = new Date();
		clock.stop();
		// 方法名
		Log annotation = invocation.getMethod().getAnnotation(Log.class);
		if (null == annotation || annotation.isLog()) {
			
			// 获取完成方法名
			Method method = invocation.getMethod();
			String className = method.getDeclaringClass().getName();
			String methodName = method.getName();
			methodName = className + "." + methodName;
			// 请求参数
			//Object[] arguments = invocation.getArguments();
			// 执行时间 单位毫秒
			Long time = clock.getTime();
			String rslt = null;
			//String args = null;
			try {
				//args = JSON.toJSONString(arguments);
				rslt = JSON.toJSONString(result);
			} catch (Exception e) {
				errorMsg = e.getMessage();
			}
			Integer manageId = sysLog.getManageId();
			if(null == manageId){
				ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
				HttpServletRequest request = servletRequestAttributes.getRequest();
				if (null != request) {
					HttpSession session = request.getSession();
					if (null != session) {
						Manager sessionManager = (Manager) session.getAttribute(Constant.SESSION_MANAGER);
						if (null != sessionManager) {
							manageId = sessionManager.getManageId();
							sysLog.setManageId(manageId);
						}
					}
				}
			}
			sysLog.setMethodName(methodName);
			sysLog.setStartTime(start);
			sysLog.setEndTime(end);
			sysLog.setConsum(time.intValue());
			//sysLog.setArgs(args);
			sysLog.setResult(rslt);
			sysLog.setLogId(StringUtil.getUUID());
			sysLog.setStatus(status);
			sysLogService.create(sysLog);
		}
		if (1 == status) {
			throw new RuntimeException(errorMsg);
		}
		return result;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	SysLog getRequestInfo(){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		SysLog sysLog = new SysLog();
		// 访问路径
		String path = "";
		//访问参数
		String args ="";
		// 管理员id
		Integer manageId = null;
		// 权限id
		Integer permissionId = null;
		if (null != request) {
			path = request.getServletPath();
			path = path.substring(1);
			Permission permission = (Permission) CacheFactory.get("PERMISSION_URL_CACHE", path);
			if (null != permission) {
				permissionId = permission.getPermissionId();
			}
			HttpSession session = request.getSession();
			if (null != session) {
				Manager sessionManager = (Manager) session.getAttribute(Constant.SESSION_MANAGER);
				if (null != sessionManager) {
					manageId = sessionManager.getManageId();
				}
			}
			Map<String, String[]> parameterMap = request.getParameterMap();
			args = JSON.toJSONString(parameterMap);
		}
		sysLog.setManageId(manageId);
		sysLog.setPermissionId(permissionId);
		sysLog.setArgs(args);
		return sysLog;
	}
}
