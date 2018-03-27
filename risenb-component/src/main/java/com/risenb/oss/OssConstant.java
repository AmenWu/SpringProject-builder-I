package com.risenb.oss;
/**
 * OSS常量
 */
import com.risenb.util.PropertiesFileUtil;

public class OssConstant {
	
	/**
	 *  阿里云endpoint
	 */
	public static final String ALIYUN_OSS_ENDPOINT = PropertiesFileUtil.getInstance("AliyunOss").get("aliyun.oss.endpoint");
	/**
	 *  阿里云bucketName
	 */
	public static final String ALIYUN_OSS_BUCKET_NAME = PropertiesFileUtil.getInstance("AliyunOss").get("aliyun.oss.bucketName");
	/**
	 * 阿里云accesskey
	 */
	public static final String ACCESS_KEY_ID = PropertiesFileUtil.getInstance("AliyunOss").get("aliyun.oss.accessKeyId");
	/**
	 * 阿里云secret
	 */
	public static final String ACCESS_KEY_SECRET = PropertiesFileUtil.getInstance("AliyunOss").get("aliyun.oss.accessKeySecret");
}
