package com.springcloud.sysadmin.organization.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.core.entity.vo.Result;
import com.springcloud.start.gateway.entity.po.GatewayRoute;
import com.springcloud.start.gateway.service.IGatewayRouteService;
import com.springcloud.start.sentinel.service.ISentinelFlowControlService;
import com.springcloud.start.user.service.ISysUsersService;
import com.springcloud.sysadmin.organization.entity.param.UserAddParam;
import com.springcloud.web.service.ICacheUserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RefreshScope
public class TestController {
	
	@Autowired
	private IGatewayRouteService gatewayRouteService;
	@Autowired
	private ISentinelFlowControlService sentinelFlowControlService;
	@Autowired
	private ISysUsersService sysUsersService;

    @Value("${name:false}")
    private String name;

    @GetMapping("hello")
    public String hello(){
    	System.out.println(JSONObject.toJSONString(sysUsersService.getById(1401568640119459842L)));
        System.out.println("time:"+new Date());
        return "hello orianization"+new Date();
    }
    
    @GetMapping("h")
    public Result h() {
    	return Result.success(sentinelFlowControlService.queryAll());
    }
    
    @GetMapping("hello2")
    public String hello2(ServerHttpRequest request) throws InterruptedException{
    	Thread.sleep(3000);
        System.out.println("time:"+new Date());
        ICacheUserStrategy cacheUser = ICacheUserStrategy.getDefaultStrategy();
        return "hello orianization2"+new Date()+"  username:"+request.getHeaders().getFirst("username")+ "  cache:username:"+cacheUser.getUser().get().getUsername();
    }
    
    @GetMapping("consumingServiceEndpoint/hello")
    @SentinelResource(value="hello")
    public String hello3() {
    	System.out.println("time"+new Date());
    	return "consumingServiceEndpoint/hello"+new Date();
    }
    
    @GetMapping("getAllGateway")
    public Result<List<GatewayRoute>> getAllGateway() {
    	List<GatewayRoute> list = gatewayRouteService.getAll();
    	return Result.success(list);
    }

    @GetMapping("getConfigName")
    public Result<String> getConfigName(){
        return Result.success(name);
    }

    @PostMapping("testPost")
    public Result<String> testPost(String username){
        return Result.success(username);
    }

    @PostMapping("testPost2")
    public Result<UserAddParam> testPost2(@RequestBody UserAddParam username){
        return Result.success(username);
    }
}
