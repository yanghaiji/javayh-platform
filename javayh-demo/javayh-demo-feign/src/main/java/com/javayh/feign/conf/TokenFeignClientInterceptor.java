//package com.javayh.feign.conf;
//
//import com.javayh.common.util.servlet.RequestUtils;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * <p>
// *      feign添加token
// * </p>
// *
// * @author Dylan-haiji
// * @version 1.0.0
// * @since 2020-04-08 22:33
// */
//@Configuration
//public class TokenFeignClientInterceptor implements RequestInterceptor {
//
//
//    private final String AUTHORIZATION_HEADER = "Authorization";
//    private final String BEARER_TOKEN_TYPE = "Bearer";
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
//            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
//            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
//        }
//
//    }
//
//}
