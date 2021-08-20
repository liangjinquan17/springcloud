package com.springcloud.sysadmin.organization.entity.dto;

import com.springcloud.start.user.entity.po.SysAuthority;
import com.springcloud.start.user.entity.po.SysRoles;
import com.springcloud.start.user.entity.po.SysUsers;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailDTO {
    private SysUsers users;
    private List<SysRoles> roles;
    private List<SysAuthority> authoritys;
}
