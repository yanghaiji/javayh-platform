package com.javayh.common.constant;

/**
 * <p>
 * 加密常量
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-25 17:34
 */
public interface EncryptConstantUtils {

	String ALGORITHM_AES = "AES";

	String INSTANCE = "AES/ECB/PKCS5Padding";

	String FILL_STR = "0";

	String SUFFIX_SHA = "20200202";

	String SUFFIX_AES = "2020020220";

	char[] HEXADECIMAL = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f' };

	char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f', 'g' };

	char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F', 'G' };

	String MD5 = "MD5";

	String KEY_ALGORITHM = "RSA";

	String PUBLIC_KEY = "RSAPublicKey";

	String PRIVATE_KEY = "RSAPrivateKey";

	String ALGORITHM = "RSA/ECB/PKCS1Padding";

	String SHA = "SHA-256";

}
