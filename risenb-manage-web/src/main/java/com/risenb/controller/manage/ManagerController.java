package com.risenb.controller.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.risenb.bean.Page;
import com.risenb.bean.Result;
import com.risenb.common.CommonControllor;
import com.risenb.common.Constant;
import com.risenb.manage.bean.Manager;
import com.risenb.manage.bean.Role;
import com.risenb.manage.service.ManagerService;
import com.risenb.manage.service.RoleService;
import com.risenb.util.EncryptUtil;
import com.risenb.util.JsonUtil;

/**
 * <pre>
 *   	<p>Title:操作员控制类 </p>
 *  	<p>Description:</p>
 * </pre>
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("manage")
public class ManagerController extends CommonControllor {

	@Resource
	ManagerService managerService;

	@Resource
	RoleService roleService;

	/**
	 * 跳转到修改密码页面
	 * 
	 * @param mid
	 * @param request
	 * @param response
	 */
	@RequestMapping("/toModifyPwd")
	public String toModifyPwd(Integer mid) {
		request().setAttribute("mid", mid);
		return "manage/manage_chgpwd";
	}

	/**
	 * 修改密码
	 * 
	 * @param newpwd
	 * @param mid
	 * @throws IOException
	 */
	@RequestMapping("/modifyPwd")
	public void modifyPwd(Integer mid, String newpwd) throws IOException {
		Manager manager = managerService.getById(mid);
		manager.setPassword(EncryptUtil.encrypt(newpwd));
		boolean result = managerService.update(manager);
		returnJson(handleResult(result));
	}

	/**
	 * 查询管理员列表
	 * 
	 * @param params
	 * @param pageSize
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping(value = "/manage")
	public ModelAndView list(Map<String, Object> params, Integer pageSize, Integer pageCurrent) {
		String userName = getString("userName");
		Page<Manager> page = new Page<Manager>().setPageNo(pageCurrent == null ? 1 : pageCurrent).setPageSize(pageSize == null ? 15 : pageSize)
				.setParams(params);
		page.addParams("userName", userName);
		page = managerService.getListByPage(page);
		ModelAndView mv = new ModelAndView("manage/manage_manage");
		Map<String, Object> map = new HashMap<String, Object>();
		Role role = new Role();
		role.setStatus(0);
		map.put("role", role);
		mv.addObject("page", page);
		mv.addObject("userName", userName);
		return mv;
	}

	/**
	 * 跳转到编辑管理员页面
	 * 
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "toSavePage")
	public ModelAndView toSaveManager(Integer mid) {
		ModelAndView mv = new ModelAndView("manage/manage_save");
		if (mid != null) {
			Manager m = managerService.getById(mid);
			mv.addObject("manager", m);
		}
		return mv;
	}

	/**
	 * 编辑管理员信息
	 * 
	 * @param manager
	 * @throws IOException
	 */
	@RequestMapping(value = "saveManager")
	public void save(@RequestBody @ModelAttribute Manager manager) throws IOException {
		boolean rusult = false;
		Manager m = (Manager) session().getAttribute(Constant.SESSION_MANAGER);
		manager.setOperaTime(new Date());
		manager.setOperator(m.getManageId());
		Manager temp = new Manager();
		temp.setUserName(manager.getUserName());
		if (manager.getManageId() == null) {// 新增管理员
			manager.setCreateTime(new Date());
			manager.setPassword(EncryptUtil.encrypt(manager.getPassword()));
			if (managerService.getManageByUserName(temp) != null) {
				returnJson(Result.fail("该管理员已存在"));
				return;
			}
			rusult = managerService.insert(manager);
		} else {// 编辑管理员
			Manager queryManager = managerService.getManageByUserName(temp);
			if (null != queryManager) {
				if (queryManager.getManageId().intValue() != manager.getManageId()) {
					returnJson(Result.fail("该管理员已存在"));
					return;
				}
			}
			if (manager.getPassword().length() != 32) {
				manager.setPassword(EncryptUtil.encrypt(manager.getPassword()));
			}
			rusult = managerService.update(manager);
		}
		returnJson(handleResult(rusult));
	}

	/**
	 * 删除操作员
	 * 
	 * @param ids
	 * @throws IOException
	 */
	@RequestMapping(value = "del")
	public void remove(String ids) throws IOException {
		boolean b = false;
		List<String> list = new ArrayList<String>();
		List<Integer> idList = new ArrayList<Integer>();
		if (!StringUtils.isBlank(ids)) {
			list = Arrays.asList(ids.split(","));
			list.forEach((id) -> idList.add(Integer.parseInt(id)));
			b = managerService.batchDelete(idList);
		}
		returnJson(handleResult(b));
	}

	/**
	 * 冻结操作员
	 * 
	 * @param mid
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = "frozen")
	public void frozen(Integer mid) throws IOException {
		Manager manager = managerService.getById(mid);
		if (null == manager) {
			returnJson(Result.fail("操作失败！"));
		}
		if (manager.getStatus() == null || manager.getStatus().equals("1")) {
			manager.setStatus("0");
		} else {
			manager.setStatus("1");
		}
		boolean result = managerService.update(manager);
		// 将用户T下线
		if (result) {
			this.offLine(manager.getUserName());
		}
		returnJson(handleResult(result));
	}

	/**
	 * 跳转的分配角色的页面
	 * 
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "toAssignRole")
	public String toAssignRole(Integer mid) {
		List<Role> existingRoleList = managerService.queryExistingRoleByMid(mid);
		List<Role> roleList = roleService.getAll();
		request().setAttribute("roleList", roleList);
		request().setAttribute("existingRoleList", JsonUtil.objectToJson(existingRoleList));
		request().setAttribute("mid", mid);
		return "manage/manage_assignrole";
	}

	/**
	 * 分配角色
	 * 
	 * @param mid
	 * @param ids
	 */
	@RequestMapping(value = "assignRole")
	public void assignRole(Integer mid, String ids) {
		Manager manager = (Manager) session().getAttribute(Constant.SESSION_MANAGER);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		if (!StringUtils.isBlank(ids)) {
			list = Arrays.asList(ids.split(","));
		}
		map.put("manageId", mid);
		map.put("roleIdList", list);
		map.put("operator", manager.getManageId());
		map.put("operatime", new Date());
		int row = managerService.assignRole(map);
		if (row > 0) {
			Manager target = managerService.getById(mid);
			if (null != target) {
				this.offLine(target.getUserName());
			}
		}
		returnJson(handleResult(row));
	}

}
