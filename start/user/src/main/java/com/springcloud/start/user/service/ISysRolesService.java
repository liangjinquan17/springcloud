package com.springcloud.start.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.start.user.entity.po.SysRoles;


/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
public interface ISysRolesService extends IService<SysRoles>, IQueryUserExpandAssociated<SysRoles>{

}
