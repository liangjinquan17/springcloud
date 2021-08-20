package com.springcloud.gateway.gatewayadmin.controller;

import com.springcloud.sysadmin.organizationremote.remote.ISentinelDegradeRuleServiceRemote;
import com.springcloud.sysadmin.organizationremote.remote.ISentinelFlowControlServerRemote;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.core.entity.vo.Result;
import com.springcloud.sysadmin.organizationremote.entity.DTO.UserDTO;
import com.springcloud.sysadmin.organizationremote.remote.IGatewayServerRemote;
import com.springcloud.sysadmin.organizationremote.remote.IUserServerRemote;

import java.util.Date;

@RestController
public class GatewayController {
	
	@DubboReference(version = "1.0.1")
	private IUserServerRemote userServerRemote;
	@DubboReference(version = "1.0.1")
	private IGatewayServerRemote gatewayServerRemote;
    @DubboReference(version = "1.0.1", retries=0)
    private ISentinelFlowControlServerRemote sentinelFlowControlService;
    @DubboReference(version = "1.0.1", retries=0)
    private ISentinelDegradeRuleServiceRemote sentinelDegradeRuleService;

    @GetMapping("getTime")
    public Result<Date> getTime(){
        return Result.success(new Date());
    }

    @GetMapping("hello")
    public Result<UserDTO> hello(String username){
        return Result.success(userServerRemote.getByUsername(username));
    }
    
    @GetMapping("gateway")
    public Result gateway() {
    	return Result.success(gatewayServerRemote.getEnableGatewayRoute());
    }

    @GetMapping("getSentinelFlow")
    public Result getSentinelFlow(){
        return Result.success(sentinelFlowControlService.queryFlowControlByProjectName("organization"));
    }

    @GetMapping("getDegradeRule")
    public Result getDegradeRule(){
        return Result.success(sentinelDegradeRuleService.queryByProjectName("gateway-admin"));
    }
}
