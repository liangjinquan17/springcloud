package com.springcloud.start.user.entity.po;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.springcloud.start.user.entity.dto.ApplicationDetailDTO;
import com.springcloud.start.user.enums.UserExpandEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户属性拓展表
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_expand_associated")
@ApiModel(value="SysUserExpandAssociated对象", description="用户属性拓展表")
public class SysUserExpandAssociated extends Model<SysUserExpandAssociated> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "拓展类型二进制转十进制 1=角色 2=权限")
    @TableField("expand")
    private Integer expand;

    @ApiModelProperty(value = "动态拓展信息")
    @TableField("application")
    private String application;

    @ApiModelProperty(value = "可用状态：1=可用 2=不可用")
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
