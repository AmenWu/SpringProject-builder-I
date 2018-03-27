/**
 * 
 */
package com.risenb.common;

import javax.servlet.http.HttpSession;

import com.risenb.base.BaseControllor;
import com.risenb.bean.Result;
import com.risenb.manage.bean.Manager;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月17日 下午1:42:53 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class CommonControllor extends BaseControllor {
	/**
	 * 根据用户名将用户踢下线
	 * 
	 * @param managerName
	 */
	public void offLine(String userName) {
		if (null != userName && !"".equals(userName)) {
			HttpSession httpSession = (HttpSession) CacheFactory.get("LOGIN_STATUS_CACHE", userName);
			if (null != httpSession) {
				try {
					httpSession.removeAttribute(Constant.SESSION_MANAGER);
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 统一处理是否操作成功与操作失败
	 * 
	 * @param status
	 * @return Result
	 */
	public Result handleResult(boolean flag) {
		return Result.handleResult(flag, "操作成功！", "操作失败！");
	}

	/**
	 * 统一处理是否操作成功与操作失败
	 * 
	 * @param status
	 * @return Result
	 */
	public Result handleResult(int flag) {
		return handleResult(flag > 0);
	}

	/**
	 * 获取session中管理员
	 * 
	 * @return
	 */
	public Manager getSessionManager() {
		return (Manager) session().getAttribute(Constant.SESSION_MANAGER);
	}
}
