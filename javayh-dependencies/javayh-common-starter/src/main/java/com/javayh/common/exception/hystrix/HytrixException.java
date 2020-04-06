package com.javayh.common.exception.hystrix;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * <p>
 * feign client 避免熔断异常处理
 * </p>
 *
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/1
 */
public class HytrixException extends HystrixBadRequestException {

	private static final long serialVersionUID = -2437160791033393978L;

	public HytrixException(String msg) {
		super(msg);
	}

	public HytrixException(Exception e) {
		this(e.getMessage());
	}

}
