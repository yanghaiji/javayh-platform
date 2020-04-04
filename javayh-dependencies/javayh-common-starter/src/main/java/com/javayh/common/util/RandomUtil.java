package com.javayh.common.util;

import org.joda.time.DateTime;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 *       流水号生产
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/1
 */
public class RandomUtil {

    private static final ThreadLocalRandom random=ThreadLocalRandom.current();

    /**
     * <p>
     *       生产流水号
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/1
     * @param
     * @return java.lang.String
     */
    public static String generateOrderCode(){
        return  DateTime.now().toString("yyyyMMddHHmmssSS") + generateNumber(4);
    }

    //num为随机数流水号
    public static String generateNumber(final int num){
        StringBuffer sb=new StringBuffer();
        for (int i=1;i<=num;i++){
            sb.append(random.nextInt(9));
        }
        return sb.toString();
        
    }
    
    


    

}