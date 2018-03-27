package com.risenb.controller.manage;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.risenb.annotation.Log;
import com.risenb.common.CacheFactory;
import com.risenb.common.CommonControllor;
import com.risenb.common.Constant;
import com.risenb.manage.bean.Manager;
import com.risenb.manage.service.ManagerService;
import com.risenb.manage.service.PermissionService;
import com.risenb.util.EncryptUtil;

/**
 * <p>
 * Title:登录控制类
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends CommonControllor {

	@Resource
	ManagerService managerService;

	@Resource
	PermissionService permissionService;

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@Log(isLog = false)
	@RequestMapping(value = "toLogin")
	public String tologin() {
		return "login";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout() {
		session().removeAttribute(Constant.SESSION_MANAGER);
		return "login";
	}

	/**
	 * 登录
	 * 
	 * @param manager
	 * @param checkcode
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestBody @ModelAttribute Manager manager, @Param(value = "checkcode") String checkcode, HttpServletResponse response)
			throws IOException {
		ModelAndView model = new ModelAndView();
		Manager sessionManager = (Manager) session().getAttribute(Constant.SESSION_MANAGER);
		if (null != sessionManager) {// 用户已经登录 跳转到首页
			response.sendRedirect("/home/index.im");
			return null;
		}
		Object attribute = session().getAttribute("checkcode");
		if (null == attribute) {
			model.addObject("error", "验证码不能为空");
			model.addObject("manager", manager);
			model.setViewName("login");
			return model;
		}
		Manager m = managerService.getManageByUserName(manager);
		String code = attribute.toString().trim();
		if (null == checkcode || "".equals(checkcode)) {
			model.addObject("error", "验证码不能为空");
		} else if (!checkcode.equalsIgnoreCase(code)) {
			model.addObject("error", "验证码不正确");
		} else if (m == null) {
			model.addObject("error", "用户名不存在");
		} else if (!EncryptUtil.check(m.getPassword(), manager.getPassword())) {
			model.addObject("error", "用户名或密码错误！");
		} else if ("1".equalsIgnoreCase(m.getStatus())) {
			model.addObject("error", "用户名被冻结,请联系管理员");
		} else {
			HttpSession httpSession = (HttpSession) CacheFactory.get("LOGIN_STATUS_CACHE", manager.getUserName());
			if (null != httpSession) {
				try {
					httpSession.removeAttribute(Constant.SESSION_MANAGER);
				} catch (Exception e) {
				}
			}
			CacheFactory.remove("LOGIN_STATUS_CACHE", manager.getUserName());
			session().setAttribute(Constant.SESSION_MANAGER, m);
			CacheFactory.put("LOGIN_STATUS_CACHE", manager.getUserName(), session());
			model.setViewName("redirect:/home/index.im");
			return model;
		}
		model.addObject("manager", manager);
		model.setViewName("login");
		return model;
	}
}
