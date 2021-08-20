package com.springcloud.sysadmin.organization.entity.param;

import lombok.Data;

@Data
public class AuthorityAddParam {

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限描述
     */
    private String description;
}
