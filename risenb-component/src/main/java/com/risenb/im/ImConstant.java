package com.risenb.im;
/**
 * 即时通讯常量类
 */
import com.risenb.util.PropertiesFileUtil;

public class ImConstant {
	
	/**
	 *  环信org_name
	 */
	public static final String  EASEMO_ORG_NAME = PropertiesFileUtil.getInstance("easemo").get("EASEMO_ORG_NAME");
	
	/**
	 *  环信app_name
	 */
	public static final String  EASEMO_APP_NAME = PropertiesFileUtil.getInstance("easemo").get("EASEMO_APP_NAME");
	
	/**
	 *  环信client_id
	 */
	public static final String  EASEMO_CLIENT_ID = PropertiesFileUtil.getInstance("easemo").get("EASEMO_CLIENT_ID");
	
	/**
	 *  环信client_secret
	 */
	public static final String  EASEMO_CLIENT_SECRET = PropertiesFileUtil.getInstance("easemo").get("EASEMO_CLIENT_SECRET");
	
}
