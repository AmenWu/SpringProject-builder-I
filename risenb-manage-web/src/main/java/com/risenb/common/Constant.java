package com.risenb.common;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月18日 上午9:41:50 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class Constant {
	public static final String SESSION_MANAGER = "sessionManager";
	public static final String SESSION_MANAGER_PERMISSIONLIST = "sessionPermission";
	public static final String NO_INTERCEPTOR_PATH_MANAGE = ".*/((login/login)|(login/toLogin)|(login/logout)|(home/index)|(upload/file)|(error/authority)|(login/head)|(error/notFound)|(error/innerError)|(manage/toCpPassword)|(manage/cp)|(home/welcome)|(file_upload)|(error/ajaxAuthority)|(permission/ajaxPermissionListData)|(upload/uploadImg)).*"; // 不对匹配该值的访问路径拦截（正则）後台

}
