package com.javayh.demo.web;

import com.javayh.common.result.ResultData;
import com.javayh.demo.service.DemoService;
import com.javayh.log.annotation.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * demo 访问
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 20:46
 */
@RestController
@RequestMapping(value = "/demo/")
public class DemoCtroller {

    @Autowired
    private DemoService demoService;

    /**
     * <p>
     *       测试全局异常是否生效
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/1
     * @param
     * @return com.javayh.common.result.ResultData
     */
    @GetMapping(value = "hello")
    public ResultData get(){
        Integer.valueOf("aa");
        return ResultData.success("Hello Word!");
    }


    /***
     * <p>
     *       测试gateway
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/2
     * @param
     * @return com.javayh.common.result.ResultData
     */
    @GetMapping(value = "gateway")
    public ResultData gateway(){
        return ResultData.success("Hello Gateway!");
    }

    /**
     * <p>
     *       Feign 调用测试
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/3/2
     * @param
     * @return com.javayh.common.result.ResultData
     */
    @SysLog(value = "javayh-demo-common",detail = "测试Feign异步调用")
    @GetMapping(value = "getCleint")
    public ResultData getCleint(){
        String feign = demoService.getFeign();
        return ResultData.success(feign);
    }
}
