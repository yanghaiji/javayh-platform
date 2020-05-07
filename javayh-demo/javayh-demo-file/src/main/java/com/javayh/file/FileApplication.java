package com.javayh.file;

import com.javayh.common.annotation.JavayhBootApplication;
import org.springframework.boot.SpringApplication;

/**
 * <p>
 * file 启动类
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-05-07
 */
@JavayhBootApplication
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class,args);
        System.out.println("1111");
        System.out.println("222222");
    }
}
