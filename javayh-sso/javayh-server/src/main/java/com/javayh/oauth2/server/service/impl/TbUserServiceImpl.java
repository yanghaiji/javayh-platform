package com.javayh.oauth2.server.service.impl;

import com.javayh.oauth2.server.domain.TbUser;
import com.javayh.oauth2.server.mapper.TbUserMapper;
import com.javayh.oauth2.server.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getByUsername(String username) {
        return tbUserMapper.findByUserName(username);
    }

}
