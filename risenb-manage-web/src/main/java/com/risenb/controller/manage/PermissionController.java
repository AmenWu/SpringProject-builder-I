package com.risenb.controller.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.risenb.bean.Page;
import com.risenb.bean.Result;
import com.risenb.common.CacheFactory;
import com.risenb.common.CommonControllor;
import com.risenb.initialize.InitializePermission;
import com.risenb.manage.bean.Permission;
import com.risenb.manage.service.PermissionService;
import com.risenb.util.JsonUtil;

/**
 * <p>
 * Title:权限控制类
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends CommonControllor {

	@Resource
	PermissionService permissionService;
	@Resource
	InitializePermission initializePermission;

	/**
	 * 权限列表
	 * 
	 * @param parentid
	 * @return
	 */
	@RequestMapping("/navList")
	public ModelAndView navList(Integer parentId) {
		Page<Permission> page = new Page<Permission>();
		page.getParams().put("isMenu", 0);
		page.setIsPage(false);
		page = permissionService.getListByPage(page);
		List<Permission> permissionList = null;
		Permission permission = new Permission();
		if (null != page) {
			permissionList = page.getResults();
			permission.setPermissionId(0);
			permission.setTitle("权限树");
			permission.setSort(0);
		}
		permissionList.add(permission);
		Collections.sort(permissionList);
		String json = JsonUtil.objectToJson(permissionList);
		request().setAttribute("ztreeJson", json);
		request().setAttribute("parentId", parentId);
		return new ModelAndView("/manage/permission_manage").addObject("permissions", permissionList);
	}

	/**
	 * 跳转到权限编辑页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("toSavePage")
	public ModelAndView toSavePage(Integer permissionId, Integer parentId) {
		ModelAndView model = new ModelAndView("/manage/permission_save");
		Permission permission = null;
		if (null != permissionId) {
			Page<Permission> page = new Page<Permission>();
			page.getParams().put("permissionId", permissionId);
			page.setIsPage(false);
			page = permissionService.getListByPage(page);
			List<Permission> permissionList = null;
			if (null != page) {
				permissionList = page.getResults();
				if (null != permissionList && permissionList.size() > 0) {
					permission = permissionList.get(0);
				}
			}
		}
		model.addObject("permission", permission);
		model.addObject("parentId", parentId);
		return model;
	}

	/**
	 * 获取权限列表数据
	 * 
	 * @param parentid
	 */
	@RequestMapping("/manage")
	public String manage(Integer parentId, Map<String, Object> params, Integer pageSize, Integer pageCurrent) {
		if (parentId == null) {
			parentId = 0;
		}
		params.put("parentId", parentId);
		Page<Permission> page = new Page<Permission>().setPageNo(pageCurrent == null ? 1 : pageCurrent).setPageSize(pageSize == null ? 15 : pageSize).setParams(params);
		page = permissionService.getListByPage(page);
		request().setAttribute("page", page);
		request().setAttribute("parentId", parentId);
		return "/manage/permission_list";
	}

	/**
	 * 保存编辑
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public void save(Integer permissionId) throws IOException {
		String title = getString("title");
		String sort = getString("sort");
		String parentId = getString("parentId");

		if (null == sort || "".equals(sort)) {
			sort = "0";
		}
		String url = request().getParameter("url");
		String isMenu = request().getParameter("isMenu");
		if (null == isMenu || "".equals(isMenu)) {
			isMenu = "0";
		}

		Permission permission = new Permission();
		permission.setPermissionId(permissionId);
		permission.setUrl(url);
		permission.setTitle(title);
		permission.setIsMenu(Integer.valueOf(isMenu));
		permission.setSort(Integer.parseInt(sort));
		permission.setOperaTime(new Date());
		boolean flag = false;
		Result result = null;
		if (permissionId != null) {
			flag = permissionService.update(permission);
			result = Result.handleResult(flag, "修改成功", "修改失败");
		} else {
			permission.setParentId(Integer.parseInt(parentId));
			flag = permissionService.insert(permission);
			result = Result.handleResult(flag, "添加成功", "添加失败");
		}
		if (flag) {
			initializePermission.refreshPermission();
		}
		returnJson(result);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/del")
	public void remove(Integer id) throws IOException {
		List<Integer> ids = new ArrayList<Integer>();
		Permission permission = (Permission) CacheFactory.get("PERMISSION_CACHE", id);
		ids = getPermissionChildId(permission, ids);
		boolean flag = false;
		flag = permissionService.batchDelete(ids);
		Result result = Result.handleResult(flag, "删除成功", "删除失败");
		if (flag) {
			initializePermission.refreshPermission();
		}
		returnJson(result);
	}

	// 递归获取权限子集
	private List<Integer> getPermissionChildId(Permission permission, List<Integer> list) {
		list.add(permission.getPermissionId());
		List<Permission> children = permission.getChildren();
		if (null != children && children.size() > 0) {
			for (int i = 0, size = children.size(); i < size; i++) {
				Permission per = children.get(i);
				list = getPermissionChildId(per, list);
			}
		}
		return list;
	}

}