package com.javayh.common.encrypt.sha;

import com.javayh.common.constant.ConstantUtils;
import com.javayh.common.constant.EncryptConstantUtils;
import com.javayh.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-24 9:38
 */
@Slf4j
public class Sha256 {

	/**
	 * <p>
	 * SHA256加密
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/24
	 * @param str 加密
	 * @return java.lang.String
	 */
	public static String getSHA256(String str) {
		str = StringUtils.setSuffix(str, EncryptConstantUtils.SUFFIX_SHA);
		MessageDigest messageDigest;
		String encodestr = "";
		try {
			messageDigest = MessageDigest.getInstance(EncryptConstantUtils.SHA);
			messageDigest.update(str.getBytes(ConstantUtils.UTF));
			encodestr = byte2Hex(messageDigest.digest());
		}
		catch (NoSuchAlgorithmException e) {
			log.error("NoSuchAlgorithmException {}", e.getMessage());
		}
		catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException {}", e.getMessage());
		}
		return encodestr;
	}

	/**
	 * <p>
	 * 将byte转为16进制
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/24
	 * @param bytes
	 * @return java.lang.String
	 */
	private static String byte2Hex(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				stringBuffer.append(EncryptConstantUtils.FILL_STR);
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}

	// public static void main(String[] args) {
	// String code = "123456";
	// System.out.println("code:"+getSHA256(code));
	// long l = System.currentTimeMillis();
	// System.out.println("时间："+l);
	// String instanceKey = DataKeyGenerator.getInstanceKey(256, l);
	// System.out.println("初始化datakey:"+instanceKey);
	// System.out.println("加密datakey"+ AESUtil.encryptDataKey(instanceKey,code));
	// System.out.println("解密datakey"+
	// AESUtil.decryptDataKey(AESUtil.encryptDataKey(instanceKey,code),code));
	// }

}
