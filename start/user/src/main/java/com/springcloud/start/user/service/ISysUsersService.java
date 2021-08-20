package com.springcloud.start.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.start.user.entity.po.SysUsers;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-19
 */
public interface ISysUsersService extends IService<SysUsers> {

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    SysUsers getByUserName(String username);

    /**
     * 分页查询用户
     * @param page      分页
     * @param username  用户名模糊查询
     * @return
     */
    IPage<SysUsers> getByPage(IPage<SysUsers> page,  String username);

    /**
     * 修改用户密码
     * @param username      用户名
     * @param password      密码
     * @return
     */
    boolean updatePassword(String username, String password);

    /**
     * 修改用户有效性
     * @param username      用户名
     * @param enabled       是否有效
     * @return
     */
    boolean updateEnabled(String username, boolean enabled);

    /**
     * 修改账号是否过期
     * @param username              用户名
     * @param accountNonExpired     是否未过期
     * @return
     */
    boolean updateAccountNonExpired(String username, boolean accountNonExpired);

    /**
     * 修改密码是否过期
     * @param username                  用户名
     * @param credentialsNonExpired     是否未过期
     * @return
     */
    boolean updateCredentialsNonExpired(String username, boolean credentialsNonExpired);

    /**
     * 修改用户是否锁定
     * @param username              用户名
     * @param accountNonLocked     是否未锁定
     * @return
     */
    boolean updateAccountNonLocked(String username, boolean accountNonLocked);
}
