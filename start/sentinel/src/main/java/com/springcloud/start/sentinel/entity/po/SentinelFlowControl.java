package com.springcloud.start.sentinel.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * sentinel流量控制规则
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sentinel_flow_control")
@ApiModel(value="SentinelFlowControl对象", description="sentinel流量控制规则")
public class SentinelFlowControl extends Model<SentinelFlowControl> {

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

    @ApiModelProperty(value = "限流阈值")
    @TableField("count")
    private Integer count;

    @ApiModelProperty(value = "限流阈值类型（QPS 或并发线程数）")
    @TableField("grade")
    private Integer grade;

    @ApiModelProperty(value = "流控针对的调用来源，若为 default 则不区分调用来源")
    @TableField("limitApp")
    private String limitapp;

    @ApiModelProperty(value = "调用关系限流策略")
    @TableField("strategy")
    private Integer strategy;

    @ApiModelProperty(value = "流量控制效果（直接拒绝、Warm Up、匀速排队）")
    @TableField("controlBehavior")
    private Integer controlbehavior;

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
