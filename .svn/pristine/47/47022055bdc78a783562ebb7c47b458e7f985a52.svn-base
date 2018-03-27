package com.risenb.controller.manage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.risenb.annotation.Log;
import com.risenb.common.CommonControllor;
import com.risenb.common.Constant;
import com.risenb.common.SystemInfo;
import com.risenb.manage.bean.Manager;
import com.risenb.manage.bean.Permission;
import com.risenb.manage.service.PermissionService;

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
@RequestMapping(value = "/home")
public class HomeController extends CommonControllor {

	@Autowired
	private PermissionService permissionService;

	private static SystemInfo systemInfo;

	/**
	 * 欢迎页
	 * 
	 * @return
	 */
	@Log(isLog = false)
	@RequestMapping(value = "welcome")
	public ModelAndView welcome() {
		ModelAndView view = new ModelAndView("manage/home_sysinfo");
		systemInfo = new SystemInfo();
		view.addObject(systemInfo);
		return view;
	}

	/**
	 * 跳转到首页，并展示相应的权限
	 * 
	 * @return
	 */
	@Log(isLog = false)
	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("manage/home_index");
		Manager manager = (Manager) session().getAttribute(Constant.SESSION_MANAGER);
		if (manager == null) {
			view.setViewName("login");
			return view;
		}
		List<Permission> permissionList = permissionService.getPermissionByManagerId(manager.getManageId());
		List<Permission> navMenus = new ArrayList<Permission>(); // 所有左侧菜单信息
		List<Permission> subNavMenus = new ArrayList<Permission>(); // 所有左侧菜单信息
		for (int i = 0, size = permissionList.size(); i < size; i++) {
			Permission permission = permissionList.get(i);
			Integer isMenu = permission.getIsMenu(); // 0为左侧菜单 1为功能菜单
			if (null != isMenu) {
				if (0 == isMenu) {
					subNavMenus.add(permission);
					Integer parentid = permission.getParentId();
					if (null != parentid && parentid == 0) {
						navMenus.add(permission);
					}
				}
			}
		}
		// 组织权限关系图
		for (int i = 0, size = subNavMenus.size(); i < size; i++) {
			Permission permission = subNavMenus.get(i);
			Integer id = permission.getPermissionId();
			if (null != id) {
				for (int j = 0, sizes = subNavMenus.size(); j < sizes; j++) {
					Permission permission2 = subNavMenus.get(j);
					Integer parentid = permission2.getParentId();
					if (null != parentid && parentid.intValue() == id) {
						List<Permission> children = permission.getChildren();
						if (null == children) {
							children = new ArrayList<Permission>();
						}
						children.add(permission2);
						permission.setChildren(children);
					}
				}
			}
		}
		Map<String, String> permissionMap = new HashMap<String, String>();
		for (int i = 0, size = permissionList.size(); i < size; i++) {
			Permission permission = permissionList.get(i);
			String url = permission.getUrl();
			if (null != url && !"".equals(url)) {
				permissionMap.put(url, url);
			}
		}
		// 将权限放入session
		Collections.sort(navMenus);
		session().setAttribute("permissionMap", permissionMap);
		session().setAttribute("navMenus", navMenus);
		session().setAttribute(Constant.SESSION_MANAGER_PERMISSIONLIST, permissionList);
		return view;
	}

}
