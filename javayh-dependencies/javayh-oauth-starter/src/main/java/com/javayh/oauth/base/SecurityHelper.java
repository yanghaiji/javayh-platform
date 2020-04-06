package com.javayh.oauth.base;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * 认证信息帮助类
 *
 */
@Slf4j
public class SecurityHelper {

	/**
	 * 获取认证用户信息
	 * @return
	 */
	public static BaseUserDetails getUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication != null && authentication.isAuthenticated()
				&& authentication instanceof OAuth2Authentication) {
			OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
			OAuth2Request clientToken = oAuth2Authentication.getOAuth2Request();
			if (!oAuth2Authentication.isClientOnly()) {
				if (authentication.getPrincipal() instanceof BaseUserDetails) {
					return (BaseUserDetails) authentication.getPrincipal();
				}
				if (authentication.getPrincipal() instanceof Map) {
					return BeanUtil.mapToBean((Map) authentication.getPrincipal(),
							BaseUserDetails.class, true);
				}
			}
			else {
				BaseUserDetails openUser = new BaseUserDetails();
				openUser.setClientId(clientToken.getClientId());
				openUser.setAuthorities(clientToken.getAuthorities());
				return openUser;
			}
		}
		return null;
	}

	/**
	 * 更新OpenUser
	 * @param openUser
	 */
	public static void updateUser(TokenStore tokenStore, BaseUserDetails openUser) {
		if (openUser == null) {
			return;
		}
		Assert.notNull(openUser.getClientId(), "客户端ID不能为空");
		Assert.notNull(openUser.getUsername(), "用户名不能为空");
		// 动态更新客户端生成的token
		Collection<OAuth2AccessToken> accessTokens = tokenStore
				.findTokensByClientIdAndUserName(openUser.getClientId(),
						openUser.getUsername());
		if (accessTokens != null && !accessTokens.isEmpty()) {
			for (OAuth2AccessToken accessToken : accessTokens) {
				// 由于没有set方法,使用反射机制强制赋值
				OAuth2Authentication oAuth2Authentication = tokenStore
						.readAuthentication(accessToken);
				if (oAuth2Authentication != null) {
					Authentication authentication = oAuth2Authentication
							.getUserAuthentication();
					ReflectUtil.setFieldValue(authentication, "principal", openUser);
					// 重新保存
					tokenStore.storeAccessToken(accessToken, oAuth2Authentication);
				}
			}
		}
	}

	/***
	 * 更新客户端权限
	 * @param tokenStore
	 * @param clientId
	 * @param authorities
	 */
	public static void updateClientAuthorities(TokenStore tokenStore, String clientId,
			Collection<? extends GrantedAuthority> authorities) {
		if (authorities == null) {
			return;
		}
		// 动态更新客户端生成的token
		Collection<OAuth2AccessToken> accessTokens = tokenStore
				.findTokensByClientId(clientId);
		if (accessTokens != null && !accessTokens.isEmpty()) {
			Iterator<OAuth2AccessToken> iterator = accessTokens.iterator();
			while (iterator.hasNext()) {
				OAuth2AccessToken token = iterator.next();
				OAuth2Authentication oAuth2Authentication = tokenStore
						.readAuthentication(token);
				if (oAuth2Authentication != null && oAuth2Authentication.isClientOnly()) {
					// 只更新客户端权限
					// 由于没有set方法,使用反射机制强制赋值
					ReflectUtil.setFieldValue(oAuth2Authentication, "authorities",
							authorities);
					// 重新保存
					tokenStore.storeAccessToken(token, oAuth2Authentication);
				}
			}
		}
	}

	/**
	 * 获取认证用户Id
	 * @return
	 */
	public static Long getUserId() {
		return getUser().getUserId();
	}

	/**
	 * 是否拥有权限
	 * @param authority
	 * @return
	 */
	public static Boolean hasAuthority(String authority) {
		BaseUserDetails auth = getUser();
		if (auth == null) {
			return false;
		}
		if (AuthorityUtils.authorityListToSet(auth.getAuthorities())
				.contains(authority)) {
			return true;
		}
		return false;
	}

	/**
	 * 构建token转换器
	 * @return
	 */
	public static DefaultAccessTokenConverter buildAccessTokenConverter() {
		BaseUserConverter userAuthenticationConverter = new BaseUserConverter();
		DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
		accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
		return accessTokenConverter;
	}

}
