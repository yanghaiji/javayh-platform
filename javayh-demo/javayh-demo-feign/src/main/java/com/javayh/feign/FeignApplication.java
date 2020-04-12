package com.javayh.feign;

import com.javayh.common.annotation.JavayhBootApplication;
import com.javayh.common.i18n.annotation.EnableAutoInternationalization;
import com.javayh.swagger.annotation.EnableAutoSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * feign 启动类
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-02 13:34
 */
@EnableFeignClients
@EnableAutoSwagger
// @EnableAutoHeartBeat
@EnableAutoInternationalization
@JavayhBootApplication
public class FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}

}
