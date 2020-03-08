package com.javayh.mail.annotation;

import com.javayh.mail.selector.MailImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>
 *       启动日志框架支持
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MailImportSelector.class)
public @interface EnableMail {

}