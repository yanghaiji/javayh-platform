package com.javayh.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 监听配置类
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-10 15:41
 */
@ConfigurationProperties(prefix = "heartbeat.server", ignoreUnknownFields = true)
public class HeartbeatClientProperties {

	/** 唯一标识 */
	private long channelId;

	/** host地址 */
	private String host;

	/** server 端口 */
	private int port;

	public HeartbeatClientProperties() {
	}

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
