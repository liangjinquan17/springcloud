package com.springcloud.gateway.gatewayweb.authorization;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 过滤不需要鉴权的地址
 * @author liangjinquan
 *
 */
@Service
public class AuthorizationIgnoreService {

	@Value("${gate.ignore.authorization.startWith}")
	private String ignoreUrl;
	
	/**
	 * 判断地址需要要过滤
	 * @param url
	 * @return true=过滤的地址 false=要授权的地址
	 */
	public boolean ignoreUrl(String url) {
		return Stream.of(ignoreUrl.split(",")).anyMatch(ignoreUrl -> ignoreUrl.startsWith(url));
	}
}
