package com.javayh.oauth2.server.mapper;

import com.javayh.oauth.domain.TbUser;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {

	TbUser findByUserName(@Param("username") String username);

}