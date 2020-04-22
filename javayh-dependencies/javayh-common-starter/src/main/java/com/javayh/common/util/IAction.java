package com.javayh.common.util;

import org.apache.commons.lang3.ObjectUtils;

/**
 * <p>
 *       其他附属操作
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/4/20
 */
@FunctionalInterface
public interface IAction<T> {

    /**
     * <p>
     *       执行器
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/20
     * @param param
     * @return void
     */
    void run(T param);


    static <T> Boolean allNotNull(T param){
        return ObjectUtils.allNotNull(param);
    }
}
