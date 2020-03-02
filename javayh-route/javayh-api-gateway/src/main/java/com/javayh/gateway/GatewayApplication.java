package com.javayh.gateway;

import com.javayh.common.annotation.JavayhBootApplication;
import org.springframework.boot.SpringApplication;

/**
 * <p>
 * gateway启动类
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-02 10:03
 */
@JavayhBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
