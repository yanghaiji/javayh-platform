//package com.javayh.feign.conf;
//
//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
///**
// * <p>
// *
// * </p>
// * @version 1.0.0
// * @author Dylan-haiji
// * @since 2020/4/8
// */
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled
//        = true)
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
//                .ExpressionInterceptUrlRegistry registry =
//                http.antMatcher("/**").authorizeRequests();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
//                .authorizeRequests()
//                // 指定监控访问权限
//                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                // 认证鉴权错误处理,为了统一异常处理。每个资源服务器都应该加上。
//// .accessDeniedHandler(new AccessDeniedHandler())
//// .authenticationEntryPoint(new WebException())
//                //配置跨域
//                .and().cors()
//                .and().csrf().disable()
//                // 禁用httpBasic
//                .httpBasic().disable();
//        registry.anyRequest().authenticated();
//    }
//
//}
