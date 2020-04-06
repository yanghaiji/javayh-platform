package com.javayh.oauth2.server.mapper;

import com.javayh.oauth.domain.TbPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbPermissionMapper {

	List<TbPermission> selectByUserId(@Param("userId") Long userId);

}