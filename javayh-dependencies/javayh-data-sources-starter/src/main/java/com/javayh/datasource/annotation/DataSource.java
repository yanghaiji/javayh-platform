package com.javayh.datasource.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName DataSource
 * @Description 数据源注解
 * @Author Yang haiji
 * @Version 1.0.0
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

	/**
	 * @Description 数据库名称
	 * @UserModule: javayh-datasource
	 * @author Yang haiji
	 */
	String name();

}