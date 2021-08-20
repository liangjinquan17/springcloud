package com.springcloud.start.sentinel.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * sentinel熔断降级规则
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sentinel_degrade_rule")
@ApiModel(value="SentinelDegradeRule对象", description="sentinel熔断降级规则")
public class SentinelDegradeRule extends Model<SentinelDegradeRule> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "服务名称")
    @TableField("project_name")
    private String projectName;

    @ApiModelProperty(value = "资源名，即限流规则的作用对象")
    @TableField("resource")
    private String resource;

    @ApiModelProperty(value = "慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值")
    @TableField("count")
    private Integer count;

    @ApiModelProperty(value = "熔断策略，支持慢调用比例/异常比例/异常数策略")
    @TableField("grade")
    private Integer grade;

    @ApiModelProperty(value = "熔断时长，单位为 s")
    @TableField("time_window")
    private Integer timeWindow;

    @ApiModelProperty(value = "熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断")
    @TableField("min_request_amount")
    private Integer minRequestAmount;

    @ApiModelProperty(value = "统计时长（单位为 ms），如 60*1000 代表分钟级")
    @TableField("stat_interval_ms")
    private Integer statIntervalMs;

    @ApiModelProperty(value = "慢调用比例阈值，仅慢调用比例模式有效")
    @TableField("slow_ratio_threshold")
    private BigDecimal slowRatioThreshold;

    @ApiModelProperty(value = "状态1=可用 0=不可用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updated_time")
    private Date updatedTime;

    @ApiModelProperty(value = "创建人")
    @TableField("created_by")
    private String createdBy;

    @ApiModelProperty(value = "更新人")
    @TableField("updated_by")
    private String updatedBy;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
