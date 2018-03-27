package com.risenb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月17日 下午1:45:54 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
	
	/** 是否记录日志 */
	boolean isLog() default true;
}
