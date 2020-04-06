package com.javayh.common.feign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javayh.common.exception.hystrix.HytrixException;
import com.javayh.common.exception.service.ServiceException;
import com.javayh.common.util.log.Log;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * <p>
 * 自定义异常处理
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-02 14:57
 */
@Slf4j
public class FeignExceptionConfig {

	@Bean
	public ErrorDecoder errorDecoder() {
		return new UserErrorDecoder();
	}

	public class UserErrorDecoder implements ErrorDecoder {

		@Override
		public Exception decode(String key, Response response) {
			Exception exception = null;
			ObjectMapper mapper = new ObjectMapper();
			ObjectMapper objectMapper = new ObjectMapper();
			/* 序列化的时候序列对象的所有属性 空属性处理 */
			mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			/* 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性 */
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			/* 禁止使用int代表enum的order来反序列化enum */
			mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
			try {
				String json = Util.toString(response.body().asReader());
				/* 非业务异常包装成自定义异常类ServiceException */
				if (StringUtils.isNotEmpty(json)) {
					if (json.contains("code")) {
						FeignFaildResult result = mapper.readValue(json,
								FeignFaildResult.class);
						result.setStatus(response.status());
						/* 业务异常包装成自定义异常类HytrixException */
						if (result.getStatus() != HttpStatus.OK.value()) {
							exception = new HytrixException(result.getResp_msg());
						}
						else {
							exception = feign.FeignException.errorStatus(key, response);
						}
					}
					else {
						exception = new ServiceException("程序异常");
					}
				}
				else {
					exception = feign.FeignException.errorStatus(key, response);
				}
			}
			catch (IOException ex) {
				Log.error(ex.getMessage(), ex.getStackTrace());
			}
			return exception;
		}

	}

}
