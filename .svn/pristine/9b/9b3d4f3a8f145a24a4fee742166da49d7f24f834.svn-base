/**
 * 
 */
package com.risenb.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2016年11月7日 下午6:59:44 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
@Component
public class PropertyUtil implements ApplicationContextAware {

	private static ApplicationContext APPLICATION_CONTEXT;

	/**
	 * 获取property文件中key对应的值
	 * 
	 * @param key
	 * @return String
	 */
	public static String getString(String key) {
		return APPLICATION_CONTEXT.getMessage(key, null, null);
	}

	/**
	 * 当取不到值时，返回传入的默认值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return String
	 */
	public static String getString(String key, String defaultValue) {
		if (StringUtil.isEmpty(getString(key))) {
			return defaultValue;
		}
		return getString(key);
	}

	/**
	 * 获取property文件中key对应的值
	 * 
	 * @param key
	 * @return double
	 */
	public static double getDouble(String key) {
		return Double.parseDouble(getString(key));
	}

	/**
	 * 获取property文件中key对应的值
	 * 
	 * @param key
	 * @return int
	 */
	public static int getInt(String key) {
		return Integer.parseInt(getString(key));
	}

	/**
	 * 获取property文件中key对应的值
	 * 
	 * @param key
	 * @return int
	 */
	public static int getInt(String key, int defaultValue) {
		if (StringUtil.isEmpty(getString(key))) {
			return defaultValue;
		}
		return getInt(key);
	}

	/**
	 * 
	 * 
	 * @param applicationContext
	 * @throws BeansException
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		PropertyUtil.APPLICATION_CONTEXT = applicationContext;
	}
}