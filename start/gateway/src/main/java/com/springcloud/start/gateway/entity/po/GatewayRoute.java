package com.springcloud.start.gateway.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 网关路由
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_gateway_route")
@ApiModel(value="GatewayRoute对象", description="网关路由")
public class GatewayRoute extends Model<GatewayRoute> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "路由id")
    @TableField("route_id")
    private String routeId;

    @ApiModelProperty(value = "跳转地址")
    @TableField("uri")
    private String uri;

    @ApiModelProperty(value = "判定器")
    @TableField("predicate")
    private String predicate;

    @ApiModelProperty(value = "拦截器")
    @TableField("filters")
    private String filters;

    @ApiModelProperty(value = "可用状态1=true, 0=false")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建人")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;


    @Override
	public Serializable pkVal() {
        return this.id;
    }

}
