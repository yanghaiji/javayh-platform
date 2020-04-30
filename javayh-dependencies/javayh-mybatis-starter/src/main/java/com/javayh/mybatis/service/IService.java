package com.javayh.mybatis.service;

import com.google.common.collect.Lists;
import com.javayh.mybatis.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *      公用service
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-30
 */
@Slf4j
public abstract class IService<T> {

    @Autowired(required = false)
    private BaseMapper baseMapper;

    /**
     * <p>
     *       数据分片插入
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/30
     * @param t
     * @param size
     * @return int
     */
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public int batchInsert(List<T> t,int size){
        if (CollectionUtils.isEmpty(t) || size < 0) {
            log.error("The parameter is abnormal please make sure the parameter is correct t{},size {}",t,size);
            return 1;
        }
        size = size == 0 ? 500 :size;
        List objects = Lists.newArrayList();
        objects.add(t);
        List<List<T>> list = Lists.partition(objects,size);
        try {
            list.forEach(tt -> baseMapper.batchInsert(tt));
        }catch (Exception e){
            log.info("Abnormal handling of adding new shards in batch{}",e.getMessage());
            return 1;
        }
        return 0;
    }

    /**
     * <p>
     *       数据分片插入
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
    public int batchInsert(List<T> t){
        if (CollectionUtils.isEmpty(t)) {
            log.error("The parameter is abnormal please make sure the parameter is correct t{}",t);
            return 1;
        }
       return batchInsert(t,500);
    }

}
