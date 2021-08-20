package com.springcloud.start.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.start.user.dao.SysRolesMapper;
import com.springcloud.start.user.entity.po.SysRoles;
import com.springcloud.start.user.enums.UserExpandEnum;
import com.springcloud.start.user.service.ISysRolesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
@Service
public class SysRolesServiceImpl extends ServiceImpl<SysRolesMapper, SysRoles> implements ISysRolesService {

    @Override
    public List<SysRoles> getByIds(List<String> ids) {
        return baseMapper.getByIds(ids.stream().map(s -> Long.valueOf(s)).collect(Collectors.toList()));
    }

    @Override
    public UserExpandEnum getUserExpandEnum() {
        return UserExpandEnum.USER_AUTHORITY;
    }
}
