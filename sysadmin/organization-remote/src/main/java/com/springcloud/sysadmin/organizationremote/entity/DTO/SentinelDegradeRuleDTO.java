package com.springcloud.sysadmin.organizationremote.entity.DTO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liangjinquan
 */
@Data
public class SentinelDegradeRuleDTO implements java.io.Serializable {

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
     * 慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈
     */
    private Integer count;

    /**
     * 熔断策略，支持慢调用比例/异常比例/异常数策略
     */
    private Integer grade;

    /**
     * 熔断时长，单位为 s
     */
    private Integer timeWindow;

    /**
     * 熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断
     */
    private Integer minRequestAmount;

    /**
     * 统计时长（单位为 ms），如 60*1000 代表分钟级
     */
    private Integer statIntervalMs;

    /**
     * 慢调用比例阈值，仅慢调用比例模式有效
     */
    private BigDecimal slowRatioThreshold;
}
