package com.springcloud.gateway.gatewayweb.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.sysadmin.organizationremote.entity.DTO.GatewayRouteDTO;
import com.springcloud.sysadmin.organizationremote.remote.IGatewayServerRemote;

import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

/**
 * 动态路由
 */
@Component
public class GatewayConfig implements RouteDefinitionLocator {
	
	@DubboReference(version = "1.0.1")
    private IGatewayServerRemote gatewayServerRemote;
	
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
    	List<GatewayRouteDTO> routes = gatewayServerRemote.getEnableGatewayRoute();
    	if(null != routes && routes.size() > 0) {
    		List<RouteDefinition> routeDefinitions = routes.stream().reduce(new ArrayList<RouteDefinition>(routes.size()), this::reduceRouteDefinition, (l1, l2) -> {return null;});
    		return Flux.fromIterable(routeDefinitions);
    	}
		return Flux.empty();
    }

	/**
	 * 网关信息转化
	 * @param routeDefinitions
	 * @param dto
	 * @return
	 */
	private List<RouteDefinition> reduceRouteDefinition(List<RouteDefinition> routeDefinitions, GatewayRouteDTO dto){
    	try {
    		RouteDefinition routeDefinition = new RouteDefinition();
        	routeDefinition.setId(dto.getRouteId());
			routeDefinition.setUri(new URI(dto.getUri()));
			// 路由转化
			if(null != dto.getPredicate()) {
				routeDefinition.setPredicates(converPredicateDefinition(dto.getPredicate()));
			}
			// 过滤条件转化
			if(null != dto.getFilters()) {
				routeDefinition.setFilters(converFilterDefinition(dto.getFilters()));
			}
			routeDefinitions.add(routeDefinition);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    	
    	return routeDefinitions;
    }

	/**
	 * 路由转化
	 * @param predicateStr
	 * @return
	 */
	private List<PredicateDefinition> converPredicateDefinition(String predicateStr){
		List<PredicateDefinition> predicateDefinitions = new ArrayList<PredicateDefinition>();
		JSONObject predicatesJSONArray = JSONObject.parseObject(predicateStr);
		predicatesJSONArray.forEach((key, value) -> {
			predicateDefinitions.add(new PredicateDefinition(key+"="+value));
		});
		return predicateDefinitions;
	}

	/**
	 * 过滤条件转化
	 * @param filterStr
	 * @return
	 */
	private List<FilterDefinition> converFilterDefinition(String filterStr){
		List<FilterDefinition> filterDefinitions = JSONObject.parseArray(filterStr, FilterDefinition.class);
		return filterDefinitions;
	}
}
