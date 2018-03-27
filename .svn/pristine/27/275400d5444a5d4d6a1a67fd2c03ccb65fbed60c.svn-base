package com.risenb.pay;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.alipay.api.internal.util.StringUtils;
/**
 * 微信支付
 * @author Administrator
 *	使用前请先配置 WxPay.properties
 */

public class WxPay {

	/**
	 * 生成预支付id  prepay_id
	 * 
	 * @param out_trade_no 订单号
	 * @param total_fee 总金额以分为单位
	 * @param body 商品描述
	 * @param ip 服务器IP地址
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static Map<String,String> appPay(String out_trade_no,Integer total_fee,String body,String ip) throws IOException, JDOMException{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("appid", PayConstant.WX_APPID);//appid
		map.put("mch_id", PayConstant.WX_MCH_ID);//mch_id
		String randomNumber = getRandomNumber(6);//生成随机数
		map.put("nonce_str", randomNumber);//随机字符串
		map.put("body", body);//商品描述
		map.put("out_trade_no", out_trade_no);//订单号
		map.put("total_fee", total_fee.toString());//商品总价  以分为单位
		map.put("spbill_create_ip", ip);//  服务器ip
		map.put("notify_url", PayConstant.WX_NOTIFY_URL);// 回调地址
		map.put("trade_type", PayConstant.WX_TRADE_TYPE);//交易类型
		String sign = getSign(map,PayConstant.WX_SIGNKEY);
		map.put("sign", sign);
		String mapToXml = mapToXml(map);
		String sendPost = sendPost(PayConstant.WX_URL, mapToXml);
		String times=String.valueOf(System.currentTimeMillis() / 1000);
		Map<String,String> doXMLParse = doXMLParse(sendPost);
		String prepay_id="";
		if("SUCCESS".equals(doXMLParse.get("result_code")) && "SUCCESS".equals(doXMLParse.get("return_code"))){//两个值都是SUCCESS
			prepay_id=doXMLParse.get("prepay_id").toString();
		}
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("appid",  PayConstant.WX_APPID);//返回商appid
		resultMap.put("partnerid", PayConstant.WX_MCH_ID);//返回商户号
		resultMap.put("prepayid", prepay_id);//返回预支付id
		resultMap.put("noncestr", randomNumber);//返回随机数
		resultMap.put("timestamp", times);//返回时间戳
		resultMap.put("package", "Sign=WXPay");//返回时间戳
		sign = getSign(resultMap,PayConstant.WX_SIGNKEY);
		resultMap.put("sign", sign);//返回时间戳
		//resultMap.put("package", "Sign=WXPay");//扩展字段 暂填写固定值Sign=WXPay
		return  resultMap;
	}
	/**
	 * 验证签名是否正确
	 * @param params
	 * @return
	 */
	public static boolean checkSign(Map<String, String> params){
		String sign=params.get("sign");
		if(null ==sign ||"".equals(sign) || "null".equals(sign)){
			return false;
		}
		params.remove("sign");
		String signContent = getSignContent(params, PayConstant.WX_SIGNKEY);
		String md5Encode = MD5Encode(signContent, "utf-8").toUpperCase();
		if(sign.equals(md5Encode)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 签名MD5
	 * @param param 
	 * @param key
	 * @return
	 */
	public static String getSign(HashMap<String, String> param,String certpwd){
		String signContent = getSignContent(param,certpwd);
		String md5Encode = MD5Encode(signContent, "utf-8").toUpperCase();
		return md5Encode;
	}
	
	
	 /**
     * 拼接字符
     * @param sortedParams
     * @param key值
     * @return
     */
    public static String getSignContent(Map<String, String> sortedParams,String certpwd) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
		for (int i = 0, size = keys.size(); i < size; i++) {
			String key = keys.get(i);
			String value = sortedParams.get(key);
			if (StringUtils.areNotEmpty(key, value)) {
				content.append((index == 0 ? "" : "&") + key + "=" + value);
				index++;
			}
        }
        content.append("&key="+certpwd);
        return content.toString();
    }
	/**
	 * 生成随机数字
	 * @param j
	 * @return
	 */
	public static String getRandomNumber(int j) {
		Random random = new Random();
		String result="";
		for(int i=0;i<j;i++){
			result+=random.nextInt(10);
		}
		return result;
	}
	/**
	 * 对字符串进行MD5加密
	 * @param origin
	 * @param charsetname
	 * @return
	 */
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * 将map转换为xml格式
	 * @param param
	 * @return
	 */
	public static String mapToXml(HashMap<String, String> param){
		 StringBuffer sb = new StringBuffer("<xml>");
		 List<String> keys = new ArrayList<String>(param.keySet());
	     Collections.sort(keys);
		for (int i = 0, size = keys.size(); i < size; i++) {
			String key = keys.get(i);
			String value = param.get(key);
			if(null != value && !"".equals(value)){
				sb.append("<");
				sb.append(key);
				sb.append(">");
				sb.append(value);
				sb.append("</");
				sb.append(key);
				sb.append(">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	/**
	 * post请求
	 * @param url
	 * @param param
	 * @return
	 */
	 public static String sendPost(String url, String param) {  
	    	OutputStreamWriter out = null;  
	        BufferedReader in = null;  
	        String result = "";  
	        try {  
	            URL realUrl = new URL(url);  
	            //打开和URL之间的连接  
	            URLConnection conn = realUrl.openConnection();  
	            //设置通用的请求属性  
	            conn.setRequestProperty("Accept", "application/json");  
	            conn.setRequestProperty("connection", "Keep-Alive");  
	            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
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
	            e.printStackTrace();  
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
	        return result;  
	    }  

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> doXMLParse(String strxml) throws JDOMException,
			IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map<String,String> m = new HashMap<String,String>();

		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}
	 
	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}
}
