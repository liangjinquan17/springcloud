package com.springcloud.start.user.service;

import com.springcloud.start.user.entity.po.SysAuthority;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
public interface ISysAuthorityService extends IService<SysAuthority>, IQueryUserExpandAssociated<SysAuthority>{

}
