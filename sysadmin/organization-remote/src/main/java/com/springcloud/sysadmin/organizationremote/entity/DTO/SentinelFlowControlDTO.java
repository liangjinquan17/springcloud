package com.springcloud.sysadmin.organizationremote.entity.DTO;

import lombok.Data;

/**
 * sentinel流量控制规则
 * @author liangjinquan
 *
 */
@Data
public class SentinelFlowControlDTO implements java.io.Serializable{

	private static final long serialVersionUID = -8150977664905850896L;

	/**
     * 主键id
     */
    private Long id;

    /**
     * 服务名称
     */
    private String projectName;

    /**
     * 资源名，即限流规则的作用对象
     */
    private String resource;

    /**
     * 限流阈值
     */
    private Integer count;

    /**
     * 限流阈值类型（QPS 或并发线程数）
     */
    private Integer grade;

    /**
     * 流控针对的调用来源，若为 default 则不区分调用来源
     */
    private String limitapp;

    /**
     * 调用关系限流策略
     */
    private Integer strategy;

    /**
     * 流量控制效果（直接拒绝、Warm Up、匀速排队）
     */
    private Integer controlbehavior;
}
