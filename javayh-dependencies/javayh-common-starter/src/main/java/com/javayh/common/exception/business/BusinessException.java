package com.javayh.common.exception.business;

import com.javayh.common.constant.ConstantUtils;
import com.javayh.common.exception.BaseException;

/**
 * <p>
 * 业务参数异常
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 21:34
 */
public class BusinessException extends BaseException {

	public BusinessException() {
		super(ConstantUtils.BUSINESS_DATA);
	}

	public BusinessException(String msg) {
		super(msg);
	}

}