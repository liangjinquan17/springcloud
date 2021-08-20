package com.springcloud.start.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.start.gateway.dao.GatewayRouteMapper;
import com.springcloud.start.gateway.entity.po.GatewayRoute;
import com.springcloud.start.gateway.service.IGatewayRouteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 网关路由 服务实现类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-04
 */
@Service
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements IGatewayRouteService {

	@Override
	public List<GatewayRoute> getAll() {
		return baseMapper.getAll();
	}


}
