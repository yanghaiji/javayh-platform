package com.javayh.mybatis.exception;

import java.sql.SQLException;

/**
 * <p>
 * mybatis sql注入异常
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-05 11:51
 */
public class MybatisInjectionException extends Exception {

	private static final long serialVersionUID = 7859712770754900356L;

	public MybatisInjectionException(String msg) {
		super(msg);
	}

	public MybatisInjectionException(Exception e) {
		this(e.getMessage());
	}

}
