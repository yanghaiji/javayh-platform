package com.javayh.common.feign;

import lombok.Data;

/**
 * <p>
 * feign异常返回
 * </p>
 *
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/2
 */
@Data
public class FeignFailResult {

	private int status;

	private String resp_msg;

}
