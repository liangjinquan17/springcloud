package com.springcloud.gateway.gatewayweb.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.authorizationremote.remote.ICheckTokenServiceRemote;
import com.springcloud.core.entity.vo.Result;
import com.springcloud.sysadmin.organizationremote.remote.IGatewayServerRemote;

@RestController
public class HelloController {

//    @Reference( retries = 1, cluster = "failsafe", loadbalance = "leastactive", group = "user", version = "1.0.1")
    @DubboReference(version = "1.0.1")
    private IGatewayServerRemote gatewayServerRemote;
    
    @DubboReference(version = "1.0.1")
    private ICheckTokenServiceRemote checkTokenServiceRemote;

    @GetMapping("/hello")
    public Result hello(String token){
        return Result.success(checkTokenServiceRemote.checkToken(token));
    }

    
}
