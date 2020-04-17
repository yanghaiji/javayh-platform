package com.javayh.log.mapper;

import com.javayh.log.entity.OperationLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 日志持久化
 * </p>
 * @author Yang Haiji
 * @version 1.0.0
 * @since 2020-04-16 23:07
 */
public interface LogMapper {

    /**
     * <p>
     *       分页查询
     * </p>
     * @version 1.0.0
     * @author Dylan
     * @since 2020/4/16
     * @param t
     * @return java.util.List<com.javayh.log.entity.OperationLog>
     */
    List<OperationLog> findByPage(OperationLog t);


    /**
     * <p>
     *       批量新增
     * </p>
     * @version 1.0.0
     * @author Dylan
     * @since 2020/4/16
     * @param t
     * @return int
     */
    int batchInsert(List<OperationLog> t);
}
