package com.springcloud.gatewayweb.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RouteDefinitionRepositoryConfig implements RouteDefinitionRepository{

	@Override
	public Flux<RouteDefinition> getRouteDefinitions() {
		List<RouteDefinition> list = new ArrayList<RouteDefinition>();
		RouteDefinition routeDefinition = new RouteDefinition();

		// route_id: authorization-server
		//  uri: lb://authorization-server:6000
	      // predicates: [{"name":"Path","args":{"pattern":"/authorization-server/**"}}]
		//  filters: [{"name":"StripPrefix","args":{"parts":"1"}}]

		routeDefinition.setId("dubbo-consumer");
		try {
		    routeDefinition.setUri(new URI("lb://user-consumer"));
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
		PredicateDefinition predicateDefinition = new PredicateDefinition();
		predicateDefinition.setName("Path");
		Map<String, String> predicates = new HashMap<>();
		predicates.put("pattern", "/user/**");
		predicateDefinition.setArgs(predicates);

		Map<String, String> filters = new HashMap<>();
		filters.put("parts", "1");
		FilterDefinition filterDefinition = new FilterDefinition();
		filterDefinition.setName("StripPrefix");
		filterDefinition.setArgs(filters);

		routeDefinition.setPredicates(Arrays.asList(predicateDefinition));
		routeDefinition.setFilters(Arrays.asList(filterDefinition));

		list.add(routeDefinition);
		return Flux.fromIterable(list);

	}

	@Override
	public Mono<Void> delete(Mono<String> routeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		// TODO Auto-generated method stub
		return null;
	}

}
