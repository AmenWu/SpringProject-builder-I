package com.risenb.manage.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/*
 * 权限实体
 * */
public class Permission implements Serializable, Comparable<Permission> {

	private static final long serialVersionUID = 7834665900041554028L;

	private Integer permissionId;
	private String title; // 权限名称
	private String detail;
	private Integer status;
	private Integer parentId; // 父权限id
	private Integer operator;
	private Date operaTime;
	private String showOperatime;
	private Integer isMenu; // 权限类型 0：左侧菜单 1：功能菜单
	List<Permission> children = new ArrayList<Permission>();
	private Integer sort;
	private String url;

	/**
	 * @return the permissionId
	 */
	public Integer getPermissionId() {
		return permissionId;
	}

	/**
	 * @param permissionId
	 *            the permissionId to set
	 */
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the parentid
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentid
	 *            the parentid to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the operator
	 */
	public Integer getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	/**
	 * @return the operatime
	 */
	public Date getOperaTime() {
		return operaTime;
	}

	/**
	 * @param operatime
	 *            the operatime to set
	 */
	public void setOperaTime(Date operaTime) {
		this.operaTime = operaTime;
	}

	/**
	 * @return the showOperatime
	 */
	public String getShowOperatime() {
		return showOperatime;
	}

	/**
	 * @param showOperatime
	 *            the showOperatime to set
	 */
	public void setShowOperatime(String showOperatime) {
		this.showOperatime = showOperatime;
	}

	/**
	 * @return the ismenu
	 */
	public Integer getIsMenu() {
		return isMenu;
	}

	/**
	 * @param ismenu
	 *            the ismenu to set
	 */
	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	/**
	 * @return the children
	 */
	public List<Permission> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<Permission> children) {
		Collections.sort(children);
		this.children = children;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int compareTo(Permission o) {
		if (this.sort > o.sort) {
			return 1;
		} else {
			return -1;
		}
	}

}