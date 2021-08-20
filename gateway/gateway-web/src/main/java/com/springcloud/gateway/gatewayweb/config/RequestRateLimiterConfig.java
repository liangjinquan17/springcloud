package com.springcloud.gateway.gatewayweb.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 自定义限流标志的key，多个维度可以从这里入手
 * exchange对象中获取服务ID、请求信息，用户信息等
 */
@Component
public class RequestRateLimiterConfig {

    /**
     * 请求路径限速
     * @return
     */
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
