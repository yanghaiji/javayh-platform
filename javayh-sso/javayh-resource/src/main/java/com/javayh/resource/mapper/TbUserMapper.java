package com.javayh.resource.mapper;

import com.javayh.resource.vo.UserVO;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {

	UserVO getdByUserName(@Param("username") String username);

}