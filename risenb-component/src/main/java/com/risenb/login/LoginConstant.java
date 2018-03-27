package com.risenb.login;
/**
 * 第三方登录产量类
 */
import com.risenb.util.PropertiesFileUtil;

public class LoginConstant {

	/****************** 微信第三方 start ******************************/

	// appId 用户注册后所获得的
	public static final String WX_APPID = PropertiesFileUtil.getInstance("Login").get("WX_APPID");
	// SECRET 用户注册后所获得的
	public static final String WX_SECRET = PropertiesFileUtil.getInstance("Login").get("WX_SECRET");
	// HTTPURL 用户注册后 所绑定的url
	public static final String WX_HTTPURL = PropertiesFileUtil.getInstance("Login").get("WX_HTTPURL");
	// 访问前后自定义的 安全校验码
	public static final String WX_STATE = PropertiesFileUtil.getInstance("Login").get("WX_STATE");
	// 访问前后自定义的 安全校验码
	public static final String WX_CODING = PropertiesFileUtil.getInstance("Login").get("WX_CODING");

	/****************** 微信第三方 end ******************************/

	/****************** QQ第三方 start ******************************/

	// CLIENT 用户注册后所获得的
	public static final String QQ_CLIENT = PropertiesFileUtil.getInstance("Login").get("QQ_CLIENT");
	// APPKEY 用户注册后所获得的
	public static final String QQ_APPKEY = PropertiesFileUtil.getInstance("Login").get("QQ_APPKEY");
	// HTTPURL 用户注册后 所绑定的url
	public static final String QQ_HTTPURL = PropertiesFileUtil.getInstance("Login").get("QQ_HTTPURL");
	// 访问前后自定义的 安全校验码
	public static final String QQ_STATE = PropertiesFileUtil.getInstance("Login").get("QQ_STATE");
	// 访问前后自定义的 安全校验码
	public static final String QQ_CODING = PropertiesFileUtil.getInstance("Login").get("QQ_CODING");

	/****************** QQ第三方 end ******************************/
	
}
