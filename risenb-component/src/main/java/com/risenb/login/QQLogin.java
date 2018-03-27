package com.risenb.login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.alipay.api.internal.util.StringUtils;
import com.risenb.util.HttpsUtil;

import net.sf.json.JSONObject;

/**
 * qq第三方登录
 * @author Administrator
 *	使用此类请先配置Login.properties
 */
public class QQLogin {
	
	/**
	 * 实现第三方（QQ）登入需要提供一下
	 * 
	 * 1：客户方需要在QQ开发平台注册相应的账号并且需要申请网站开发的机制
	 * 2：注册相应的网站后 从而获得 client_id，需要吧client_id 提供提供给开发人员 
	 * 3：开发人员需要把访问项目的服务器 URL 提供给客户，客户需要在第三方的平台上绑定该URL
	 * 	或者客户方直接把账户提供给开发人员，有开发人员自己操作
	 * 
	 */
	/**
	 * 调取QQ登入的第一次请求
	 * 返回的是一个url路径，需要重定向跳转
	 * @param redirect_uri    用户授权后 索要跳转路径， 注： 一定是 Const.WX_HTTPURL + 路径
	 * @return
	 * @throws IOException 
	 * @throws IOException
	 */
	public  void loginUrl(HttpServletResponse response) throws IOException{
		String url = "https://graph.qq.com/oauth2.0/authorize?state="+LoginConstant.QQ_STATE+"&response_type=code&client_id="+LoginConstant.QQ_CLIENT+"&redirect_uri="+LoginConstant.QQ_HTTPURL+"&scope=get_user_info";
		response.sendRedirect(url);
	}
	/**
	 * 1.用户授权后 可获得code
	 * 2.根据code + 注册码    访问相应的接口获得token
	 * 3.根据token + 注册码   访问相应的接口获得对象的openId
	 * 4.根据openId + 注册码 访问相应接口获取对象
	 * 4.根据api文档获取用户相应的信息
	 * @param code 用后授权后 可获得
	 * @param state  校验访问时的安全码
	 * @param type  所要访问的接口
	 * @return
	 * @throws IOException
	 */
	public JSONObject getJSONObject(String code,String state,String type) {
		//QQ用户是否授权
		if(StringUtils.isEmpty(code)){
			//验证是否自己填写的验证码
			if("password".equals(state)){
				//获取 Access Token
				//根据code 获取token
				HashMap<String, String> tokenMap = new HashMap<String, String>();
				tokenMap.put("grant_type", "authorization_code");//写死的数据
				tokenMap.put("client_id", LoginConstant.QQ_CLIENT);//注册时所得
				tokenMap.put("client_secret", LoginConstant.QQ_APPKEY);//注册时所得
				tokenMap.put("code", code);//授权后所得
				tokenMap.put("redirect_uri", LoginConstant.QQ_HTTPURL);//和授权时保持一致
				String user = HttpsUtil.URLGet("https://graph.qq.com/oauth2.0/token", tokenMap, "UTF-8");
				JSONObject udrtJson = JSONObject.fromObject(user);
				//获取koken
				String token = udrtJson.getString("access_token");
				//通过token获取user 的 openId
				HashMap<String, String> openMap = new HashMap<String, String>();
				openMap.put("access_token", token);//掉接口所得数据
				String open = HttpsUtil.URLGet("https://graph.qq.com/oauth2.0/me", openMap, "UTF-8");
				JSONObject openJson = JSONObject.fromObject(open);
				String openId = openJson.getString("openid");
				//通过 openId 可调取用户的多个接口信息 
				//例; 调取用户的基本注册信息   get_user_info
				HashMap<String, String> userMap = new HashMap<String, String>();
				openMap.put("access_token", token);//掉接口所得数据
				openMap.put("oauth_consumer_key", LoginConstant.QQ_CLIENT);//用户注册所得
				openMap.put("openid", openId);//掉接口所得数据
				String userOpen = HttpsUtil.URLGet("https://graph.qq.com/user/"+type, userMap, "UTF-8");
				JSONObject userJson = JSONObject.fromObject(userOpen);
				//根据API可从userJson中获取详细信息
				//更多信息可根绝接口获取
				return userJson;
			}
			return null;
		}
		return null;
    }
}
