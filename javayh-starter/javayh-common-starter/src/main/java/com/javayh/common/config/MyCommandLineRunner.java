package com.javayh.common.config;

import com.javayh.common.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

/**
 * <p>
 *      成功启动后的操作
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-23 20:58
 */
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String port;
    private String pr = "项目< ";
    private String sr = " >已启动  访问地址为:{}";
    @Override
    public void run(String... args) throws Exception {
        log.info(pr+applicationName+sr,IPUtils.getHostIp()+":"+port+"/");
    }
}
