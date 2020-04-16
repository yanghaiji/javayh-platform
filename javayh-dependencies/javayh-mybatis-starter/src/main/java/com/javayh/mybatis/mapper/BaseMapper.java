package com.javayh.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

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
     * 分页查询
     */
    List<T> findByPage(T t);

    /**
     * 根据id查找
     * @return
     */
    T findById(@Param("id") String id);

    /**
     * 新增
     */
    int insert(T t);


    /**
     * 新增
     */
    int batchInsert(List<T> t);


    /**
     * 修改
     */
    int update(T t);

    /**
     * 根据id删除
     */
    int deleteById(@Param("id") String id);
}
