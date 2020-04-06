package com.javayh.config.web;

import com.javayh.common.result.ResultData;
import com.javayh.redis.prefix.KeyUtils;
import com.javayh.redis.util.RedisUtil;
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
	private RedisUtil redisUtil;

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
	@GetMapping(value = "redis")
	public ResultData redis() {
		String ceshi = KeyUtils.key("ceshi");
		boolean hello_word = redisUtil.setObj(ceshi, "hello word", 100);
		String s = (String) redisUtil.get(ceshi);
		return ResultData.success(s);
	}

}
