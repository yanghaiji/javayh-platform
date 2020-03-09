package com.javayh.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     数据处理
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-09 14:07
 */
public class NumberUtil {

    /**
     * <p>
     *       根据给定的数据进行数据分配
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/9
     * @param num       源数据
     * @param offset    分成的分数
     * @param factor    放在的位置
     * @return java.util.List<java.lang.Integer>
     */
    public static List<Integer> distribution(Integer num, Integer offset, int factor){
        if(num > 0 && offset > 0 && factor > 0 && offset >= factor){
            Integer model = num % offset;
            Integer rem = num / offset;
            List<Integer> lists = new ArrayList<>(offset);
            Integer rand = (int)Math.random() +factor ;
            for (int j = 0; j < offset ; j++) {
                lists.add(rem);
            }
            lists.set(rand,rem + model);
            return lists;
        }
        return null;
    }
}
