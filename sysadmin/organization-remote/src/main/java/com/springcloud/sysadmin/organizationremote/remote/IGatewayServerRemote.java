package com.springcloud.sysadmin.organizationremote.remote;

import java.util.List;

import com.springcloud.sysadmin.organizationremote.entity.DTO.GatewayRouteDTO;

/**
 * 网关服务
 */
public interface IGatewayServerRemote {

	/**
	 * 查询可用路由信息
	 * @return
	 */
    List<GatewayRouteDTO> getEnableGatewayRoute();
}
