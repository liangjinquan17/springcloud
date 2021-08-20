package com.springcloud.start.user.entity.po;

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
 * 用户表
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_users")
@ApiModel(value="SysUsers对象", description="用户表")
public class SysUsers extends Model<SysUsers> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户密码密文")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "oauth2权限")
    @TableField("authorities")
    private String authorities;

    @ApiModelProperty(value = "是否有效用户")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "账号是否未过期")
    @TableField("account_non_expired")
    private Boolean accountNonExpired;

    @ApiModelProperty(value = "密码是否未过期")
    @TableField("credentials_non_expired")
    private Boolean credentialsNonExpired;

    @ApiModelProperty(value = "是否未锁定")
    @TableField("account_non_locked")
    private Boolean accountNonLocked;

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
