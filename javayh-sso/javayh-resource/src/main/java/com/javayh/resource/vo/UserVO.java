package com.javayh.resource.vo;

import com.javayh.oauth.domain.TbPermission;
import com.javayh.oauth.domain.TbRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-06 16:25
 */
@Data
public class UserVO implements Serializable {

	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码，加密存储
	 */
	private String password;

	/**
	 * 注册手机号
	 */
	private String phone;

	/**
	 * 注册邮箱
	 */
	private String email;

	private List<PermissionRole> permission;

	private List<Role> role;

}

@Data
class PermissionRole {

	private String pname;

	private String penname;

}

@Data
class Role {

	private String rname;

	private String renname;

}