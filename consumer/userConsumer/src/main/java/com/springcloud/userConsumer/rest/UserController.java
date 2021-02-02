package com.springcloud.userConsumer.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springcloud.userRemote.IUserRemote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController{

	@Reference( retries = 1, cluster = "failsafe", loadbalance = "leastactive", group = "user", version = "1.0.1")
	private IUserRemote userRemote;
	
	@GetMapping("hello")
	public String hello(){
		return userRemote.getUserNameById(10l);
	}
}
