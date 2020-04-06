package com.javayh.common.encrypt.rsa;

import com.javayh.common.constant.EncryptConstantUtils;
import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 生成公钥私钥
 * </p>
 *
 * @version 1.0.0
 * @author zhao-baolin
 * @since 2020/3/5
 */
public class RSAEncrypt {

	public class Keys {

	}

	// 获得公钥
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		// 获得map中的公钥对象 转为key对象
		Key key = (Key) keyMap.get(EncryptConstantUtils.PUBLIC_KEY);
		// byte[] publicKey = key.getEncoded();
		// 编码返回字符串
		return encryptBASE64(key.getEncoded());
	}

	// 获得私钥
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		// 获得map中的私钥对象 转为key对象
		Key key = (Key) keyMap.get(EncryptConstantUtils.PRIVATE_KEY);
		// byte[] privateKey = key.getEncoded();
		/* 编码返回字符串 */
		return encryptBASE64(key.getEncoded());
	}

	/**
	 * <p>
	 * 编码返回字符串
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/5
	 * @param key
	 * @return java.lang.String
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return Base64.encodeBase64String(key);
	}

	/**
	 * <p>
	 * map对象中存放公私钥
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/5
	 * @param
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 */
	public static Map<String, Object> initKey() throws Exception {
		/* 获得对象 KeyPairGenerator 参数 RSA 256个字节 */
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(EncryptConstantUtils.KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		/* 通过对象 KeyPairGenerator 获取对象KeyPair */
		KeyPair keyPair = keyPairGen.generateKeyPair();
		/* 通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey */
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		/* 公私钥对象存入map中 */
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(EncryptConstantUtils.PUBLIC_KEY, publicKey);
		keyMap.put(EncryptConstantUtils.PRIVATE_KEY, privateKey);
		return keyMap;
	}

	// public static void main(String[] args) {
	// Map<String, Object> keyMap;
	// try {
	// keyMap = initKey();
	// String publicKey = getPublicKey(keyMap);
	// System.out.println(publicKey);
	// String privateKey = getPrivateKey(keyMap);
	// System.out.println(privateKey);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

}
