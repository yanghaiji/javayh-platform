package com.javayh.log.conf;

import com.javayh.log.interceptor.LogInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * 自定义拦截
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-02 16:10
 */
@Configuration
@ConditionalOnClass(WebMvcConfigurer.class)
public class LogAutoConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 自定义拦截器，添加拦截路径和排除拦截路径
         * addPathPatterns():添加需要拦截的路径
         * excludePathPatterns():添加不需要拦截的路径
         * 在括号中还可以使用集合的形式，如注释部分代码所示
         */
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**") ;


    }
}

