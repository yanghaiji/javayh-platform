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
