package com.javayh.gateway.conf.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 限流配置器 key-resolver:"#{@ipKeyResolver}" #SPEL表达式去的对应的bean
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-05 22:38
 */
@Configuration
public class RateLimiterConfig {

	/**
	 * <p>
	 * 根据Ip限流
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/5
	 * @param
	 * @return org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
	 */
	@Primary
	@Bean
	public KeyResolver ipKeyResolver() {
		return exchange -> Mono
				.just(exchange.getRequest().getRemoteAddress().getHostName());
	}

	/**
	 * <p>
	 * 根据用户限流 官方不建议再生产使用该参数，这样路需要携带用户信息
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/5
	 * @param
	 * @return org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
	 */
	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono
				.just(exchange.getRequest().getQueryParams().getFirst("user"));
	}

	/**
	 * <p>
	 * 根据请求地址限流
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/5
	 * @param
	 * @return org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
	 */
	@Bean
	KeyResolver apiKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getPath().value());
	}

}
