package com.javayh.resource.mapper;

import com.javayh.resource.vo.UserVO;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {

	UserVO getByUserName(@Param("username") String username);

}