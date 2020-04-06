package com.javayh.oauth2.server.service.impl;

import com.javayh.oauth2.server.mapper.TbRoleMapper;
import com.javayh.oauth2.server.service.TbRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbRoleServiceImpl implements TbRoleService {

	@Autowired
	private TbRoleMapper tbRoleMapper;

}
