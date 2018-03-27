/**
 * 
 */
package com.risenb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import com.risenb.constant.DateFormat;


/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2016年11月7日 下午6:48:29 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class DateUtil implements DateFormat{
	/**
	 * 返回当前时间的字符串
	 * 
	 * @return String
	 */
	public static String getCurrDateStr() {
		return getCurrDateStr(DEFAULT_FORMAT);
	}

	/**
	 * 返回指定格式的当前时间的字符串
	 * 
	 * @param format
	 * @return String
	 */
	public static String getCurrDateStr(String format) {
		if (null == format) {
			throw new RuntimeException("Format date error: format is null!");
		}
		return format(getCurrDate(), format);
	}

	/**
	 * 返回当前时间的对象
	 * 
	 * @return Date
	 */
	public static Date getCurrDate() {
		return DateTime.now().toDate();
	}

	/**
	 * 按照指定格式将Date对象格式化为时间字符串
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String format(Date date, String format) {
		if (null == date) {
			throw new RuntimeException("Format date error: date is null!");
		}
		if (null == format) {
			throw new RuntimeException("Format date error: format is null!");
		}
		return new DateTime(date).toString(format);
	}

	/**
	 * 转换默认格式为指定时间格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Object date, String format) {

		if (date == null)
			return null;
		if ("".equals(date))
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		try {
			return format(sdf.parse(date.toString()), format);
		} catch (ParseException e) {
			throw new RuntimeException("Input '" + date + "' format is not correct，must be: " + DEFAULT_FORMAT, e);
		}

	}

	/**
	 * 格式化时间,采用默认格式
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, DEFAULT_FORMAT);
	}

	/**
	 * 
	 * 按照指定的格式解析时间字符串并返回Date对象
	 * 
	 * @param date
	 * @param format
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parse(String date, String format) throws ParseException {
		if (null == date) {
			throw new RuntimeException("Parse date error: date is null!");
		}
		if (null == format) {
			throw new RuntimeException("Parse date error: format is null!");
		}
		return new SimpleDateFormat(format).parse(date);
	}

	/**
	 * 按照默认格式解析时间字符串并返回Date对象
	 * 
	 * @param date
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parse(String date) throws ParseException {
		if (ObjectUtil.isEmpty(date)) {
			throw new RuntimeException("param date is empty!");
		}
		return parse(date, DEFAULT_FORMAT);
	}

	/**
	 * 转换时间字符串的格式
	 * 
	 * @param srcDate
	 *            源时间字符串
	 * @param srcFormat
	 *            源格式
	 * @param destFormat
	 *            目标格式
	 * @return String
	 * @throws ParseException
	 */
	public static String format(String srcDate, String srcFormat, String destFormat) throws ParseException {
		if (null == destFormat) {
			throw new RuntimeException("Format date error: destFormat is null!");
		}
		Date date = parse(srcDate, srcFormat);
		return format(date, destFormat);
	}

	/**
	 * 返回指定时间毫秒数
	 * 
	 * @param date
	 * @return long
	 */
	// --FIXME:改.
	public static long getTimeMillis(Date date) {
		if (null == date) {
			throw new NullPointerException("param date is null!");
		}
		return new DateTime(date).getMillis();
	}

	/**
	 * 返回指定时间的秒数
	 * 
	 * @param date
	 * @return long
	 */
	// --FIXME:改.
	public static long getTimeSecond(Date date) {
		if (null == date) {
			throw new NullPointerException("param date is null!");
		}
		return getTimeMillis(date) / 1000;
	}

	/**
	 * 将指定时间增加8小时
	 * 
	 * @param srcDate
	 * @return Date
	 */
	public static Date plusEightHours(Date srcDate) {
		return plusHours(srcDate, 8);
	}

	/**
	 * 将指定时间增加指定的小时数
	 * 
	 * @param srcDate
	 * @return Date
	 */
	public static Date plusHours(Date srcDate, int hours) {
		if (ObjectUtil.isEmpty(srcDate)) {
			throw new RuntimeException("param srcDate is empty!");
		}
		return new DateTime(srcDate).plusHours(hours).toDate();
	}

	/**
	 * 将CST格式转换为默认时间格式
	 * 
	 * @param cstDate
	 * @return
	 * @throws ParseException
	 */
	public static String cstToDefault(String cstDate) throws ParseException {
		if (ObjectUtil.isEmpty(cstDate)) {
			throw new RuntimeException("param cstDate is empty!");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		return format(sdf.parse(cstDate));
	}

	/**
	 * 格式化开始时间
	 * 
	 * @param srcDate
	 * @return
	 */
	public static String formatStart(Object srcDate) {
		return completeDate(srcDate, true);
	}

	/**
	 * 格式化结束时间
	 * 
	 * @param srcDate
	 * @return
	 */
	public static String formatEnd(Object srcDate) {
		return completeDate(srcDate, false);
	}

	/**
	 * 补全时间
	 * 
	 * @param srcDate
	 * @param isStart	
	 * @return
	 */
	public static String completeDate(Object srcDate, boolean isStart) {
		if (ObjectUtil.isEmpty(srcDate)) {
			throw new RuntimeException("param srcDate is empty!");
		}
		String tmpDate = srcDate.toString();
		if (tmpDate.length() != 10)
			return tmpDate;
		if (isStart) {
			return srcDate + " 00:00:00";
		} else {
			return srcDate + " 23:59:59";
		}
	}
}
