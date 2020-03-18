package com.javayh.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-02 13:40
 */
@Service
@FeignClient(name = "javayh-feign",fallback = DemoFallback.class)
public interface DemoService {

    @GetMapping(value = "/feign/getfeign")
    String getFeign();
}
