/**
 * 
 */
package com.chinarewards.gwt.license.util;

/**
 * @author Cream
 * @since 0.2.0 2011-01-07
 */
public class StringUtil {

	/**
	 * 
	 * @param str
	 * @return true - when input string is {@code null} or equals to "".
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str) || "null".equals(str);
	}

	/**
	 * Returns {@code null} when input string is null.
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return null == str ? null : str.trim();
	}

	/**
	 * Truncating string.
	 * 
	 * @param str
	 * @param length
	 *            - length of output string.
	 * @return Returns "" if input <code>null</code>.
	 */
	public static String truncate(String str, int length) {
		if (isEmpty(str)) {
			return "";
		}
		String result = null;
		if (str.length() > length) {
			result = str.substring(0, length);
		} else {
			result = str;
		}
		return result;
	}

	/**
	 * The special Object must implement toString()
	 * 
	 * @param obj
	 * @return
	 */
	public static String valueOf(Object obj) {
		return (obj == null) ? "" : obj.toString();
	}

	public static Integer valueOf(String str) {

		if (str == null || "".equals(str.trim())) {
			return null;
		} else {
			try {
				int d = Integer.parseInt(str.trim());
				return d;
			} catch (Exception e) {
				return new Integer(-1);
			}
		}

	}

	public static void printToTxt(String file, String content) {
		// 以下将导致mvn install 失败
		// try {
		// PrintWriter logWriter = new PrintWriter(new BufferedWriter(
		// new FileWriter(file, true)), true);
		// logWriter.println(content);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * @param fullString
	 *            原字符串
	 * @param cellString
	 *            需要拼接的
	 * @param appString
	 *            连接符
	 * 
	 * */
	public static String appendString(String fullString, String cellString,
			String appString) {
		if (!isEmpty(fullString)) {
			if (containsExistString(fullString, cellString)) {
				// 如已存在，则不追加
			} else {
				fullString =fullString+appString+cellString;
			}
		} else {
			fullString = cellString;
		}
		
		return fullString;
	}

	public static boolean containsExistString(String fullString,
			String cellString) {
		if (!isEmpty(fullString) && !isEmpty(cellString)) {
			int flag = fullString.indexOf(cellString);

			if (flag >= 0) { // 大于0 则表示存在 为-1 则表示不存在
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 删除cellString
	 */
	public static String removeCellString(String fullString, String cellString) {
		String[] orderArray = getSplitString(fullString, ",");
		int orderLength = orderArray.length;

		for (int i = 0; i < orderLength; i++) {
			String tempStr = orderArray[i];
			if (tempStr == null || "".equals(tempStr)) {

			} else {
				if (tempStr.equals(cellString)) {
					orderArray = delArrayCellByStr(orderArray, cellString);
					fullString = getStringByStringArray(orderArray, ",");
					orderLength = orderArray.length;
				}
			}
		}

		return fullString;
	}

	public static String getStringByStringArray(String[] array, String splitStr) {
		String returnStr = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null || "".equals(array[i])) {
				returnStr = returnStr + "";
			} else {
				returnStr = returnStr + array[i] + splitStr;
			}
		}

		if (returnStr.lastIndexOf(",") > 0) {
			returnStr = returnStr.substring(0, returnStr.lastIndexOf(splitStr));
		}
		return returnStr;
	}

	public static String[] getSplitString(String strSrc, String splitStr) {
		if (strSrc != null) {
			String splitString[] = strSrc.split(splitStr);
			return splitString;
		} else {
			return null;
		}

	}

	/**
	 * 删除字符数组中指定的元素
	 * 
	 * @param String
	 *            [] array
	 * @param String
	 *            para 需要删除的元素
	 */
	public static String[] delArrayCellByStr(String[] array, String para) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].equals(para)) {
				array[i] = "";
			}
		}
		return array;
	}

	public static void main(String[] args) {
		System.out.println("=======test");
		String str = "";
		str = appendString(str, "123", ",");
		System.out.println(str);
		str = appendString(str, "456", ",");
		System.out.println(str);
		str = appendString(str, "789", ",");
		System.out.println(str);
		str = removeCellString(str, "456");
		System.out.println(str);

	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}
}
