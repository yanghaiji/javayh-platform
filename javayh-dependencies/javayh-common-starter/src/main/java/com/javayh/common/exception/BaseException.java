package com.javayh.common.exception;

import com.javayh.common.constant.ConstantUtils;

/**
 * <p>
 * 基础异常
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 21:30
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 7859712770754900356L;

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(Exception e){
        this(e.getMessage());
    }
}
