package com.javayh.common.exception.service;

import com.javayh.common.exception.BaseException;

/**
 * <p>
 *       service处理异常
 *       controller处理
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/1
 */
public class ServiceException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2437160791033393978L;

	public ServiceException(String msg) {
	  super(msg);
	}

	public ServiceException(Exception e){
	  this(e.getMessage());
	}
}
