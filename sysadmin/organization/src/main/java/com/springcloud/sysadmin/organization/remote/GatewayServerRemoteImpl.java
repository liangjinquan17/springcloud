package com.springcloud.sysadmin.organization.remote;

import java.util.List;
import java.util.stream.Collectors;

import com.springcloud.start.gateway.entity.po.GatewayRoute;
import com.springcloud.start.gateway.service.IGatewayRouteService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.springcloud.sysadmin.organizationremote.entity.DTO.GatewayRouteDTO;
import com.springcloud.sysadmin.organizationremote.remote.IGatewayServerRemote;

/**
 * 网关路由服务
 * @author liangjinquan
 *
 */
@DubboService(version = "1.0.1")
public class GatewayServerRemoteImpl implements IGatewayServerRemote {
	
	@Autowired
	private IGatewayRouteService gatewayRouteServiceImpl;

    @Override
    public List<GatewayRouteDTO> getEnableGatewayRoute() {
    	List<GatewayRoute> list = gatewayRouteServiceImpl.getAll();
    	if(null != list && list.size() > 0) {
    		return list.stream().filter(po -> po.getStatus().compareTo(1) == 0 ).map(gatewayRoute -> reduceGatewayRoute(gatewayRoute)).collect(Collectors.toList());
    	}
    	return null;
    }
    
    private GatewayRouteDTO reduceGatewayRoute(GatewayRoute po){
    	GatewayRouteDTO dto = new GatewayRouteDTO();
    	dto.setRouteId(po.getRouteId());
    	dto.setUri(po.getUri());
    	dto.setPredicate(po.getPredicate());
    	dto.setFilters(po.getFilters());
    	return dto;
    } 
}
