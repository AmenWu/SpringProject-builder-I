package com.risenb.sms;
/**
 * 短信常量类
 */
import com.risenb.util.PropertiesFileUtil;

public class SMSConstant {
	/**
	 * 阿里大于url
	 */
	public static final String ALI_URL = PropertiesFileUtil.getInstance("Alidayu").get("ALI_URL");
	/**
	 * 阿里大于appkey
	 */
	public static final String ALI_APPKEY = PropertiesFileUtil.getInstance("Alidayu").get("ALI_APPKEY");
	/**
	 * 阿里大于secret
	 */
	public static final String ALI_SECRET = PropertiesFileUtil.getInstance("Alidayu").get("ALI_SECRET");
	
	
}
