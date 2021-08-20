package com.springcloud.web.filter;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.springcloud.web.entity.DTO.UserDTO;
import com.springcloud.web.service.ICacheUserStrategy;

import reactor.core.publisher.Mono;

/**
 * 封装用户登录信息
 * @author liangjinquan
 *
 */
public class UserWebFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		// demo
		String username = exchange.getRequest().getHeaders().getFirst("username");
		// 测试了threadlocal可以传递参数，并发高的时候有待测试是否能用
		// 因为这里用的是webflux，所以对threadlocal传递参数是否能用，不确定
		ICacheUserStrategy cacheUserService = ICacheUserStrategy.getDefaultStrategy();
		cacheUserService.setUser(new UserDTO(username));
		return chain.filter(exchange);
	}

}
