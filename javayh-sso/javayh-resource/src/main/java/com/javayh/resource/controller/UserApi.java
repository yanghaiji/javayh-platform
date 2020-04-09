package com.javayh.resource.controller;

import com.javayh.common.result.ResultData;
import com.javayh.resource.mapper.TbUserMapper;
import com.javayh.resource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-06 14:16
 */
@RestController
@RequestMapping("/api/user/")
public class UserApi {

	@Autowired
	private UserService userService;

	@GetMapping(value = "info")
	public ResultData getUser(String username) {
		return ResultData.success(userService.getByUserName(username));
	}

}
