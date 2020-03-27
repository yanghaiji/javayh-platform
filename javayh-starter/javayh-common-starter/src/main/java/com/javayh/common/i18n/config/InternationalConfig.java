package com.javayh.common.i18n.config;

import com.javayh.common.constant.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

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
    public MessageSource  getMessageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(ConstantUtils.UTF);
        String[] basename = i18nProperties.getBasename().split(",");
        for (String name : basename) {
            messageSource.addBasenames(name);
        }
        return messageSource;
    }
    /**
     * 设置当前的返回信息
     * @param request
     * @param code
     * @return
     */
    public String getMessage(HttpServletRequest request, String code){
        MessageSource messageResource = getMessageResource();
        String language=request.getHeader("Accept-Language");
        //默认没有就是请求地区的语言
        Locale locale=null;
        if(language==null){
            locale=request.getLocale();
        }
        else if("en-US".equals(language)){
            locale= Locale.ENGLISH;
        }
        else if("zh-CN".equals(language)){
            locale=Locale.CHINA;
        }
        //其余的不正确的默认就是本地的语言
        else{
            locale=request.getLocale();
        }
        String result=null;
        try {
            result = messageResource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e) {
        }
        if(result==null){
            return code;
        }
        return result;
    }
}