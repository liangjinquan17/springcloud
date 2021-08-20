package com.springcloud.auth.authorization.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.core.entity.vo.Result;

@RestController
public class TestController {
	
	@Autowired
	private CheckTokenEndpoint checkTokenEndpoint;

	@GetMapping("hello")
	public String hello() {
		return "hello authorization";
	}
	
	@GetMapping("/oauth/checkToken")
	public Result<Map<String, ?>> checkToken(String token) {
		return Result.success(checkTokenEndpoint.checkToken(token));
	}
}
