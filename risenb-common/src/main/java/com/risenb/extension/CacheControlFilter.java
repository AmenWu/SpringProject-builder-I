/**
 * 
 */
package com.risenb.extension;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Title:
 * @Description:
 * @Copyright: Copyright (c) 2003 - 2014
 * @Company: iSoftStone Information Technology (Group) Co.,Ltd.
 * @bulid: 2015年1月27日 下午1:43:01
 * @author Chang Yuxin
 * @version 1.0
 */
public class CacheControlFilter implements ContainerResponseFilter {
	private static final Log logger = LogFactory.getLog(CacheControlFilter.class);

	public void filter(ContainerRequestContext req, ContainerResponseContext res) {
		String methodName = req.getMethod();
		// 可以配置header
		// res.getHeaders().add(key, value);
		switch (methodName) {
		case "GET":
			logger.info("GET method excute! ");
			break;
		case "POST":
			logger.info("POST method excute! ");
			break;
		default:
			break;
		}
	}

}
