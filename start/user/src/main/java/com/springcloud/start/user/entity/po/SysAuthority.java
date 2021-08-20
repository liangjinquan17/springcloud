package com.springcloud.start.user.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 权限表
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_authority")
@ApiModel(value="SysAuthority对象", description="权限表")
public class SysAuthority extends Model<SysAuthority> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "状态 1=可用 0=不可用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "简介")
    @TableField("description")
    private String description;

    @TableField("order_num")
    private Integer orderNum;

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
