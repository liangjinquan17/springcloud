package com.springcloud.sysadmin.organization.controller;

import com.springcloud.core.entity.vo.Result;
import com.springcloud.core.enums.StatusEnum;
import com.springcloud.start.user.entity.po.SysRoles;
import com.springcloud.start.user.service.ISysRolesService;
import com.springcloud.sysadmin.organization.entity.param.RoleAddParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  角色 前端控制器
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private ISysRolesService sysRolesService;

    /**
     * 新增角色
     * @return
     */
    @PostMapping("add")
    public Result<Boolean> addRole(@RequestBody RoleAddParam param){
        SysRoles roles = new SysRoles();
        roles.setName(param.getName());
        roles.setDescription(param.getDescription());
        roles.setOrderNum(1);
        roles.setStatus(StatusEnum.USABLE.getStatus());
        return Result.success(sysRolesService.save(roles));
    }

}
