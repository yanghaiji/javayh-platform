package com.javayh.common.encrypt.key;

import com.javayh.common.constant.EncryptConstantUtils;
import com.javayh.common.encrypt.md5.MD5Util;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * <p>
 *      系统生成datakey
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-24 15:40
 */
@Slf4j
public class DataKeyGenerator {

    /**
     * <p>
     *       安全随机种子
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/24
     * @param keySize
     * @param seed
     * @return java.lang.String
     */
    public static String getInstanceKey(int keySize,long seed) {
        String key = "";
        try {
            KeyGenerator generator = KeyGenerator.getInstance(EncryptConstantUtils.ALGORITHM_AES);
            SecureRandom random=new  SecureRandom();
            random.setSeed(seed);
            generator.init(keySize, random);
            SecretKey secretKey=generator.generateKey();
            key= MD5Util.hash32(new String(secretKey.getEncoded()));
        } catch (Exception e) {
            log.error("DataKeyGenerator NoSuchAlgorithmException{}",e.getMessage());
        }
        return key;
    }

}
