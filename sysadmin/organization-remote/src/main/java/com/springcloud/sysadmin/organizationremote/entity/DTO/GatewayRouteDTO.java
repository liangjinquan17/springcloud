package com.springcloud.sysadmin.organizationremote.entity.DTO;

import lombok.Data;

/**
 * 网关路由信息
 * @author liangjinquan
 *
 */
@Data
public class GatewayRouteDTO implements java.io.Serializable{

	private static final long serialVersionUID = 8583576362165331681L;

	/**
     * 路由id
     */
    private String routeId;

    /**
     * 跳转地址
     */
    private String uri;

    /**
     * 判定器
     */
    private String predicate;

    /**
     * 拦截器
     */
    private String filters;
}
