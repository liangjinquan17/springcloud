package com.springcloud.sysadmin.organization.controller;

import com.springcloud.core.entity.vo.Result;
import com.springcloud.core.enums.StatusEnum;
import com.springcloud.start.user.entity.po.SysAuthority;
import com.springcloud.start.user.service.ISysAuthorityService;
import com.springcloud.sysadmin.organization.entity.param.AuthorityAddParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  权限 前端控制器
 * </p>
 *
 * @author liangjinquan
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private ISysAuthorityService authorityService;

    /**
     * 新增
     * @return
     */
    @PostMapping("add")
    public Result<Boolean> addAuthority(@RequestBody AuthorityAddParam param){
        SysAuthority authority = new SysAuthority();
        authority.setName(param.getName());
        authority.setDescription(param.getDescription());
        authority.setOrderNum(1);
        authority.setStatus(StatusEnum.USABLE.getStatus());
        return Result.success(authorityService.save(authority));
    }

}
