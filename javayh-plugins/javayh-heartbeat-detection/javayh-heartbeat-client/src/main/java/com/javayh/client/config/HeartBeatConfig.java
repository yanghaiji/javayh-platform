package com.javayh.client.config;

import com.javayh.common.result.MessageBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *
 * </p>
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/10
 */
public class HeartBeatConfig {

    @Value("${channel.id}")
    private long id;

    @Bean(value = "heartBeat")
    public MessageBody heartBeat(){
        return new MessageBody(id,"ping") ;
    }
}
