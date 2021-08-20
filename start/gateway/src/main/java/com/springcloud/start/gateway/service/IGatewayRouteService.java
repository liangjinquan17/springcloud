package com.springcloud.start.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.start.gateway.entity.po.GatewayRoute;

import java.util.List;

/**
 * <p>
 * 网关路由 服务类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-04
 */
public interface IGatewayRouteService extends IService<GatewayRoute> {

	/**
	 * 查询所有路由信息
	 * @return
	 */
	List<GatewayRoute> getAll();
}
