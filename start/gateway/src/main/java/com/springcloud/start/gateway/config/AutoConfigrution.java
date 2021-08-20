package com.springcloud.start.gateway.config;


import com.springcloud.start.gateway.service.IGatewayRouteService;
import com.springcloud.start.gateway.service.impl.GatewayRouteServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Properties.class)
@MapperScan("com.springcloud.start.gateway.dao")
@ComponentScan(value = {"com.springcloud.start.gateway.service"})
public class AutoConfigrution {

}
