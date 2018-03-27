package com.risenb.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * <pre>
 * <p>Title: 拼音工具类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年9月7日 上午11:26:34 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class PinYinUtil {

	/**
	 * 将字符串中的中文转化为拼音,其他字符不变
	 * 
	 * @param inputString
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static String getPingYin(String inputString) throws BadHanyuPinyinOutputFormatCombination {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);

		char[] input = inputString.trim().toCharArray();
		StringBuffer output = new StringBuffer();

		for (int i = 0, j = input.length; i < j; i++) {
			if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
				String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
				output.append(temp[0]);
			} else
				output.append(java.lang.Character.toString(input[i]));
		}
		return output.toString();
	}

	/**
	 * 获取汉字串拼音首字母，英文字符不变
	 * 
	 * @param chinese 汉字串
	 * @return 汉语拼音首字母
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getFirstSpell(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer buffer = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0, j = arr.length; i < j; i++) {
			if (arr[i] > 128) {
				String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
				if (temp != null) {
					buffer.append(temp[0].charAt(0));
				}
			} else {
				buffer.append(arr[i]);
			}
		}
		return buffer.toString().replaceAll("\\W", "").trim();
	}

	/**
	 * 获取汉字串拼音，英文字符不变
	 * 
	 * @param chinese 汉字串
	 * @return 汉语拼音
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getFullSpell(String chinese) throws BadHanyuPinyinOutputFormatCombination {
		StringBuffer buffer = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				buffer.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
			} else {
				buffer.append(arr[i]);
			}
		}
		return buffer.toString();
	}
}