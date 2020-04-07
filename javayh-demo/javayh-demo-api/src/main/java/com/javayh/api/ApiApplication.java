package com.javayh.api;

import com.javayh.common.annotation.JavayhBootApplication;
import com.javayh.common.exception.annotation.EnableAutoException;
import com.javayh.common.i18n.annotation.EnableAutoInternationalization;
import com.javayh.swagger.annotation.EnableAutoSwagger;
import org.springframework.boot.SpringApplication;

/**
 * <p>
 * 测试启动类
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 20:44
 */
//@EnableAutoException
@EnableAutoSwagger
@EnableAutoInternationalization
@JavayhBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
