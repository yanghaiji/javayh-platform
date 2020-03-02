package com.javayh.demo.service;

import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-02 13:48
 */
@Component
public class DemoFallback implements DemoService{
    @Override
    public String getFeign() {
        return "error";
    }
}
