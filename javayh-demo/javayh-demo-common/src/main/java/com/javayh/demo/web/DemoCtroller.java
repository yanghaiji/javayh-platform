package com.javayh.demo.web;

import com.javayh.common.encrypt.rsa.RSAEncrypt;
import com.javayh.common.encrypt.rsa.RSAUtil;
import com.javayh.common.result.ResultData;
import com.javayh.demo.service.DemoService;
import com.javayh.log.annotation.SysLog;
import com.javayh.redis.prefix.KeyUtils;
import com.javayh.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

import static com.javayh.common.encrypt.rsa.RSAEncrypt.initKey;

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

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * <p>
	 * 测试全局异常是否生效
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/1
	 * @param
	 * @return com.javayh.common.result.ResultData
	 */
	@GetMapping(value = "hello")
	public ResultData get() {
		Integer.valueOf("aa");
		return ResultData.success("Hello Word!");
	}

	/***
	 * <p>
	 * 测试gateway
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/2
	 * @param
	 * @return com.javayh.common.result.ResultData
	 */
	@GetMapping(value = "gateway")
	public ResultData gateway() {
		return ResultData.success("Hello Gateway!");
	}

	/**
	 * <p>
	 * Feign 调用测试
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/2
	 * @param
	 * @return com.javayh.common.result.ResultData
	 */
//	@SysLog(value = "javayh-demo-common", detail = "测试Feign异步调用")
	@GetMapping(value = "getCleint")
	public ResultData getCleint() {
		String feign = demoService.getFeign();
		return ResultData.success(feign);
	}

	/**
	 * <p>
	 * 测试Redis
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/4
	 * @param
	 * @return com.javayh.common.result.ResultData
	 */
	@SysLog(value = "javayh-demo-common", detail = "测试Redis")
	@GetMapping(value = "redis")
	public ResultData redis() {
		String ceshi = KeyUtils.key("ceshi");
		boolean hello_word = redisUtil.setObj(ceshi, "hello word", 100);
		String s = (String) redisUtil.get(ceshi);
		return ResultData.success(s);
	}

	/**
	 * <p>
	 * 测试加密解密
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/5
	 * @param key
	 * @return com.javayh.common.result.ResultData
	 */
	@GetMapping(value = "rsa/{key}")
	public ResultData getE(@PathVariable String key) throws Exception {
		Map<String, Object> keyMap = initKey();
		PublicKey publicKey = RSAUtil.getPublicKey(RSAEncrypt.getPublicKey(keyMap));
		System.out.println(publicKey);
		PrivateKey privateKey = RSAUtil.getPrivateKey(RSAEncrypt.getPrivateKey(keyMap));
		System.out.println(privateKey);
		String source = RSAUtil.encryptString(publicKey, key);
		String oldSource = RSAUtil.decryptString(privateKey, source);
		System.out.println("解密后数据:" + oldSource);
		return ResultData.success(oldSource);
	}

}
