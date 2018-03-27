package com.risenb.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.risenb.constant.StringPool;

public class StringUtil extends jodd.util.StringUtil {

	/**
	 * 判断字符串是否为空，对象为null或者长度为0都返回true
	 * 
	 * @param string
	 *            要判断的字符串
	 * @return
	 */
	public static boolean isEmpty(final CharSequence string) {
		return ((string == null) || (string.length() == 0));
	}

	/**
	 * 判断字符串是否不为空，字符串对象不为null并且长度不为0则返回true
	 * 
	 * @param string
	 *            要判断的字符串
	 * @return
	 */
	public static boolean isNotEmpty(final CharSequence string) {
		return string != null && string.length() > 0;
	}

	/**
	 * 用特定字符串替换字符串中的空格,只能替换半角空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param replacement
	 *            设置的特定的字符
	 * @return 处理后的字符串
	 */
	public static String replaceSpace(String str, String replacement) {
		return str.replaceAll("[\\p{Space}]+", replacement);
	}

	/**
	 * 获取字符串的字节个数
	 * 
	 * @param str
	 * @return
	 */
	public static int getByteLength(String str) {
		int len = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			int highByte = c >>> 8;
			len += highByte == 0 ? 1 : 2;
		}
		return len;
	}

	/**
	 * 将字符串数组中元素用指定符号连接成字符串
	 * 
	 * @param strArr
	 *            字符串数组
	 * @param delim
	 *            连接符
	 * @return
	 */
	public static String combineStringArray(String[] strArr, String delim) {
		if (strArr == null) {
			throw new RuntimeException("String array is null!....");
		}
		int length = strArr.length - 1;
		if (delim == null) {
			delim = "";
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(strArr[i]);
			result.append(delim);
		}
		result.append(strArr[length]);
		return result.toString();
	}

	/**
	 * 判断字符串数组中是否包含特定字符串
	 * 
	 * @param strArr
	 *            字符串数组
	 * @param str
	 *            要判断的字符串
	 * @param caseSensitive
	 *            是否大小写敏感
	 * @return
	 */
	public static boolean contains(String[] strArr, String str, boolean caseSensitive) {
		if (strArr == null) {
			throw new RuntimeException("String array is null!....");
		}
		if (str == null) {
			throw new RuntimeException(StringPool.MSG_EMPTY);
		}
		for (int i = 0; i < strArr.length; i++) {
			if (caseSensitive == true) {
				if (strArr[i].equals(str)) {
					return true;
				}
			} else {
				if (strArr[i].equalsIgnoreCase(str)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断字符串数组中是否包含特定字符串默认是大小写不敏感
	 * 
	 * @param strArr
	 *            字符串数组
	 * @param str
	 *            要判断的字符串
	 * @return
	 */
	public static boolean containsIgnoreCase(String[] strArr, String str) {
		return contains(strArr, str, false);
	}

	/**
	 * 反转字符串
	 * 
	 * @param str
	 *            源字符串
	 * @return
	 */
	public static String reverse(String str) {
		if (str == null) {
			throw new RuntimeException(StringPool.MSG_EMPTY);
		}
		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * 代码转换 从srcCode转换为destCode
	 * 
	 * @param srcCode
	 *            原编码
	 * @param destCode
	 *            目标编码
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertEncode(String srcEncode, String destEncode, String str) throws UnsupportedEncodingException {
		if (str == null) {
			throw new RuntimeException(StringPool.MSG_EMPTY);
		}
		return new String(str.getBytes(srcEncode), destEncode);
	}

	/**
	 * @param strSrc
	 *            要进行替换操作的字符串
	 * @param strOld
	 *            要查找的字符串
	 * @param strNew
	 *            要替换的字符串
	 * @return 替换后的字符串
	 * 
	 */
	public static final String replace(String strSrc, String strOld, String strNew) {
		if (strSrc == null || strOld == null || strNew == null)
			throw new RuntimeException(StringPool.MSG_EMPTY);
		int i = 0;
		if (strOld.equals(strNew)) // 避免新旧字符一样产生死循环
			return strSrc;
		if ((i = strSrc.indexOf(strOld, i)) >= 0) {
			char[] arr_cSrc = strSrc.toCharArray();
			char[] arr_cNew = strNew.toCharArray();
			int intOldLen = strOld.length();
			StringBuffer buf = new StringBuffer(arr_cSrc.length);
			buf.append(arr_cSrc, 0, i).append(arr_cNew);
			i += intOldLen;
			int j = i;
			while ((i = strSrc.indexOf(strOld, i)) > 0) {
				buf.append(arr_cSrc, j, i - j).append(arr_cNew);
				i += intOldLen;
				j = i;
			}
			buf.append(arr_cSrc, j, arr_cSrc.length - j);
			return buf.toString();
		}
		return strSrc;
	}

	/**
	 * @param inputString
	 *            原始字符串
	 * @param character
	 *            要查找的字符 计算指定字符出现的次数
	 */
	public static int count(String inputString, char character) {
		if (inputString == null) {
			throw new RuntimeException(StringPool.MSG_EMPTY);
		}
		int n = 0;
		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) == character)
				n++;
		}
		return n;
	}

	/**
	 * 把null值和""值转换成&nbsp; 主要应用于页面表格格的显示
	 * 
	 * @param str
	 *            要进行处理的字符串
	 * @return 转换后的字符串
	 */
	public static String str4Table(String str) {
		if (str == null)
			return "&nbsp;";
		else if (str.equals(""))
			return "&nbsp;";
		else
			return str;
	}

	/**
	 * @Description: [全角,半角及另类的空白字符]处理为一个半角空格
	 * 
	 * @param s
	 *            要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String normalizingSpace(String str) {
		Pattern p = null;
		Matcher m = null;
		if (null != str && !"".equals(str)) {
			p = Pattern.compile(StringPool.REGEX_SPACE);
			m = p.matcher(str);
		}
		return m.replaceAll(" ");
	}

	/**
	 * 去除字符串中所包含的空格（包括:空格(全角，半角)、换页符等） 这里有一个神奇的" "字符
	 * 
	 * @param s
	 *            要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String removeAllBlank(String str) {
		String result = "";
		if (null != str && !"".equals(str)) {
			result = str.replaceAll("[　*|　*| *| *| *|//s*]*", "");
		}
		return result;
	}

	/**
	 * 半角字符->全角字符转换 只处理空格，!到˜之间的字符，忽略其他
	 * 
	 * @param src
	 *            要处理的字符串
	 * @return 处理后的字符串数组
	 */
	public static String bj2qj(String src) {
		if (src == null) {
			throw new RuntimeException(StringPool.MSG_EMPTY);
		}
		StringBuilder buf = new StringBuilder(src.length());
		char[] ca = src.toCharArray();
		for (int i = 0; i < ca.length; i++) {
			if (ca[i] == StringPool.DBC_SPACE) { // 如果是半角空格，直接用全角空格替代
				buf.append(StringPool.SBC_SPACE);
			} else if ((ca[i] >= StringPool.DBC_CHAR_START) && (ca[i] <= StringPool.DBC_CHAR_END)) { // 字符是!到~之间的可见字符
				buf.append((char) (ca[i] + StringPool.CONVERT_STEP));
			} else { // 不对空格以及ascii表中其他可见字符之外的字符做任何处理
				buf.append(ca[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * 全角字符->半角字符转换 只处理全角的空格，全角！到全角～之间的字符，忽略其他
	 * 
	 * @param src
	 *            要处理的字符串
	 * @return 处理后的字符串数组
	 */
	public static String qj2bj(String src) {
		if (isEmpty(src)) {
			throw new RuntimeException(StringPool.MSG_EMPTY);
		}
		StringBuilder buf = new StringBuilder(src.length());
		char[] ca = src.toCharArray();
		for (int i = 0; i < src.length(); i++) {
			if (ca[i] >= StringPool.SBC_CHAR_START && ca[i] <= StringPool.SBC_CHAR_END) {
				// 如果位于全角！到全角～区间内
				buf.append((char) (ca[i] - StringPool.CONVERT_STEP));
			} else if (ca[i] == StringPool.SBC_SPACE) {
				// 如果是全角空格
				buf.append(StringPool.DBC_SPACE);
			} else {
				// 不处理全角空格，全角！到全角～区间外的字符
				buf.append(ca[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * 将数组中的每个元素两端加上给定的符号
	 * 
	 * @param aSource
	 *            源数组
	 * @param sChar
	 *            符号
	 * @return 处理后的字符串数组
	 */
	public static String[] arrayAddSign(String[] aSource, String sChar) {
		String aReturn[] = new String[aSource.length];
		for (int i = 0; i < aSource.length; i++) {
			aReturn[i] = sChar + aSource[i] + sChar;
		}
		return aReturn;
	}

	/**
	 * 将数组中的元素连成一个以给定字符分隔的字符串
	 * 
	 * @param aSource
	 *            源数组
	 * @param sChar
	 *            分隔符
	 * @return 字符串
	 */
	public static <T> String arrayToString(T[] aSource, String sChar) {
		if (aSource == null) {
			throw new RuntimeException("String array length is zero!....");
		}

		String sReturn = "";
		for (int i = 0; i < aSource.length; i++) {
			if (i > 0) {
				sReturn += sChar;
			}
			sReturn += aSource[i];
		}
		return sReturn;
	}

	/**
	 * 将两个对象数组中的所有元素连结为一个对象数组
	 * 
	 * @param array1
	 *            源字符串数组1
	 * @param array2
	 *            源字符串数组2
	 * @return Object[]
	 */
	public static Object[] arrayAppend(Object[] array1, Object[] array2) {
		int iLen = 0;
		Object aReturn[] = null;
		if (array1 == null) {
			array1 = new Object[0];
		}
		if (array2 == null) {
			array2 = new Object[0];
		}
		iLen = array1.length;
		aReturn = new Object[iLen + array2.length];
		// 将第一个对象数组的元素加到结果数组中
		for (int i = 0; i < iLen; i++) {
			aReturn[i] = array1[i];
		}
		// 将第二个对象数组的元素加到结果数组中
		for (int i = 0; i < array2.length; i++) {
			aReturn[iLen + i] = array2[i];
		}
		return aReturn;
	}

	/**
	 * 拆分以给定分隔符分隔的字符串,并存入字符串数组中
	 * 
	 * @param sSource
	 *            源字符串
	 * @param sChar
	 *            分隔符
	 * @return String[]
	 */
	public static String[] strToArray(String sSource, String sChar) {
		if (sSource == null) {
			throw new RuntimeException(StringPool.MSG_EMPTY);
		}
		String aReturn[] = null;
		StringTokenizer st = null;
		st = new StringTokenizer(sSource, sChar);
		int i = 0;
		aReturn = new String[st.countTokens()];
		while (st.hasMoreTokens()) {
			aReturn[i] = st.nextToken();
			i++;
		}
		return aReturn;
	}

	/**
	 * 拆分以给定分隔符分隔的字符串,并存入整型数组中
	 * 
	 * @param sSource
	 *            源字符串
	 * @param sChar
	 *            分隔符
	 * @return int[]
	 */
	public static int[] strToArray(String sSource, char sChar) {
		int aReturn[] = null;
		StringTokenizer st = null;
		st = new StringTokenizer(sSource, String.valueOf(sChar));
		int i = 0;
		aReturn = new int[st.countTokens()];
		while (st.hasMoreTokens()) {
			aReturn[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		return aReturn;
	}

	/**
	 * 将以逗号分隔的字符串的每个元素加上单引号 如： 1000,1001,1002 --> '1000','1001','1002'
	 * 
	 * @param sSource
	 *            源串
	 * @return String 处理后的字符串
	 */
	public static String addMark(String sSource) {
		String sReturn = "";
		StringTokenizer st = null;
		st = new StringTokenizer(sSource, ",");
		if (st.hasMoreTokens()) {
			sReturn += "'" + st.nextToken() + "'";
		}
		while (st.hasMoreTokens()) {
			sReturn += "," + "'" + st.nextToken() + "'";
		}
		return sReturn;
	}

	/**
	 * 判断一个字符串是否是english
	 * 
	 * @param word
	 *            要判断的字符串
	 * @return boolean 返回类型
	 */
	public static boolean isEnglish(String word) {
		if (word == null) {
			throw new RuntimeException(StringPool.MSG_EMPTY);
		}
		int length = word.length();
		char c;
		for (int i = 0; i < length; i++) {
			c = word.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= StringPool.MIN_LOWER && c <= StringPool.MAX_LOWER) || (c >= StringPool.MIN_UPPER && c <= StringPool.MAX_UPPER)
					|| (c >= StringPool.MIN_UPPER_E && c <= StringPool.MAX_UPPER_E)) {
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断一个字符串是否是数字
	 * 
	 * @param word
	 *            要判断的字符串
	 * @return boolean 返回类型
	 */
	public static boolean isNumber(String word) {
		char c = 0;
		int len = word.length();
		for (int i = 0; i < len; i++) {
			c = word.charAt(i);
			if ((c >= '0' && c <= '9') || c >= StringPool.MIN_UPPER_N && c <= StringPool.MAX_UPPER_N || c == '.') {
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 根据Unicode编码完美的判断中文汉字和符号)
	 * 
	 * @param c
	 *            要判断的字符
	 * @return boolean 返回类型
	 */
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	/**
	 * 完整的判断中文汉字和符号
	 * 
	 * @param strName
	 *            要判断的字符
	 * @return boolean 返回类型
	 */
	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!isChinese(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 通过中文字符区间判断是否为中文,通用区间来判断中文也不非常精确，因为有些中文的标点符号利用区间判断会得到错误的结果,而且利用区间判断中文效率也并不高)
	 * 
	 * @param chineseStr
	 *            要判断的字符
	 * @return boolean 返回类型
	 */
	public static boolean isChineseCharacter(String chineseStr) {
		char[] charArray = chineseStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (!((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstToUpperCase(String str) {
		return isEmpty(str) ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 替换字符中的标点符号
	 * 
	 * @param str
	 * @param replacement
	 * @return
	 */
	public static String replacePunct(String str, String replacement) {
		return str.replaceAll("[\\p{Punct}\\pP]+", replacement);
	}

	/**
	 * 清除符号及空格(只清除半角空格)
	 * 
	 * @param str
	 * @return
	 */
	public static String clear(String str) {
		return replaceSpace(replacePunct(str, ""), "");
	}

	/**
	 * 截取指定长度的字符串
	 * 
	 * @param str
	 * @param end
	 * @return
	 */
	public static String subString(String str, int end) {
		if (isEmpty(str) || str.length() < end) {
			return str;
		}
		return str.substring(0, end);
	}

	/**
	 * 截掉最后的字符
	 * 
	 * @param sourceStr
	 * @param endStr
	 * @return
	 */
	public static String replaceEndStr(String sourceStr, String endStr) {
		if (sourceStr.endsWith(endStr)) {
			return sourceStr.substring(0, sourceStr.lastIndexOf(endStr));
		}
		return sourceStr;
	}

	/**
	 * trim全角空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (isEmpty(str)) {
			return str;
		}
		String regStartSpace = "^[　 ]*";
		String regEndSpace = "[　 ]*$";
		String strDelSpace = str.replaceAll(regStartSpace, "").replaceAll(regEndSpace, "");

		return strDelSpace.trim();
	}

	/**
	 * 清除HTML标签
	 * 
	 * @param strÏ要处理的html字符串
	 * @return
	 */
	public static String clearHtml(String str) {
		return str.replaceAll("<[^>]*>", "");
	}

	/**
	 * 移除最后一个字符
	 * 
	 * @param str
	 * @return
	 */
	public static String removeLastCharacter(String str) {
		return str.substring(0, str.length() - 1);
	}

	/**
	 * 判断src使用逗号分割后是否包含str
	 * 
	 * @param src
	 * @param str
	 * @return
	 */
	public static boolean containWithComma(String src, String str) {
		if (src == null) {
			throw new RuntimeException(StringPool.MSG_EMPTY);
		}
		return Arrays.asList(src.split(", ")).contains(str) || Arrays.asList(src.split(",")).contains(str);
	}

	/**
	 * 检查对象是否为数字型字符串,包含负数开头的。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if (length < 1)
			return false;

		int i = 0;
		if (length > 1 && chars[0] == '-')
			i = 1;

		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	/**
	 * 过滤不可见字符
	 */
	public static String stripNonValidXMLCharacters(String input) {
		if (input == null || ("".equals(input)))
			return "";
		StringBuilder out = new StringBuilder();
		char current;
		for (int i = 0; i < input.length(); i++) {
			current = input.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}

	/**
	 * 获取32位uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
}
