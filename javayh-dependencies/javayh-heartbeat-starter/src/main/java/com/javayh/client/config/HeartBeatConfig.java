package com.javayh.client.config;

import com.javayh.client.properties.HeartbeatClientProperties;
import com.javayh.common.result.MessageBody;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.context.annotation.Bean;

import java.util.Date;

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
	public MessageBody heartBeat(HeartbeatClientProperties properties) {
		return MessageBody.builder()
                .msgId(properties.getChannelId())
                .msg("ping")
                .appName(properties.getAppName())
                .createDate(new Date()).build();
	}

}
