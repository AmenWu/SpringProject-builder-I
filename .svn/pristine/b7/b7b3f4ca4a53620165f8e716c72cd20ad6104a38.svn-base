package com.risenb.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月11日 上午10:31:26 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class MapUtil {
	public static <T> T MapToObject(HashMap<String, Object> map, Class<T> class1)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, Exception {
		Field[] fields = class1.getDeclaredFields();
		T t = null;
		if (fields.length > 0) {
			t = class1.newInstance();
		}
		boolean flag;
		for (Field field : fields) {
			if (map.containsKey(field.getName()) && map.get(field.getName()) != null) {
				flag = false;
				if (!field.isAccessible()) {
					field.setAccessible(true);
					flag = true;
				}
				if ((field.getType() == java.util.Date.class || field.getType() == java.sql.Date.class)
						&& map.get(field.getName()).getClass() != field.getType()) {// 时间类型的转换
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					field.set(t, format.parse((String) map.get(field.getName())));
				} else if (field.getType() == java.sql.Timestamp.class
						&& map.get(field.getName()).getClass() != field.getType()) {// Timestamp转换
					field.set(t, Timestamp.valueOf((String) map.get(field.getName())));
				} else if (field.getType() == java.lang.Long.class
						&& map.get(field.getName()).getClass() != field.getType()) {// Long
					field.set(t, Long.valueOf((String) map.get(field.getName())));
				} else if ((field.getType() == int.class || field.getType() == java.lang.Integer.class)
						&& map.get(field.getName()).getClass() != field.getType()) {// int
					field.set(t, Integer.parseInt((String) map.get(field.getName())));
				} else {
					field.set(t, map.get((String) field.getName()));
				}
				if (flag) {
					field.setAccessible(false);
				}
			}
		}
		return t;
	}

	/**
	 * getFileds 获取所有public 属性<br/>
	 * getDeclaredFields 获取所有声明的属性<br/>
	 * 将某个类及其继承属性全部添加到Map中
	 * 
	 * @param bean
	 * @param clearNull
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object bean, boolean clearNull) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (bean == null) {
			return result;
		}

		Field[] fields = bean.getClass().getDeclaredFields();
		if (fields == null || fields.length == 0) {
			return result;
		}

		for (Field field : fields) {
			// 重置属性可见(而且一般属性都是私有的)，否则操作无效
			boolean accessible = field.isAccessible();
			if (!accessible) {
				field.setAccessible(true);
			}

			// 获取属性名称及值存入Map
			String key = field.getName();
			try {
				if (clearNull) {
					if (ObjectUtil.isEmpty(field.get(bean)) == false) {
						result.put(key, field.get(bean));
					}
				} else {
					result.put(key, field.get(bean));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			// 还原属性标识
			field.setAccessible(accessible);
		}

		// 获取父类属性
		fields = bean.getClass().getSuperclass().getDeclaredFields();
		if (fields == null || fields.length == 0) {
			return result;
		}

		for (Field field : fields) {
			// 重置属性可见(而且一般属性都是私有的)，否则操作无效
			boolean accessible = field.isAccessible();
			if (!accessible) {
				field.setAccessible(true);
			}

			// 获取属性名称及值存入Map
			String key = field.getName();
			try {
				if (clearNull) {
					if (ObjectUtil.isEmpty(field.get(bean)) == false) {
						result.put(key, field.get(bean));
					}
				} else {
					result.put(key, field.get(bean));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			// 还原属性标识
			field.setAccessible(accessible);
		}

		return result;
	}

	/**
	 * 将map转换成url
	 * 
	 * @param map
	 * @return
	 */
	public static String getUrlParamsByMap(Map<String, String> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (!StringUtils.isBlank(entry.getValue())) {
				sb.append(entry.getKey() + "=" + entry.getValue());
				sb.append("&");
			}
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}

	public static String getKey(Map<String, String> map, String value, boolean islike) {
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			if (islike) {
				if (StringUtils.contains(entry.getValue().toString(), value)) {
					return entry.getKey().toString();
				}
			} else {
				if (entry.getValue().equals(value))
					return entry.getKey().toString();
			}
		}
		return "";
	}

	public static Integer getIntegerByDoubleStr(Object doublestr) {
		Double b = Double.parseDouble(doublestr.toString());
		return Integer.valueOf(b.intValue());
	}
}
