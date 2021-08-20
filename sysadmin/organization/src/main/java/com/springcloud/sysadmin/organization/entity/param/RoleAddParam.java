package com.springcloud.sysadmin.organization.entity.param;

import lombok.Data;

@Data
public class RoleAddParam {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;
}
