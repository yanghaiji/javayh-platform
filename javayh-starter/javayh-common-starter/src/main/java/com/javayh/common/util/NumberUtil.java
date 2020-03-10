package com.javayh.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
public class NumberUtil<T> {
    private static final String DOUBLE_PATTERN_AFTER_POINT_3 = "#.###";

    private static final DecimalFormat decimalFormat = new DecimalFormat(DOUBLE_PATTERN_AFTER_POINT_3);
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
        if(num != 0 && offset != 0 && factor > 0 && offset >= factor){
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

    /**
     * <p>
     *       计算百分比
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/9
     * @param dividend  被除数
     * @param divisor   除数
     * @return double
     */
    public static Double distribution(Double dividend ,Double divisor){
        if(dividend != null && divisor != null && dividend != 0 &&divisor != 0){
            Double result  = dividend / divisor;
            return Double.valueOf(decimalFormat.format(result));
        }
        return 0.0;
    }

    /**
     * <p>
     *       计算百分比
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/9
     * @param a     被除数
     * @param b     除数
     * @param c     需要减去的值
     * @return java.lang.Double
     */
    public static Double divideAndMinusFormat3(Integer a, Integer b, int c) {
        Double d = divide(a, b);
        if (d == null) {
            return d;
        } else {
            return Double.valueOf(decimalFormat.format(d - (double)c));
        }
    }

    /**
     * <p>
     *       百分比计算
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/9
     * @param a
     * @param b
     * @return java.lang.Double
     */
    public static Double divide(Integer a, Integer b) {
        if (a != null && b != null && b != 0) {
            double d = (double)a * 1.0D / (double)b;
            return d;
        } else {
            return null;
        }
    }

    /**
     * <p>
     *       BigDecimal计算
     *       建议 创建BigDecimal时传入字符串，以免精度出现问题，
     *       如 new BigDecimal("2020.02")
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/9
     * @param dividend
     * @param divisor
     * @return java.lang.Double
     */
    public static Double distributionBigFormat(BigDecimal dividend , BigDecimal divisor){
        if (dividend.doubleValue() != 0 && divisor.doubleValue() != 0 ){
            BigDecimal divide = dividend.divide(divisor, RoundingMode.HALF_UP);
            return Double.valueOf(decimalFormat.format(divide));
        }
        return 0.0;
    }

    /**
     * <p>
     *       取两个数的最大最小值
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/10
     * @param a
     * @param b
     * @param factor    负载因子
     * @return java.lang.Integer
     */
    public static Integer max(Integer a ,Integer b,int factor){
        Integer max = 0;
        if(null != a && null != b && factor == 1){
            max = Math.max(a, b);
            return max;
        }else if (null != a && null != b && factor == 0){
            max = Math.min(a, b);
            return max;
        }
        return max;
    }



}
