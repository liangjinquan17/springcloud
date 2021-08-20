package com.springcloud.sysadmin.organization.entity.param;

import lombok.Data;

import java.util.List;

@Data
public class BindUserAuthorityParam {

    private Long userId;
    private List<Long> authorityIds;
}
