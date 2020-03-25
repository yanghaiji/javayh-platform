package com.javayh.common.encrypt.md5;

import com.javayh.common.util.ConstantUtil;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-24 21:22
 */
@Slf4j
public class MD5Util {

    /**
     * <p>
     *       MD5 进行hash取模
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/24
     * @param s
     * @return java.lang.String
     */
    public static String hash32(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance(ConstantUtil.MD5);
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = ConstantUtil.HEXADECIMAL[byte0 >>> 4 & 0xf];
                str[k++] = ConstantUtil.HEXADECIMAL[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
           log.error("MD5 hash32 Exception {}",e.getMessage());
            return null;
        }
    }
}
