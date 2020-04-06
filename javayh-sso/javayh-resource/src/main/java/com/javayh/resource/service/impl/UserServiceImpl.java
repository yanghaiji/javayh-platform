package com.javayh.resource.service.impl;

import com.javayh.resource.mapper.TbUserMapper;
import com.javayh.resource.service.UserService;
import com.javayh.resource.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-06 16:32
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public UserVO getdByUserName(String username) {
		return tbUserMapper.getdByUserName(username);
	}

}
