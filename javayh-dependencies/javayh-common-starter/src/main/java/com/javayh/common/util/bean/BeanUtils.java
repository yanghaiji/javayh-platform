package com.javayh.common.util.bean;

import com.javayh.common.util.IAction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * <p>
 *      copy
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-20
 */
@Slf4j
public class BeanUtils {

    /**
     * <p>
     *       对象Copy
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/20
     * @param source    源对象
     * @param target    目标对象
     * @return T
     */
    public static <O, T> T copyProperties(O source, Class<T> target) {
        T t = baseMapper(source, target);
        return t;
    }

    /**
     * <p>
     *       对象Copy
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/20
     * @param source    源对象
     * @param target    目标对象
     * @param action    其他操作
     * @return T
     */
    public static <O, T> T copyProperties(O source, Class<T> target, IAction<T> action) {
        T t = baseMapper(source, target);
        action.run(t);
        return t;
    }

    private static <O, T> T baseMapper(O source, Class<T> target) {
        if(!ObjectUtils.allNotNull(source)){
            log.error("源对象为空");
            return null;
        }
        T instance = null;
        try {
            instance = target.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("创建对象异常" + e.getMessage());
        }
        org.springframework.beans.BeanUtils.copyProperties(source,instance);
        return instance;
    }
}
