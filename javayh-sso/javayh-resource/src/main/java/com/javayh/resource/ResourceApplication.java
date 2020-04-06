package com.javayh.resource;

import com.javayh.common.annotation.JavayhBootApplication;
import com.javayh.common.i18n.annotation.EnableAutoInternationalization;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-06 14:21
 */
@EnableAutoInternationalization
@MapperScan(basePackages = "com.javayh.resource.mapper")
@JavayhBootApplication
public class ResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}

}
