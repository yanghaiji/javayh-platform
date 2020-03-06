package com.javayh.demo.web;

import com.javayh.common.result.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @since 2020-03-06 9:48
 */
@Api("测试 API")
@RestController
@RequestMapping(value = "/demo/")
public class Demo2Controller {

    @ApiOperation(value = "swagger测试", notes = "测试")
    @GetMapping(value = "swagger")
    public ResultData getSwagger(){
        return ResultData.success("Hello Swagger!");
    }
}
