package com.javayh.oauth2.server.conf.base;

import com.javayh.oauth2.server.conf.BaseSecurityConstants;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义JwtAccessToken转换器
 */
public class BaseTokenEnhancer extends TokenEnhancerChain {

    /**
     * 生成token
     *
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        final Map<String, Object> additionalInfo = new HashMap<>(8);
        if (!authentication.isClientOnly()) {
            if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof BaseUserDetails) {
                // 设置额外用户信息
                BaseUserDetails baseUser = ((BaseUserDetails) authentication.getPrincipal());
                additionalInfo.put(BaseSecurityConstants.OPEN_ID, baseUser.getUserId());
                additionalInfo.put(BaseSecurityConstants.DOMAIN, baseUser.getDomain());
                additionalInfo.put(BaseSecurityConstants.EMAIL, baseUser.getEmail());
                additionalInfo.put(BaseSecurityConstants.IS_ADMIN, baseUser.getAdmin());
            }
        }
        defaultOAuth2AccessToken.setAdditionalInformation(additionalInfo);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
}
