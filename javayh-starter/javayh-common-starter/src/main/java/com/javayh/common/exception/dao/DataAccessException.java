package com.javayh.common.exception.dao;


import com.javayh.common.exception.BaseException;

/**
 * <p>
 *     数据库异常
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/1
 */
public class DataAccessException extends BaseException {

	private static final long serialVersionUID = 8325096920926406459L;

	public DataAccessException(String msg) {
		super(msg);
	}

	public DataAccessException(Exception e) {
		this(e.getMessage());
	}
}
