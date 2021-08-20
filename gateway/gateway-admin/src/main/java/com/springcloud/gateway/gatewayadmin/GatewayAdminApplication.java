package com.springcloud.gateway.gatewayadmin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayAdminApplication.class, args);
	}

}
