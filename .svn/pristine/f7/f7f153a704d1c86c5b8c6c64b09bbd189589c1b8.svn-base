package com.risenb.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.risenb.constant.Constants;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年3月29日 上午11:56:15 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class Start {

	private static final Logger LOGGER = LoggerFactory.getLogger(Start.class);
	static AbstractApplicationContext context;

	/**
	 * 系统启动
	 * 
	 * @param args
	 * @throws Exceptionn
	 */
	public static void main(String[] args) {
		// 启动spring容器
		context = new ClassPathXmlApplicationContext(
				"applicationContext-db.xml",
				"applicationContext-provider.xml"
				);
		LOGGER.info("System Started Successfully");
		while (true) {
			try {
				Thread.sleep(Constants.LONG_MAX);
			} catch (InterruptedException e) {
				LOGGER.error("主线程睡眠失败！", e);
			}
		}

	}
}
