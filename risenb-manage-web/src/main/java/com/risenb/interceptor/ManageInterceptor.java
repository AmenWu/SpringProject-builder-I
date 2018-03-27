package com.risenb.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.risenb.common.Constant;
import com.risenb.manage.bean.Manager;
import com.risenb.util.StringUtil;

public class ManageInterceptor extends HandlerInterceptorAdapter {
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
//		if (!"".equals(path) && path.length() > 1) {
//			path = path.substring(1, path.length());
//		}
//		path = path.substring(1, path.length());
		String requestType = request.getHeader("X-Requested-With"); 
		if (path.matches(Constant.NO_INTERCEPTOR_PATH_MANAGE)) {// 不需要用户登录要放过的资源
			return true;
		} else {
			HttpSession session = request.getSession();
			Manager manager = (Manager) session.getAttribute(Constant.SESSION_MANAGER);
			if (null == manager) {// 用户未登录 跳转到登录页面
				try {
					if(StringUtil.isNotEmpty(requestType) && "XMLHttpRequest".equals(requestType)){
						response.sendRedirect(request.getContextPath() + "/login/toLogin.im");
					}else{
						response.sendRedirect(request.getContextPath() + "/login/toLogin.im");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
			Map<String, String> permissionMap = (Map<String, String>) session.getAttribute("permissionMap");
			String string = permissionMap.get(path.substring(1));
			if (null == string || "".equals(string)) {
				try {
					if(StringUtil.isNotEmpty(requestType) && "XMLHttpRequest".equals(requestType)){
						response.sendRedirect(request.getContextPath() + "/error/ajaxAuthority.im");
					}else{
						response.sendRedirect(request.getContextPath() + "/error/authority.im");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
			return true;
		}
	}
}
