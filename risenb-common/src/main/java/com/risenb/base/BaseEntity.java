/**
 * 
 */
package com.risenb.base;

import com.alibaba.fastjson.JSONObject;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年6月3日 下午12:45:16 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class BaseEntity extends JSONObject {
	/**  */
	private static final long serialVersionUID = 339260991947385906L;
	private String c;
	private int pageNo = 1;
	private int pageSize = 10;

	/**
	 * @return the c
	 */
	public String getC() {
		return c;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(String c) {
		this.c = c;
	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return (pageNo - 1) * pageSize;
	}

	public int getEnd() {
		return pageNo * pageSize;
	}

}
