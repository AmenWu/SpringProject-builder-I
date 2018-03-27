package com.risenb.oss;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;

/**
 * 阿里对象存储
 * @author Administrator	
 * 使用前请修改 AliyunOss.properties配置文件
 */
public class AliyunOss {
	/**
	 * 上传流文件
	 * @param fileName 要保存的文件名  文件名尽量保持唯一
	 * @param inputStream 流
	 * @return
	 */
	public static String aliOssSave(String fileName,InputStream inputStream){
		if(null != fileName && !"".equals(fileName) && null != inputStream){
			//初始化
			OSSClient ossClient = new OSSClient(OssConstant.ALIYUN_OSS_ENDPOINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
			ossClient.putObject(OssConstant.ALIYUN_OSS_BUCKET_NAME, fileName, inputStream);
			ossClient.shutdown();
			return "http://" + OssConstant.ALIYUN_OSS_BUCKET_NAME + "." + OssConstant.ALIYUN_OSS_ENDPOINT + fileName;
		}else{
			return "";
		}
		
	}
	/**
	 * 上传网络流
	 * @param fileName 保存的文件名 文件名尽量保持唯一
	 * @param url  网络文件地址
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static String aliOssSave(String fileName,String url) throws MalformedURLException, IOException{
		if(null != fileName && !"".equals(fileName) && null != url && !"".equals(url)){
			OSSClient ossClient = new OSSClient(OssConstant.ALIYUN_OSS_ENDPOINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
			InputStream inputStream = new URL(url).openStream();
			ossClient.putObject(OssConstant.ALIYUN_OSS_BUCKET_NAME, fileName, inputStream);
			ossClient.shutdown();
			return "http://" + OssConstant.ALIYUN_OSS_BUCKET_NAME + "." + OssConstant.ALIYUN_OSS_ENDPOINT + fileName;
		}else{
			return "";
		}
	}
	/**
	 * 上传本地文件
	 * @param fileName 保存的文件名 文件名尽量保持唯一
	 * @param dir  文件目录
	 * @return
	 */
	public static String aliOssSaveLocal(String fileName,String dir){
		if(null != fileName && !"".equals(fileName) && null != dir && !"".equals(dir)){
			OSSClient ossClient = new OSSClient(OssConstant.ALIYUN_OSS_ENDPOINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
			ossClient.putObject(OssConstant.ALIYUN_OSS_BUCKET_NAME, fileName, new File(dir));
			// 关闭client
			ossClient.shutdown();
			return "http://" + OssConstant.ALIYUN_OSS_BUCKET_NAME + "." + OssConstant.ALIYUN_OSS_ENDPOINT + fileName;
		}else{
			return "";
		}
		
	}
	/**
	 * 下载文件
	 * @param fileName 要下载的文件名
	 * @param local 保存本地文件路径
	 */
	public static void downLoad(String fileName,String local){
		if(null != fileName && !"".equals(fileName) && null != local && !"".equals(local)){
			OSSClient ossClient = new OSSClient(OssConstant.ALIYUN_OSS_ENDPOINT, OssConstant.ACCESS_KEY_ID, OssConstant.ACCESS_KEY_SECRET);
			ossClient.getObject(new GetObjectRequest(OssConstant.ALIYUN_OSS_BUCKET_NAME, fileName), new File(local));
			ossClient.shutdown();
		}
	}

}
