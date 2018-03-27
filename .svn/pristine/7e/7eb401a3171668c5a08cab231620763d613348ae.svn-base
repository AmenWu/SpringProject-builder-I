package com.risenb.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 环信即时通信
 * 
 * @author Administrator
 *	使用此方法请先初始化token，并且每隔10分钟刷新一次token值（执行getToken方法）----打开类上注解@Component和getToken()上@PostConstruct、@Scheduled注解即可
 *
 *	
 */
//@Component
public class Easemob {
	private static String token="";//管理员token值  项目启动初始化  定时刷新此值
	private static String url="http://a1.easemob.com/"+ImConstant.EASEMO_ORG_NAME+"/"+ImConstant.EASEMO_APP_NAME+"/";//环信地址
	/**
	 * 获取token值
	 */
//	@PostConstruct
//	@Scheduled(cron="0 0/10 * * * ? ")
	public static void getToken(){
		Map<String,Object> map = new HashMap<String,Object>();	//map集合用来封装请求的json参数
		map.put("grant_type", "client_credentials");
		map.put("client_id", ImConstant.EASEMO_CLIENT_ID);
		map.put("client_secret", ImConstant.EASEMO_CLIENT_SECRET);
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		JSONObject sendPost = getTokenPost(url+"token", jsonObject.toString());
		if(null != sendPost){
			Object object = sendPost.get("access_token");
			if(null != object){
				token=object.toString();
			}
		}
	}
	/**
	 * 注册环信及时通讯账号
	 * @param userName
	 * @param password
	 * @return
	 */
	public  static boolean registerIm(String userName,String password){
		Map<String,Object> map = new HashMap<String,Object>();	//map集合用来封装请求的json参数
		map.put("username", userName);
		map.put("password", password);
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		JSONObject sendPost = registerImPost(url+"users", jsonObject.toString());
		if(null == sendPost){
			return false;
		}
		Object object = sendPost.get("error");
		if(null == object){
			return true;
		}else{
			return false;
		}
		
	}
	/**
	 * 删除注册用户
	 * @param userName
	 * @return
	 */
	public  static boolean delteteIm(String userName){
		JSONObject sendPost = deleteIm(url+"users"+"/"+userName);
		if(null == sendPost){
			return false;
		}
		Object object = sendPost.get("error");
		if(null == object){
			return true;
		}else{
			return false;
		}
		
	}
	/**
	 * 获取token时发送请求
	 * @param urls
	 * @param param
	 * @return
	 */
	 public static JSONObject getTokenPost(String urls, String param) {  
		 JSONObject jsonObject = null; 
		  for(int i=0;i<3;i++){
			  OutputStreamWriter out = null;  
		        BufferedReader in = null;  
		        String result = "";  
		        try {  
		            URL realUrl = new URL(urls);  
		            //打开和URL之间的连接  
		            URLConnection conn = realUrl.openConnection();  
		            //设置通用的请求属性  
		            conn.setRequestProperty("Accept", "application/json");  
		            conn.setRequestProperty("Content-Type", "application/json");
		            //发送POST请求必须设置如下两行  
		            conn.setDoOutput(true);  
		            conn.setDoInput(true);  
		            //获取URLConnection对象对应的输出流  
		            out = new OutputStreamWriter(conn.getOutputStream(), "utf-8"); 
		            //发送请求参数  
		            out.write(param);  
		            //flush输出流的缓冲  
		            out.flush();  
		            //定义BufferedReader输入流来读取URL的响应  
		            in = new BufferedReader(  
		                new InputStreamReader(conn.getInputStream(),"utf-8"));  
		            String line;  
		            while ((line = in .readLine()) != null) {  
		                result +=line;  
		            }  
		        } catch (Exception e) {  
		            continue;
		        }  
		        //使用finally块来关闭输出流、输入流  
		        finally {  
		            try {  
		                if (out != null) {  
		                    out.close();  
		                }  
		                if ( in != null) { in .close();  
		                }  
		            } catch (IOException ex) {  
		                ex.printStackTrace();  
		            }  
		        }  
		        
		        jsonObject= JSONObject.parseObject(result);
		        Object object = jsonObject.get("access_token");
		        if(null != object){
		        	break ;
		        }
		  }
	    	
	        return   jsonObject;
	    } 
	 /**
	  * 注册及时通讯是请求
	  * @param urls
	  * @param param
	  * @return
	  */
	 public static JSONObject registerImPost(String urls, String param) {  
		 JSONObject jsonObject = null; 
		  for(int i=0;i<3;i++){
			  OutputStreamWriter out = null;  
		        BufferedReader in = null;  
		        String result = "";  
		        try {  
		            URL realUrl = new URL(urls);  
		            //打开和URL之间的连接  
		            URLConnection conn = realUrl.openConnection();  
		            //设置通用的请求属性  
		            conn.setRequestProperty("Accept", "application/json");  
		            conn.setRequestProperty("Content-Type", "application/json");
		            conn.setRequestProperty("Authorization", "Bearer "+token);
		            //发送POST请求必须设置如下两行  
		            conn.setDoOutput(true);  
		            conn.setDoInput(true);  
		            //获取URLConnection对象对应的输出流  
		            out = new OutputStreamWriter(conn.getOutputStream(), "utf-8"); 
		            //发送请求参数  
		            out.write(param);  
		            //flush输出流的缓冲  
		            out.flush();  
		            //定义BufferedReader输入流来读取URL的响应  
		            in = new BufferedReader(  
		                new InputStreamReader(conn.getInputStream(),"utf-8"));  
		            String line;  
		            while ((line = in .readLine()) != null) {  
		                result +=line;  
		            }  
		        } catch (Exception e) {  
		            continue;
		        }  
		        //使用finally块来关闭输出流、输入流  
		        finally {  
		            try {  
		                if (out != null) {  
		                    out.close();  
		                }  
		                if ( in != null) { in .close();  
		                }  
		            } catch (IOException ex) {  
		                ex.printStackTrace();  
		            }  
		        }  
		        jsonObject=  JSONObject.parseObject(result);
		        Object object = jsonObject.get("error");
		        if(null == object){
		        	break ;
		        }
		        //执行失败  刷新token值
		      //  getToken();
		  }
	    	
	        return   jsonObject;
	    }  
	 public static JSONObject deleteIm(String urls) {  
		 JSONObject jsonObject = null; 
		  for(int i=0;i<3;i++){
			  OutputStreamWriter out = null;  
		        BufferedReader in = null;  
		        String result = "";  
		        try {  
		        	CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build(); 
		            HttpDelete httpdelete = new HttpDelete(urls);
		            httpdelete.setHeader("Accept", "application/json");
		            httpdelete.setHeader("Content-Type", "application/json");
		            httpdelete.setHeader("Authorization", "Bearer "+token);
		            CloseableHttpResponse execute = closeableHttpClient.execute(httpdelete);
		            result = EntityUtils.toString(execute.getEntity()); 
		            //发送请求参数  
		        } catch (Exception e) {  
		            continue;
		        }  
		        //使用finally块来关闭输出流、输入流  
		        finally {  
		            try {  
		                if (out != null) {  
		                    out.close();  
		                }  
		                if ( in != null) { in .close();  
		                }  
		            } catch (IOException ex) {  
		                ex.printStackTrace();  
		            }  
		        }  
		        jsonObject=  JSONObject.parseObject(result);
		        Object object = jsonObject.get("error");
		        if(null == object){
		        	break ;
		        }
		        //执行失败  刷新token值
		      //  getToken();
		  }
	    	
	        return   jsonObject;
	    }  
}
