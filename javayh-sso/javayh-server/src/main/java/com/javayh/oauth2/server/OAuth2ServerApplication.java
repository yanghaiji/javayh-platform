package com.javayh.oauth2.server;

import com.javayh.common.annotation.JavayhBootApplication;
import com.javayh.common.exception.annotation.EnableAutoException;
import com.javayh.common.i18n.annotation.EnableAutoInternationalization;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-05 14:17
 */
@EnableAutoException
@EnableAutoInternationalization
@MapperScan(basePackages = "com.javayh.oauth2.server.mapper")
@JavayhBootApplication
public class OAuth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2ServerApplication.class, args);
	}

}
