package com.risenb.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.risenb.util.HttpsUtil;

import net.sf.json.JSONObject;

/**
 * 微信第三方登录
 * @author Administrator
 *使用此类请先配置Login.properties
 */
public class WxLogin {
	/**
	 * 调取微信登入的第一次请求
	 * @param redirect_uri    用户授权后 索要跳转路径， 注
	 * @throws IOException
	 */
	public static void loginUrl(HttpServletResponse response) throws IOException{
		String url = "https://open.weixin.qq.com/connect/qrconnect?appid="+LoginConstant.WX_APPID+"&redirect_uri="+LoginConstant.WX_HTTPURL+"&response_type=CODE&scope=snsapi_login&state="+LoginConstant.WX_STATE;
		response.sendRedirect(url);
	}
	/**
	 * 1.用户授权后 可获得code
	 * 2.根据code + 注册码    访问相应的接口获得token
	 * 3.根据token + 注册码   访问相应的接口获得对象
	 * 4.根据api文档获取用户相应的信息
	 * @param code 用后授权后 可获得
	 * @param state  校验访问时的安全码
	 * @param type  所要访问的接口
	 * @return
	 * @throws IOException
	 */
	public JSONObject getJSONObject(String code,String state,String type) throws IOException{
		//验证是否授权
		if(!("".equals(code) || null == code)){
			//验证是否自己填写的验证码
			if("abc".equals(state)){
				//开始自己的逻辑运算
				//第一步 根据code获取访问接口的token
				Map<String, String> tokenMap = new HashMap<String, String>();
				tokenMap.put("appid", LoginConstant.WX_APPID);//注册后所得
				tokenMap.put("secret", LoginConstant.WX_SECRET);//注册后所得
				tokenMap.put("code", code);//用户授权后 所得
				tokenMap.put("grant_type", "authorization_code");//所要访问的方法
				//此次访问有效时间是两个小时  
				//如若失效后https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN  默认时间 是30天详情见API文档
				String result = HttpsUtil.URLPost("https://api.weixin.qq.com/sns/oauth2/access_token", tokenMap, LoginConstant.WX_CODING);
				//转化为json
				JSONObject json = JSONObject.fromObject(result);
				//获得access_token 再加上自己注册的appid 可获得用户信息
				//开始自己的逻辑运算
				Map<String, String> userMap=new HashMap<String, String>();
				userMap.put("access_token", json.getString("access_token"));//掉接口所得数据
				userMap.put("openid", json.getString("openid"));//掉接口所得数据
				//https://api.weixin.qq.com/sns/userinfo url 此是获得用户基本信息的
				String user = HttpsUtil.URLGet("https://api.weixin.qq.com/sns/"+type, userMap, LoginConstant.WX_CODING);
				JSONObject udrtJson = JSONObject.fromObject(user);
				//udrtJson 存放着用户基本数据信息，想获取更多的信息，根据API调取更多的接口
				return udrtJson;
			}
		}
		return null;
	}
	
}
