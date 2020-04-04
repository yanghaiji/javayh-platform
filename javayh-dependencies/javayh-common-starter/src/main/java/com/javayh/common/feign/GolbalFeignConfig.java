package com.javayh.common.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *     feign全局 日志输出
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/2
 */
@Configuration
public class GolbalFeignConfig {

    @Bean
    public Logger.Level levl(){
        return Logger.Level.FULL;
    }
}
