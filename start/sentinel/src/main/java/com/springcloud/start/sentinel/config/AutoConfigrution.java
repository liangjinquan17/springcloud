package com.springcloud.start.sentinel.config;

import com.springcloud.start.sentinel.service.ISentinelDegradeRuleService;
import com.springcloud.start.sentinel.service.ISentinelFlowControlService;
import com.springcloud.start.sentinel.service.impl.SentinelDegradeRuleServiceImpl;
import com.springcloud.start.sentinel.service.impl.SentinelFlowControlServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Properties.class)
@MapperScan("com.springcloud.start.sentinel.dao")
@ComponentScan(value = {"com.springcloud.start.sentinel.service"})
public class AutoConfigrution {

}
