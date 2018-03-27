/**
 * 
 */
package com.risenb.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2016年11月7日 下午6:58:47 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class CollectionUtil extends jodd.util.CollectionUtil {
	/**
	 * 
	 * @param list
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public static <T> List<T> subList(List<T> list, int page, int pageSize) {
		if (ObjectUtil.isEmpty(list)) {
			throw new RuntimeException("param list is null!");
		}
		int start = page * pageSize;
		int end = (page + 1) * pageSize;
		if (list.size() < end) {
			end = list.size();
		}
		return list.subList(start, end);
	}

	/**
	 * 去掉list中的重复元素
	 * 
	 * @param originList
	 * @return
	 */
	public static <T> List<T> removeDuplicate(List<T> originList) {
		if (ObjectUtil.isEmpty(originList)) {
			throw new RuntimeException("param list is null!");
		}
		// java里hashSet是一种不包含重复元素的集合
		HashSet<T> hashSet = new HashSet<T>();
		hashSet.addAll(originList);
		originList.clear();
		originList.addAll(hashSet);
		return originList;
	}

	/**
	 * 过滤数据列表中，对象为null的数据.
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> filterObject(List<T> list) {
		for (Iterator<T> it = list.iterator(); it.hasNext();) {
			T t = it.next();
			if (null == t) {
				it.remove();
			}
		}
		return list;
	}
}
