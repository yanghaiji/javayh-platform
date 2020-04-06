package com.javayh.common.exception.controller;

import com.javayh.common.exception.BaseException;

/**
 * <p>
 * controller 异常
 * </p>
 *
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/1
 */
public class ControllerException extends BaseException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1412104290896291466L;

	public ControllerException(String msg) {
		super(msg);
	}

	public ControllerException(Exception e) {
		this(e.getMessage());
	}

}
