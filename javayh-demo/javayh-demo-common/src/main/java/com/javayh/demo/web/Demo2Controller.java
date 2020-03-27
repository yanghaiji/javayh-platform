package com.javayh.demo.web;

import com.javayh.common.result.ResultData;
import com.javayh.log.log.LogError;
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
 * @since 2020-03-06 9:48
 */
@Api("测试 API")
@RestController
@RequestMapping(value = "/demo/")
public class Demo2Controller {

    @Autowired(required = false)
    private LogError logError;

    @ApiOperation(value = "swagger测试", notes = "测试")
    @GetMapping(value = "swagger")
    public ResultData getSwagger(){
        try {
            Integer.valueOf("22m");
            return ResultData.success();
        } catch (NumberFormatException e) {
            logError.logPrint("swagger",e.getStackTrace());
            return ResultData.fail();
        }
    }
}
