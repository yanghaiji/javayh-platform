package com.javayh.heartbeat;

import com.javayh.common.annotation.JavayhBootApplication;
import org.springframework.boot.SpringApplication;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-10 13:45
 */
@JavayhBootApplication
public class HeartApplication {
    public static void main(String[] args) {
        SpringApplication.run(HeartApplication.class,args);
    }
}
