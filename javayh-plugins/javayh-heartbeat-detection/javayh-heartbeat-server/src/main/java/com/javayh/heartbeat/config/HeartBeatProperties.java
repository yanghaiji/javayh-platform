package com.javayh.heartbeat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-27 15:55
 */
@ConfigurationProperties(prefix = "heartbeat.server")
public class HeartBeatProperties {

    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
