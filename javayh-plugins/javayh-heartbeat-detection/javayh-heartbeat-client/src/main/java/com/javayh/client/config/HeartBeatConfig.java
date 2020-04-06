package com.javayh.client.config;

import com.javayh.client.properties.HeartbeatClientProperties;
import com.javayh.common.result.MessageBody;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *
 * </p>
 *
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/10
 */
public class HeartBeatConfig {

	@Bean(value = "heartBeat")
	public MessageBody heartBeat(HeartbeatClientProperties heartbeatClientProperties) {
		return new MessageBody(heartbeatClientProperties.getChannelId(), "ping");
	}

	// @Bean(value = "heartbeatProperties")
	// public HeartbeatProperties heartbeatProperties(){
	// return new HeartbeatProperties() ;
	// }

}
