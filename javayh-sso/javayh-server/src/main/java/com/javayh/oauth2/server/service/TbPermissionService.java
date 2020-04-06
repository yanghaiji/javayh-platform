package com.javayh.oauth2.server.service;

import com.javayh.oauth.domain.TbPermission;

import java.util.List;

public interface TbPermissionService {

	List<TbPermission> selectByUserId(Long userId);

}
