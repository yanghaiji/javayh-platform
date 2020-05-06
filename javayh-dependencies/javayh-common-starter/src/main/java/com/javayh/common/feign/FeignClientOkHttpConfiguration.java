package com.javayh.common.feign;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *      Feign Client
 * </p>
 *
 * @author Yang Haiji
 * @version 1.0.0
 * @since 2020-05-04 12:13
 */
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignClientOkHttpConfiguration {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                // 连接超时
                .connectTimeout(30, TimeUnit.SECONDS)
                // 响应超时
                .readTimeout(30, TimeUnit.SECONDS)
                // 写超时
                .writeTimeout(30, TimeUnit.SECONDS)
                // 是否自动重连
                .retryOnConnectionFailure(true)
                // 连接池
                .connectionPool(new ConnectionPool())
                .build();
    }

}
