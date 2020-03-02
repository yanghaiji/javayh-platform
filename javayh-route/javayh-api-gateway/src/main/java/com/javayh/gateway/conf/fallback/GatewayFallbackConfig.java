package com.javayh.gateway.conf.fallback;

import com.javayh.gateway.handler.HystrixFallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * <p>
 *      服务回滚
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-02 12:29
 */
@Configuration
public class GatewayFallbackConfig {

    @Autowired
    private HystrixFallbackHandler hystrixFallbackHandler;

    /**
     * <p>
     *       实例化，用于服务降级配置
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/2
     * @param
     * @return org.springframework.web.reactive.function.server.RouterFunction
     */
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.GET("/defaultfallback")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler);
    }

}

