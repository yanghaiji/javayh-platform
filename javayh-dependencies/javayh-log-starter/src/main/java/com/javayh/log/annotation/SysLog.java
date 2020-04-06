package com.javayh.log.annotation;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 日志记录
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 23:43
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface SysLog {

	/**
	 * 模块
	 */
	String value();

	/**
	 * 方法描述
	 */
	String detail() default "";

	/**
	 * 记录执行参数
	 */
	boolean recordRequestParam() default true;

	/**
	 * 等级 1 - 9 等级越高 ，数字越大
	 */
	int level() default 1;

	/**
	 * 是否持久化
	 */
	boolean persistence() default true;

}
