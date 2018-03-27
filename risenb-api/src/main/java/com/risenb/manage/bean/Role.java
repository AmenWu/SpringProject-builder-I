package com.risenb.manage.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {
    
	private static final long serialVersionUID = -2271843122835358614L;

	private Integer roleId;

    private String roleName;

    private Integer parentId;

    private Integer status;

    private Integer moduleId;

    private Integer operator;

    private Date operaTime;
    
    private Manager operatorManage;

    private String operaTimeStr;
    
    private List<PermissionRole> permissionRoleList;

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the moduleId
	 */
	public Integer getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the operator
	 */
	public Integer getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	/**
	 * @return the operaTime
	 */
	public Date getOperaTime() {
		return operaTime;
	}

	/**
	 * @param operaTime the operaTime to set
	 */
	public void setOperaTime(Date operaTime) {
		this.operaTime = operaTime;
	}

	/**
	 * @return the operatorManage
	 */
	public Manager getOperatorManage() {
		return operatorManage;
	}

	/**
	 * @param operatorManage the operatorManage to set
	 */
	public void setOperatorManage(Manager operatorManage) {
		this.operatorManage = operatorManage;
	}

	/**
	 * @return the operaTimeStr
	 */
	public String getOperaTimeStr() {
		return operaTimeStr;
	}

	/**
	 * @param operaTimeStr the operaTimeStr to set
	 */
	public void setOperaTimeStr(String operaTimeStr) {
		this.operaTimeStr = operaTimeStr;
	}

	/**
	 * @return the permissionList
	 */
	public List<PermissionRole> getPermissionRoleList() {
		return permissionRoleList;
	}

	/**
	 * @param permissionList the permissionList to set
	 */
	public void setPermissionRoleList(List<PermissionRole> permissionRoleList) {
		this.permissionRoleList = permissionRoleList;
	}
    
	
}