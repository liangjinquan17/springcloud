package com.springcloud.start.user.service.impl;

import com.springcloud.start.user.entity.po.SysAuthority;
import com.springcloud.start.user.dao.SysAuthorityMapper;
import com.springcloud.start.user.enums.UserExpandEnum;
import com.springcloud.start.user.service.IQueryUserExpandAssociated;
import com.springcloud.start.user.service.ISysAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-30
 */
@Service
public class SysAuthorityServiceImpl extends ServiceImpl<SysAuthorityMapper, SysAuthority> implements ISysAuthorityService {

    @Override
    public List<SysAuthority> getByIds(List<String> ids) {
        return baseMapper.getByIds(ids.stream().map(s -> Long.valueOf(s)).collect(Collectors.toList()));
    }

    @Override
    public UserExpandEnum getUserExpandEnum() {
        return UserExpandEnum.USER_ROLE;
    }
}
