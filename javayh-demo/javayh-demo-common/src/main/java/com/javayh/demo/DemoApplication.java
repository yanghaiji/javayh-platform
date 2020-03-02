package com.javayh.demo;

import com.javayh.common.annotation.JavayhBootApplication;
import com.javayh.log.annotation.EnableLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * 测试启动类
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 20:44
 */
@EnableLogging
@JavayhBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }

}
