package com.springcloud.gateway.gatewayweb.filter;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.authorizationremote.entity.DTO.AuthUserDetailDTO;
import com.springcloud.authorizationremote.remote.ICheckTokenServiceRemote;
import com.springcloud.core.entity.vo.Result;
import com.springcloud.core.exception.SystemErrorType;
import com.springcloud.gateway.gatewayweb.authorization.AuthorizationIgnoreService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 全局拦截
 * 判断地址是否在需要登录，需要登录的地址，获取token，解析token。提取用户信息并传到下游服务
 * @author liangjinquan
 *
 */
@Component
public class GatewayGlobalFIlter implements GlobalFilter, Ordered{
	
	private String authorizationStartWith = "Bearer ";
	@Resource
	private AuthorizationIgnoreService authorizationIgnoreService;
	@DubboReference(version = "1.0.1")
	private ICheckTokenServiceRemote checkTokenServiceRemote;
	
	@Override
	public int getOrder() {
		return 99;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String url = exchange.getRequest().getPath().value();
		if(authorizationIgnoreService.ignoreUrl(url)) {
			return chain.filter(exchange);
		}else {
			String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
			if(null == authorization || !authorization.startsWith(authorizationStartWith)) {
				 return tokenFailure(exchange);
			}

			// 鉴权同时提取用户信息，传给下游服务
			String token = authorization.split(authorizationStartWith)[1];
			AuthUserDetailDTO authUserDetailDTO = checkTokenServiceRemote.checkToken(token);
			if(null != authUserDetailDTO && null != authUserDetailDTO.getUserName()) {
				ServerHttpRequest.Builder builder = request.mutate();
				builder.header("username", authUserDetailDTO.getUserName());
				return chain.filter(exchange.mutate().request(builder.build()).build());
			}else {
				return tokenFailure(exchange);
			}
		}
	}
	
	/**
     * token失效
     * @param serverWebExchange
     * @return
     */
    private Mono<Void> tokenFailure(ServerWebExchange serverWebExchange){
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(JSONObject.toJSONString(Result.fail(SystemErrorType.INVALID_TOKEN)).getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

}
