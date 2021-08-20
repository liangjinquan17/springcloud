package com.springcloud.sysadmin.organization.entity.param;

import lombok.Data;

@Data
public class UpdateUserPasswordParam {

    /**
     * 用户名
     */
    private String username;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 旧密码
     */
    private String oldPassword;
}
