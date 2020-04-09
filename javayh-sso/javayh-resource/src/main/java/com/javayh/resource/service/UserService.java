package com.javayh.resource.service;

import com.javayh.resource.vo.UserVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-06 16:32
 */
public interface UserService {

	UserVO getByUserName(String username);

}
