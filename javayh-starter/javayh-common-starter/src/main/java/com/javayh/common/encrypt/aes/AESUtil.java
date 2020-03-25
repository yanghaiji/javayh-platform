package com.javayh.common.encrypt.aes;

import com.javayh.common.util.ConstantUtil;
import com.javayh.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * <p>
 *      加密
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-16
 */
@Slf4j
public class AESUtil {
    
    private static SecretKeySpec key;
    private static Cipher encCipher = null;
    private static  Cipher  decCipher = null;

    /**
     * <p>
     *       加密
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/16
     * @param encData
     * @param iv
     * @return java.lang.String
     */
    public static String encryptDataKey(String encData,String iv){
        iv = StringUtils.setSuffix(iv, ConstantUtil.SUFFIX_AES);
        byte result[] = new byte[0];
        try {
            result = encCipher(iv).doFinal(encData.getBytes());
        } catch (IllegalBlockSizeException e) {
            log.error(" IllegalBlockSizeException is error {}", e.getMessage());
        } catch (BadPaddingException e) {
            log.error(" BadPaddingException is error {}", e.getMessage());
        }
        return new String(Base64.encode(result));
    }

    /**
     * <p>
     *       解密
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/18
     * @param decryptDataK
     * @param iv
     * @return java.lang.String
     */
    public static  String decryptDataKey(String decryptDataK,String iv){
        iv = StringUtils.setSuffix(iv,ConstantUtil.SUFFIX_AES);
        byte result[] = new byte[0];
        try {
            result = decCipher(iv).doFinal(Base64.decode(decryptDataK.getBytes()));
        }  catch (IllegalBlockSizeException e) {
            log.error(" IllegalBlockSizeException is error {}", e.getMessage());
        } catch (BadPaddingException e) {
            log.error(" BadPaddingException is error {}", e.getMessage());
        }
        return new String(result);
    }


    private static Cipher encCipher(String iv){
        byte[] enCodeFormat = Arrays.copyOf(iv.getBytes(), 16);
        key = new SecretKeySpec(enCodeFormat, "AES");
        try {
            encCipher = Cipher.getInstance(ConstantUtil.INSTANCE);
            encCipher.init(Cipher.ENCRYPT_MODE, key);
            return  encCipher;
        }  catch (NoSuchAlgorithmException e) {
            log.error(" NoSuchAlgorithmException is error {}", e.getMessage());
        } catch (NoSuchPaddingException e) {
            log.error(" NoSuchPaddingException is error {}", e.getMessage());
        } catch (InvalidKeyException e) {
            log.error(" InvalidKeyException is error {}", e.getMessage());
        }
        return encCipher;
    }

    private static Cipher decCipher(String iv){
        byte[] enCodeFormat = Arrays.copyOf(iv.getBytes(), 16);
        key = new SecretKeySpec(enCodeFormat, "AES");
        try {
            decCipher = Cipher.getInstance(ConstantUtil.INSTANCE);
            decCipher.init(Cipher.DECRYPT_MODE, key);
            return  decCipher;
        }  catch (NoSuchAlgorithmException e) {
            log.error(" NoSuchAlgorithmException is error {}", e.getMessage());
        } catch (NoSuchPaddingException e) {
            log.error(" NoSuchPaddingException is error {}", e.getMessage());
        } catch (InvalidKeyException e) {
            log.error(" InvalidKeyException is error {}", e.getMessage());
        }
        return decCipher;
    }

}
