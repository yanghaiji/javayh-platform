package com.javayh.common.config.i18n;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 *      国际化
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-27 15:23
 */
@ConfigurationProperties(prefix = "spring.messages")
public class I18nProperties {

    private String basename;

    public String getBasename() {
        return basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }
}
