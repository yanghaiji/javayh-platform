package com.javayh.feign.web;

import com.javayh.common.result.ResultData;
import com.javayh.feign.service.impl.DemoFeignServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-02 14:20
 */
@Api("测试 Swagger API")
@RestController
@RequestMapping(value = "/feign/")
public class FeignCtr {

    @Autowired
    private DemoFeignServiceImpl demoFeignService;

    @ApiOperation(value = "测试 ", notes = "测试")
    @GetMapping(value = "getfeign")
    public ResultData get(){
        return ResultData.success(demoFeignService.getFeign());
    }
}
