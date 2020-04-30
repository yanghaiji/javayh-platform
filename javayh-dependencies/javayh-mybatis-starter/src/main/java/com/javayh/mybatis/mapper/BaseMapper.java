package com.javayh.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 公用Mybatis接口
 * </p>
 *
 * @author Yang Haiji
 * @version 1.0.0
 * @since 2020-04-16 17:43
 */
public interface BaseMapper<T extends Object> {

    /**
     * <p>
     *     分页查询
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/30
     * @param t
     * @return java.util.List<T>
     */
    List<T> findByPage(T t);

    /**
     * <p>
     *      根据id查找
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/30
     * @param id
     * @return T
     */
    T findById(@Param("id") String id);

    /**
     * <p>
     *      新增
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/30
     * @param t
     * @return int
     */
    @Transactional(
            rollbackFor = {Exception.class}
    )
    int insert(T t);


    /**
     * <p>
     *      批量新增
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/30
     * @param t
     * @return int
     */
    @Transactional(
            rollbackFor = {Exception.class}
    )
    int batchInsert(List<T> t);


    /**
     * <p>
     *      修改
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/30
     * @param t
     * @return int
     */
    @Transactional(
            rollbackFor = {Exception.class}
    )
    int update(T t);

    /**
     * <p>
     *    根据id删除
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/30
     * @param id
     * @return int
     */
    @Transactional(
            rollbackFor = {Exception.class}
    )
    int deleteById(@Param("id") String id);
}
