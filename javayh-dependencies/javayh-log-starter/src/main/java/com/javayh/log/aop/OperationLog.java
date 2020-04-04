package com.javayh.log.aop;

import lombok.Data;

/**
 * <p>
 * 记录参数
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 23:58
 */
@Data
public class OperationLog {

    /**
     * 唯一id
     */
    private String id;

    /*
     * 调用时间
     */
    private String createTime;
    /**
     * 日志等级
     */
    private Integer level;

    /**
     * 方法名
     */
    private String method;
    /**
     * 参数
     */
    private String args;
    /**
     * 操作人id
     */
    private String userId;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 日志描述
     */
    private String describe;

    /**
     * 方法运行时间
     */
    private Long runTime;

    /**
     * 调用方ip
     */
    private String callerIp;
    /**
     * 本地ip
     */
    private String localHostIp;

}
