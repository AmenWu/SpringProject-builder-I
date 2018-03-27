/**
 * 
 */
package com.risenb.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.risenb.util.StringUtil;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2016年11月24日 上午10:20:54 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public abstract class BaseControllor {

	private static final Logger logger = LoggerFactory.getLogger(BaseControllor.class);

	private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
	private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();

	/**
	 * 线程安全初始化request，response对象
	 * 
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		currentRequest.set(request);
		currentResponse.set(response);
	}

	/**
	 * 获取request
	 * 
	 * @return HttpServletRequest
	 */
	public HttpServletRequest request() {
		return (HttpServletRequest) currentRequest.get();
	}

	/**
	 * 获取response
	 * 
	 * @return HttpServletResponse
	 */
	public HttpServletResponse response() {
		return (HttpServletResponse) currentResponse.get();
	}

	/**
	 * 获取session
	 * 
	 * @return HttpSession
	 */
	public HttpSession session() {
		return request().getSession();
	}

	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * 
	 * @param request
	 * @return String
	 * @throws IOException
	 */
	public final String getIpAddress(HttpServletRequest request) throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		String ip = request.getHeader("X-Forwarded-For");
		if (logger.isInfoEnabled()) {
			logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				if (logger.isInfoEnabled()) {
					logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
				}
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
	
	/**
	 * 放置令牌
	 */
	public void setToken() {
		String token = System.currentTimeMillis() + "";
		session().setAttribute("formToken", token);
		request().setAttribute("formToken", token);
	}

	/**
	 * 验证令牌是否正确 并销毁令牌
	 * 
	 * @return
	 */
	public boolean checkToken() {
		boolean flag = false;
		String token = request().getParameter("formToken");
		HttpSession session = session();
		if (null != token && !"".equals(token)) {
			Object attribute = session.getAttribute("formToken");
			if (null != attribute) {
				String sessionToken = attribute.toString();
				if (token.equals(sessionToken)) {
					flag = true;
				}
			}
		}
		session.removeAttribute("formToken");
		return flag;
	}
	
	/**
	 * 
	 */
	public static ValueFilter filter = new ValueFilter() {
		@Override
		public Object process(Object obj, String s, Object v) {
			if (null == v || "null".equals(v)) {
				return "";
			} else {
				return v;
			}
		}
	};

	/**
	 * 返回JSON
	 * 
	 * @param obj
	 */
	public void returnJson(Object obj) {
		HttpServletResponse response = response();
		response.setContentType("text/html;charset=utf-8");
		String str = "";
		if (null != obj) {
			str = JSON.toJSONString(obj, filter);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
				out.flush();
			}
		}
	}

	/**
	 * 判断是否是ajax提交
	 * 
	 * @param request
	 * @return
	 */
	public boolean isAjax() {
		if (request() != null && "XMLHttpRequest".equalsIgnoreCase(request().getHeader("X-Requested-With"))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param key
	 * @param obj
	 * @param response
	 * @param request
	 */
	public static void addCookie(String key, String obj, HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		Cookie cookie = new Cookie(key, obj);
		// 设定有效时间 以s为单位
		cookie.setMaxAge(60 * 60 * 60 * 12 * 30);
		// 设置Cookie路径和域名
		cookie.setPath("/");
		// 域名要以“.”开头
		cookie.setDomain("localhost");
		// 发送Cookie文件
		response.addCookie(cookie);
	}

	/**
	 * @param key
	 * @param response
	 * @param request
	 * @return
	 */
	public static String getCookie(String key, HttpServletResponse response, HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		Cookie cookie = null;
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (key.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * @param key
	 * @param response
	 * @param request
	 */
	public static void delCookie(String key, HttpServletResponse response, HttpServletRequest request) {
		// 删除Cookie,(将Cookie的有效时间设为0)
		Cookie cookies[] = request.getCookies();
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equals(key)) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}

	/**
	 * 设置下载文件中文件的名称
	 * 
	 * @param filename
	 * @param request
	 * @return
	 */
	public String encodeFilename(String filename, HttpServletRequest request) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				String newFileName = URLEncoder.encode(filename, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
				return MimeUtility.encodeText(filename, "UTF-8", "B");

			return filename;
		} catch (Exception ex) {
			return filename;
		}
	}

	/**
	 * 获取主机地址
	 * 
	 * @return
	 */
	public String getBasePath() {
		HttpServletRequest request = request();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}

	/**
	 * 获取http请求header中的参数的值
	 * 
	 * @param param
	 * @return
	 */
	public String getHeader(String param) {
		return request().getHeader(param);
	}

	/**
	 * 获取参数值--翻译为String类型
	 * 
	 * @param params
	 * @param name
	 * @return
	 */
	public String getString(Map<String, Object> params, String key) {
		Object value = params.get(key);
		if (null != value) {
			return params.get(key).toString();
		} else {
			return null;
		}
	}

	/**
	 * 获取指定参数值
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		String value = request().getParameter(key);
		if (StringUtil.isNotEmpty(value)) {
			return value;
		} else {
			return "";
		}
	}

	/**
	 * 获取指定参数值
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key, String defaultValue) {
		String value = request().getParameter(key);
		if (StringUtil.isNotEmpty(value)) {
			return value;
		} else {
			return defaultValue;
		}
	}

	/**
	 * 获取参数值--翻译为Integer类型
	 * 
	 * @param params
	 * @param name
	 * @return
	 */
	public Integer getInteger(Map<String, Object> params, String key) {
		if (null == params) {
			return null;
		}
		Object value = params.get(key);
		if (null != value) {
			return Integer.parseInt(params.get(key).toString());
		} else {
			return null;
		}
	}

	/**
	 * 获取参数值--翻译分页所传的参数
	 * 
	 * @param params
	 * @param name
	 * @return
	 */
	public Integer getPageInteger(Map<String, Object> params, String key) {
		// 获取页数
		if ("pageNo".equals(key)) {
			Object value = params.get(key);
			if (null != value && !"".equals(value)) {
				return Integer.parseInt(params.get(key).toString());
			}
			// 每页获取数据条数
		} else if ("pageSize".equals(key)) {
			Object value = params.get(key);
			if (null != value && !"".equals(value)) {
				return Integer.parseInt(params.get(key).toString());
			}
		}
		return null;
	}
}
