package com.javayh.common.util;

import java.util.Collection;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-09 21:54
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * <p>
	 * 加言处理
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/24
	 * @param str
	 * @return java.lang.String
	 */
	public static String setSuffix(String str, String suffix) {
		return str + suffix;
	}

	public static boolean isEmpty(String str) {
		return isNull(str) || "".equals(str.trim());
	}

	public static boolean isNotEmpty(String cs) {
		return !isEmpty(cs);
	}

	public static boolean isEmpty(Collection<?> coll) {
		return isNull(coll) || coll.isEmpty();
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

}
