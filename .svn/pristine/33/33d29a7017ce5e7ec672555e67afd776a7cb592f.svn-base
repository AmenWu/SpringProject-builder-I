/**
 * 
 */
package com.risenb.extension;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * @Title: GZIP压缩
 * @Description:
 * @Copyright: Copyright (c) 2003 - 2014
 * @Company: iSoftStone Information Technology (Group) Co.,Ltd.
 * @bulid: 2015年1月27日 下午1:25:10
 * @author Chang Yuxin
 * @version 1.0
 */
public class GzipWriterIntercepter implements WriterInterceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.WriterInterceptor#aroundWriteTo(javax.ws.rs.ext.WriterInterceptorContext)
	 */
	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		OutputStream outputStream = context.getOutputStream();
		context.setOutputStream(new GZIPOutputStream(outputStream));
		context.proceed();
	}

}
