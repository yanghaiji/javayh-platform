package com.javayh.common.util.servlet;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 获取Request
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-03 13:42
 */
public class RequestUtils {

    public static  HttpServletRequest getRequest (){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
}
