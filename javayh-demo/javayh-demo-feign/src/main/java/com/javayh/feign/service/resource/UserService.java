package com.javayh.feign.service.resource;

import com.javayh.common.result.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 通过feign调用资源服务器
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-08 22:22
 */

@FeignClient
        ( name = "javayh-resource",
          fallback = ResourceFallback.class
)
public interface UserService {

    /**
     * <p>
     *       feign调用资源服务器
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/8
     * @param username
     * @return com.javayh.common.result.ResultData
     */
    @GetMapping(value = "/api/user/info")
    ResultData getByUserName(String username);
}
@Configuration
class ResourceFallback implements UserService{

    @Override
    public ResultData getByUserName(String username) {
        return ResultData.fail("service_call_exception");
    }
}
