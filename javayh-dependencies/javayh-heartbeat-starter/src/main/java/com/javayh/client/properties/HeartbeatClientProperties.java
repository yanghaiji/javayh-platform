package com.javayh.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 监听配置类
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-10 15:41
 */
@ConfigurationProperties(
        prefix = "heartbeat.server",
        ignoreUnknownFields = true
)
public class HeartbeatClientProperties {

	private @NotNull Long channelId;
	private @NotBlank String host;
	private @NotNull Integer port;
	private @NotBlank String appName;

	public HeartbeatClientProperties() {
	}

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
