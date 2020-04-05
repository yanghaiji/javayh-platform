package com.javayh.oauth2.server.conf;

import cn.hutool.extra.servlet.ServletUtil;
import com.javayh.common.util.servlet.RequestUtils;
import com.javayh.oauth2.server.conf.base.BaseUserDetails;
import com.javayh.oauth2.server.conf.base.SecurityHelper;
import com.javayh.oauth2.server.conf.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    // 声明 ClientDetails实现
    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }


    private BearerTokenExtractor tokenExtractor = new BearerTokenExtractor();

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //使用Session来保存会话
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                //放行路径
                .antMatchers("/login/**", "/oauth/**").permitAll()
                // 监控端点内部放行
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .successHandler(new LoginSuccesssHandler())
                .and()
                .logout().permitAll()
                // /logout退出清除cookie
                .addLogoutHandler(new CookieClearingLogoutHandler("token", "remember-me"))
                //登出的操作
                .logoutSuccessHandler(new LogoutSuccessHandler())
                .and()
                .exceptionHandling()
                // 认证鉴权错误处理,为了统一异常处理。每个资源服务器都应该加上。
//                .accessDeniedHandler(new BaseAccessDeniedHandler())
//                .authenticationEntryPoint(new BaseAuthenticationEntryPoint())
                .and().csrf().disable()
                // 禁用httpBasic
                .httpBasic().disable();
    }

    public class LoginSuccesssHandler implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            HttpServletRequest request = RequestUtils.getRequest();
            if (request != null) {
                Map map = new HashMap();
                BaseUserDetails userDetails = SecurityHelper.getUser();
                map.put("domain", "@site.com");
                map.put("userId", userDetails.getUserId());
                map.put("account", userDetails.getUsername());
                map.put("accountId", userDetails.getAccountId());
                map.put("accountType", userDetails.getAccountType());
                map.put("loginIp", ServletUtil.getClientIP(request));
                map.put("loginAgent", request.getHeader(HttpHeaders.USER_AGENT));
            }
        }
    }

    public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
        public LogoutSuccessHandler() {
            // 重定向到原地址
            this.setUseReferer(true);
        }

        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            try {
                // 解密请求头
                authentication = tokenExtractor.extract(request);
                if (authentication != null && authentication.getPrincipal() != null) {
                    String tokenValue = authentication.getPrincipal().toString();
                    log.debug("revokeToken tokenValue:{}", tokenValue);
                    // 移除token
                    tokenStore.removeAccessToken(tokenStore.readAccessToken(tokenValue));
                }
            } catch (Exception e) {
                log.error("revokeToken error:{}", e);
            }
            super.onLogoutSuccess(request, response, authentication);
        }
    }
}
