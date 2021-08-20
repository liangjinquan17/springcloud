package com.springcloud.start.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.start.user.dao.SysUsersMapper;
import com.springcloud.start.user.entity.po.SysUsers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-19
 */
@Service
public class SysUsersServiceImpl extends ServiceImpl<SysUsersMapper, SysUsers> implements com.springcloud.start.user.service.ISysUsersService {

    @Value("${startuser.user}")
    private String name;

    @Override
    public SysUsers getByUserName(String username) {
        SysUsers users = baseMapper.getByUserName(username);
        if(null != users){
            return users;
        }
        return null;
    }

    @Override
    public IPage<SysUsers> getByPage(IPage<SysUsers> page, String username) {
        return baseMapper.getByPage(page, username);
    }

    @Override
    public boolean updatePassword(String username, String password) {
        return baseMapper.updatePassword(username, password) > 0;
    }

    @Override
    public boolean updateEnabled(String username, boolean enabled) {
        return baseMapper.updateEnabled(username, enabled ? 1 : 0) > 0;
    }

    @Override
    public boolean updateAccountNonExpired(String username, boolean accountNonExpired) {
        return baseMapper.updateAccountNonExpired(username, accountNonExpired ? 1 : 0) > 0;
    }

    @Override
    public boolean updateCredentialsNonExpired(String username, boolean credentialsNonExpired) {
        return baseMapper.updateCredentialsNonExpired(username, credentialsNonExpired ? 1 : 0) > 0;
    }

    @Override
    public boolean updateAccountNonLocked(String username, boolean accountNonLocked) {
        return baseMapper.updateAccountNonLocked(username, accountNonLocked ? 1 : 0) > 0;
    }

}
