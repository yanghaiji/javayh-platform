package com.javayh.oauth.base;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义认证用户信息转换器
 *
 */
@Slf4j
public class BaseUserConverter extends DefaultUserAuthenticationConverter {

	public BaseUserConverter() {
	}

	/**
	 * 转换为自定义信息
	 * @param map
	 * @return
	 */
	private Object converter(Map<String, ?> map) {
		Map<String, Object> params = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			if (USERNAME.equals(key)) {
				if (map.get(key) instanceof Map) {
					params.putAll((Map) map.get(key));
				}
				else if (map.get(key) instanceof BaseUserDetails) {
					return map.get(key);
				}
				else {
					params.put(key, map.get(key));
				}
			}
			else {
				params.put(key, map.get(key));
			}
		}
		BaseUserDetails auth = BeanUtil.mapToBean(params, BaseUserDetails.class, true);
		if (params.get(USERNAME) != null) {
			auth.setUsername(params.get(USERNAME).toString());
		}
		if (params.get(BaseSecurityConstants.OPEN_ID) != null) {
			auth.setUserId(
					Long.parseLong(params.get(BaseSecurityConstants.OPEN_ID).toString()));
		}
		if (params.get(BaseSecurityConstants.DOMAIN) != null) {
			auth.setDomain(params.get(BaseSecurityConstants.DOMAIN).toString());
		}
		if (params.get(BaseSecurityConstants.EMAIL) != null) {
			auth.setEmail(params.get(BaseSecurityConstants.EMAIL).toString());
		}
		if (params.get(BaseSecurityConstants.IS_ADMIN) != null) {
			auth.setAdmin("super".equals(params.get(BaseSecurityConstants.IS_ADMIN)));
		}
		auth.setClientId(params.get(AccessTokenConverter.CLIENT_ID).toString());
		auth.setAuthorities(getAuthorities(map));
		return auth;
	}

	/**
	 * 转换用户
	 * @param authentication
	 * @return
	 */
	@Override
	public Map<String, ?> convertUserAuthentication(Authentication authentication) {
		Map<String, Object> response = new LinkedHashMap();
		response.put(USERNAME, authentication.getPrincipal());
		if (authentication.getAuthorities() != null
				&& !authentication.getAuthorities().isEmpty()) {
			response.put(AUTHORITIES,
					AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		}
		return response;
	}

	/**
	 * 读取认证信息
	 * @param map
	 * @return
	 */
	@Override
	public Authentication extractAuthentication(Map<String, ?> map) {
		if (map.containsKey(USERNAME)) {
			Object principal = converter(map);
			Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
			if (principal != null) {
				BaseUserDetails user = (BaseUserDetails) principal;
				authorities = user.getAuthorities();
			}
			return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
		}
		return null;
	}

	/**
	 * 获取权限
	 * @param map
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
		if (!map.containsKey(AUTHORITIES)) {
			return AuthorityUtils.NO_AUTHORITIES;
		}
		Object authorities = map.get(AUTHORITIES);
		if (authorities instanceof String) {
			return AuthorityUtils
					.commaSeparatedStringToAuthorityList((String) authorities);
		}
		if (authorities instanceof Collection) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
					.collectionToCommaDelimitedString((Collection<?>) authorities));
		}
		throw new IllegalArgumentException(
				"Authorities must be either a String or a Collection");
	}

}
