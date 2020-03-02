package com.javayh.feign.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 接口实现
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-02 13:37
 */
@Service
public class DemoFeignServiceImpl {

    @GetMapping(value = "/feign/getFeign")
    public String getFeign() {
        return "Feign Success";
    }
}
