package com.springcloud.start.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springcloud.start.user.entity.po.SysUsers;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author liangjinquan
 * @since 2021-07-19
 */
public interface SysUsersMapper extends BaseMapper<SysUsers> {

    /**
     * 通过用户名查询用户信息
     * @param username      用户名
     * @return
     */
    SysUsers getByUserName(@Param("username") String username);

    /**
     * 分页查询用户
     * @param page      分页
     * @param username  用户名模糊查询
     * @return
     */
    IPage<SysUsers> getByPage(IPage<SysUsers> page, @Param("username") String username);

    /**
     * 修改用户密码
     * @param username      用户名
     * @param password      密码
     * @return
     */
    int updatePassword(@Param("username") String username, @Param("password")String password);

    /**
     * 修改用户有效性
     * @param username      用户名
     * @param enabled       是否有效（0=无效 1=有效）
     * @return
     */
    int updateEnabled(@Param("username") String username, @Param("enabled")int enabled);

    /**
     * 修改账号是否过期
     * @param username              用户名
     * @param accountNonExpired     是否过期（0=过期 1=未过期）
     * @return
     */
    int updateAccountNonExpired(@Param("username") String username, @Param("accountNonExpired")int accountNonExpired);

    /**
     * 修改密码是否过期
     * @param username                  用户名
     * @param credentialsNonExpired     是否过期（0=过期 1=未过期）
     * @return
     */
    int updateCredentialsNonExpired(@Param("username") String username, @Param("credentialsNonExpired")int credentialsNonExpired);

    /**
     * 修改用户是否锁定
     * @param username              用户名
     * @param accountNonLocked     是否锁定（0=过期 1=未过期）
     * @return
     */
    int updateAccountNonLocked(@Param("username") String username, @Param("accountNonLocked")int accountNonLocked);
}
