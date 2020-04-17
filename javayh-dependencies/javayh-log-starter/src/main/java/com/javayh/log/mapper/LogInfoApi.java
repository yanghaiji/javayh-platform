package com.javayh.log.mapper;

import com.alibaba.druid.support.monitor.dao.MonitorDaoJdbcImpl;
import com.javayh.log.entity.OperationLog;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Yang Haiji
 * @version 1.0.0
 * @since 2020-04-17
 */
public class LogInfoApi implements LogMapper{

    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<OperationLog> findByPage(OperationLog t) {
        return null;
    }

    @Override
    public int batchInsert(List<OperationLog> t) {
        add(t);
        return 1;
    }

    private void add(List<OperationLog> t){
        jdbcTemplate.execute(
            "insert into sys_log_info" +
                    "(" +
                    "id," +
                    "method," +
                    "create_time as createTime," +
                    "level," +
                    "args," +
                    "userId," +
                    "user_name as userName," +
                    "runtime as runTime," +
                    "describe," +
                    "caller_ip as callerIp," +
                    "localHost_ip as localHostIp," +
                    "mode" +
                    ")" +
                    "values" +
                    " <foreach collection=\"list\" item=\"item\" separator=\",\">" +
                    "(" +
                    "<if test=\"item.id != null  and item.id != '' \">" +
                    "#{item.id}" +
                    "</if>" +
                    "<if test=\"item.method != null  and item.method != '' \">" +
                    ",#{item.method}" +
                    "</if>" +
                    " <if test=\"item.createTime != null  and item.createTime != '' \">" +
                    ",#{item.createTime}" +
                    "</if>" +
                    " <if test=\"item.level != null  and item.level != '' \">" +
                    ",#{item.level}" +
                    "</if>" +
                    " <if test=\"item.args != null  and item.args != '' \">" +
                    ",#{item.args}" +
                    "</if>" +
                    " <if test=\"item.userId != null  and item.userId != '' \">" +
                    ",#{item.userId}" +
                    "</if>" +
                    " <if test=\"item.userName != null  and item.userName != '' \">" +
                    ",#{item.userName}" +
                    "</if>" +
                    " <if test=\"item.runTime != null  and item.runTime != '' \">" +
                    ",#{item.runTime}" +
                    "</if>" +
                    " <if test=\"item.describe != null  and item.describe != '' \">" +
                    ",#{item.describe}" +
                    "</if>" +
                    " <if test=\"item.callerIp != null  and item.callerIp != '' \">" +
                    ",#{item.callerIp}" +
                    "</if>" +
                    " <if test=\"item.localHostIp != null  and item.localHostIp != '' \">" +
                    ",#{item.localHostIp}" +
                    "</if>" +
                    " <if test=\"item.mode != null  and item.mode != '' \">" +
                    " ,#{item.mode}" +
                    " </if>" +
                    "" +
                    ")" +
                    " </foreach>"
        );
    }

}
