package com.javayh.mail;

import com.javayh.client.annotation.EnableAutoHeartBeat;
import com.javayh.common.annotation.JavayhBootApplication;
import com.javayh.mail.annotation.EnableMail;
import org.springframework.boot.SpringApplication;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-08 14:55
 */
@EnableAutoHeartBeat
@EnableMail
@JavayhBootApplication
public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class,args);
    }
}
