package com.javayh.client.config;

import com.javayh.client.properties.HeartbeatProperties;
import com.javayh.common.result.MessageBody;
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

    @Bean(value = "heartBeat")
    public MessageBody heartBeat(HeartbeatProperties heartbeatProperties){
        return new MessageBody(heartbeatProperties.getChannelId(),"ping") ;
    }

//    @Bean(value = "heartbeatProperties")
//    public HeartbeatProperties heartbeatProperties(){
//        return new HeartbeatProperties() ;
//    }
}
