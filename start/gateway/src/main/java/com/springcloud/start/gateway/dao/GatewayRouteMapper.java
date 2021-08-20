package com.springcloud.start.gateway.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.start.gateway.entity.po.GatewayRoute;

import java.util.List;

/**
 * <p>
 * 网关路由 Mapper 接口
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-04
 */
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {

	/**
	 * 查询所有路由信息
	 * @return
	 */
	List<GatewayRoute> getAll();
}
