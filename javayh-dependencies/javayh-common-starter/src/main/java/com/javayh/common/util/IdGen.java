package com.javayh.common.util;

import java.util.UUID;

/**
 * <p>
 * id生成器
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-06 15:55
 */
public class IdGen {

	/**
	 * <p>
	 * uuid
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/6
	 * @param
	 * @return java.lang.String
	 */
	public static String UUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * <p>
	 * 去除 - 的uuid
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/6
	 * @param
	 * @return java.lang.Integer
	 */
	public static String UUIDReplace() {
		return UUID.randomUUID().toString().replaceAll("-", "").trim();
	}

}
