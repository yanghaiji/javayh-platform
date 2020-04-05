package com.javayh.oauth2.server.service.impl;

import com.javayh.oauth2.server.domain.TbPermission;
import com.javayh.oauth2.server.mapper.TbPermissionMapper;
import com.javayh.oauth2.server.service.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbPermissionServiceImpl implements TbPermissionService {

    @Autowired
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }
}
