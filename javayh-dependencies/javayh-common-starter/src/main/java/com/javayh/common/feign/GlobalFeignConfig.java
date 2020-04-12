package com.javayh.common.feign;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * <p>
 * feign全局 日志输出
 * </p>
 *
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/2
 */
public class GlobalFeignConfig {

    /**
     * <p>
     *       打印请求日志
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/9
     * @param
     * @return feign.Logger.Level
     */
	@Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }


    /**
     * 配置请求重试
     *
     */
    @Bean
    public Retryer feignRetry() {
        return new Retryer.Default(200, SECONDS.toMillis(2), 10);
    }


    /**
     * 设置请求超时时间
     *默认
     * public Options() {
     * this(10 * 1000, 60 * 1000);
     * }
     *
     */
    @Bean
    Request.Options feignOptions() {
        return new Request.Options(60 * 1000, 60 * 1000);
    }

}
