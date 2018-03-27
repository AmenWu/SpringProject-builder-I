package com.risenb.push;
/**
 * 推送常量类
 */
import com.risenb.util.PropertiesFileUtil;

public class PushConstant {
	/**
	 * 极光推送appid
	 */
	public static final String J_APPID = PropertiesFileUtil.getInstance("JPush").get("J_APPID");
	/**
	 * 极光推送mastersecret
	 */
	public static final String J_MASTERSECRET = PropertiesFileUtil.getInstance("JPush").get("J_MASTERSECRET");
	
}
