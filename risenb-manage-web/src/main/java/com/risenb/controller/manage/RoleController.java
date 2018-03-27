package com.risenb.controller.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.risenb.bean.Page;
import com.risenb.bean.Result;
import com.risenb.common.CommonControllor;
import com.risenb.common.Constant;
import com.risenb.manage.bean.Manager;
import com.risenb.manage.bean.Permission;
import com.risenb.manage.bean.PermissionRole;
import com.risenb.manage.bean.Role;
import com.risenb.manage.service.PermissionRoleService;
import com.risenb.manage.service.PermissionService;
import com.risenb.manage.service.RoleService;
import com.risenb.util.JsonUtil;
import com.risenb.util.StringUtil;

/**
 * <p>
 * Title:角色控制类
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("role")
public class RoleController extends CommonControllor {

	@Resource
	RoleService roleService;

	@Resource
	PermissionService permissionService;

	@Resource
	PermissionRoleService permissionRoleService;

	/**
	 * 角色列表
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("manage")
	public ModelAndView roleListByPage(@RequestParam Map<String, Object> params, Integer pageSize, Integer pageCurrent) {
		ModelAndView mv = new ModelAndView("manage/role_manage");
		Page<Role> page = new Page<Role>().setPageNo(pageCurrent == null ? 1 : pageCurrent).setPageSize(pageSize == null ? 15 : pageSize).setParams(params);
		page = roleService.getListByPage(page);
		mv.addObject("page", page);
		request().setAttribute("params", params);
		return mv;
	}

	/**
	 * 跳转到角色编辑页面
	 * 
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "toSavePage")
	public ModelAndView toSaveRole(Integer rid) {
		ModelAndView mv = new ModelAndView("manage/role_save");
		if (rid != null) {
			Role role = roleService.getById(rid);
			mv.addObject("role", role);
			List<PermissionRole> permissionRoleList = role.getPermissionRoleList();
			String psid = "";
			if (null != permissionRoleList) {
				for (PermissionRole permissionRole : permissionRoleList) {
					psid += permissionRole.getPermissionId() + ",";
				}
			}
			if (psid.length() > 0) {
				psid = StringUtil.removeLastCharacter(psid);
			}
			mv.addObject("psid", psid);
		}
		Page<Permission> page = new Page<Permission>();
		page.setIsPage(false);
		page = permissionService.getListByPage(page);
		List<Permission> permissionList = null;
		if (null != page) {
			permissionList = (List<Permission>) page.getResults();
		}
		mv.addObject("ps", permissionList);
		String json = JsonUtil.objectToJson(permissionList);
		mv.addObject("ztreeJson", json);
		return mv;
	}

	/**
	 * 编辑角色
	 * 
	 * @param role
	 * @param pids
	 * @throws IOException
	 */
	@RequestMapping(value = "save")
	public void saveRole(@RequestBody @ModelAttribute Role role, String pids) throws IOException {
		Manager manager = (Manager) session().getAttribute(Constant.SESSION_MANAGER);
		boolean result = false;
		Integer roleId = role.getRoleId();
		role.setOperator(manager.getManageId());
		role.setOperaTime(new Date());
		if (null != roleId) {
			result = roleService.update(role);
		} else {
			role = roleService.create(role);
			roleId = role.getRoleId();
			result = (null != role ? true : false);
		}
		if (result) {
			boolean flag = true;
			if (null != roleId) {
				PermissionRole permissionRole = new PermissionRole();
				permissionRole.setRoleId(roleId);
				permissionRoleService.remove(permissionRole);
				if (StringUtil.isNotEmpty(pids)) {
					List<String> ids = new ArrayList<String>();
					ids = Arrays.asList(pids.split(","));
					if (ids.size() > 0) {
						flag = permissionRoleService.batchCreate(manager.getManageId(), roleId, ids);
					}
				}
			}
			returnJson(handleResult(flag));
		} else {
			returnJson(Result.fail("操作失败！"));
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @throws IOException
	 */
	@RequestMapping(value = "del")
	public void remove(String rids) throws IOException {
		boolean b = false;
		List<String> list = new ArrayList<String>();
		List<Integer> idList = new ArrayList<Integer>();
		if (!StringUtils.isBlank(rids)) {
			list = Arrays.asList(rids.split(","));
			list.forEach((id) -> idList.add(Integer.parseInt(id)));
			b = roleService.batchDelete(idList);
		}
		returnJson(handleResult(b));
	}
}
