package com.springcloud.authorization.rest;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

	@GetMapping("hello")
	public String hello(){
		return "hello authorization"+new Date();
	}
}
