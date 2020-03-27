package com.javayh.common.config.i18n;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * <p>
 * 国际化
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-27 14:51
 */
@EnableConfigurationProperties(value = I18nProperties.class)
public class InternationalConfig {

    @Autowired(required=false)
    private I18nProperties i18nProperties;

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        ((ResourceBundleMessageSource) messageSource).setDefaultEncoding("UTF-8");
        String[] basename = i18nProperties.getBasename().split(",");
        for (String name : basename) {
            messageSource.addBasenames(name);
        }
        return messageSource;
    }
}