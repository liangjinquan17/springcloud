package com.springcloud.start.user.storage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springcloud.start.user.entity.po.SysAuthority;
import com.springcloud.start.user.entity.po.SysRoles;
import com.springcloud.start.user.entity.po.SysUsers;
import com.springcloud.start.user.enums.UserExpandEnum;
import com.springcloud.start.user.service.ISysAuthorityService;
import com.springcloud.start.user.service.ISysRolesService;
import com.springcloud.start.user.service.ISysUserExpandAssociatedService;
import com.springcloud.start.user.service.ISysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户服务封装
 */
@Service
public class UserStorageService {

    @Autowired
    private ISysUsersService sysUsersService;
    @Autowired
    private ISysRolesService sysRolesService;
    @Autowired
    private ISysAuthorityService sysAuthorityService;
    @Autowired
    private ISysUserExpandAssociatedService sysUserExpandAssociatedService;

    /**
     * 新增用户
     * @param users     用户信息
     * @return
     */
    public boolean save(SysUsers users){
        return sysUsersService.save(users);
    }

    /**
     * 查询用户信息
     * @param userId    用户id
     * @return
     */
    public SysUsers getSysUsers(Long userId){
        return sysUsersService.getById(userId);
    }

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    public Optional<SysUsers> getByUserName(String username){
        SysUsers users = sysUsersService.getByUserName(username);
        return Optional.ofNullable(users);
    }

    /**
     * 分页查询用户
     * @param page      分页
     * @param username  用户名模糊查询
     * @return
     */
    public IPage<SysUsers> getByPage(IPage<SysUsers> page, String username){
        return sysUsersService.getByPage(page, username);
    }

    /**
     * 修改用户密码
     * @param username      用户名
     * @param password      密码
     * @return
     */
    public boolean updatePassword(String username, String password){
        return sysUsersService.updatePassword(username, password);
    }

    /**
     * 修改用户有效性
     * @param username      用户名
     * @param enabled       是否有效
     * @return
     */
    public boolean updateEnabled(String username, boolean enabled){
        return sysUsersService.updateEnabled(username, enabled);
    }

    /**
     * 修改账号是否过期
     * @param username              用户名
     * @param accountNonExpired     是否未过期
     * @return
     */
    public boolean updateAccountNonExpired(String username, boolean accountNonExpired){
        return sysUsersService.updateAccountNonExpired(username, accountNonExpired);
    }

    /**
     * 修改密码是否过期
     * @param username                  用户名
     * @param credentialsNonExpired     是否未过期
     * @return
     */
    public boolean updateCredentialsNonExpired(String username, boolean credentialsNonExpired){
        return sysUsersService.updateCredentialsNonExpired(username, credentialsNonExpired);
    }

    /**
     * 修改用户是否锁定
     * @param username              用户名
     * @param accountNonLocked     是否未锁定
     * @return
     */
    public boolean updateAccountNonLocked(String username, boolean accountNonLocked){
        return sysUsersService.updateAccountNonLocked(username, accountNonLocked);
    }

    /**
     * 查询用户角色信息
     * @param userId    用户id
     * @return
     */
    public List<SysRoles> getSysRoles(Long userId){
        return sysUserExpandAssociatedService.queryUserExpandAssociated(userId, sysRolesService);
    }

    /**
     * 查询用户权限信息
     * @param userId    用户id
     * @return
     */
    public List<SysAuthority> getSysAuthoritys(Long userId){
        return sysUserExpandAssociatedService.queryUserExpandAssociated(userId, sysAuthorityService);
    }

    /**
     * 绑定用户角色
     * @param userId            用户id
     * @param roleIds           角色id列表
     * @return
     */
    public boolean bindUserRole(Long userId, List<Long> roleIds, String createBy){
        return sysUserExpandAssociatedService.updateUserExpandAssociated(userId, roleIds.stream().map(id -> id.toString()).collect(Collectors.toList()), UserExpandEnum.USER_ROLE, createBy);
    }

    /**
     * 绑定用户权限
     * @param userId                用户id
     * @param authorityIds          权限id列表
     * @return
     */
    public boolean bindUserAuthority(Long userId, List<Long> authorityIds, String createBy){
        return sysUserExpandAssociatedService.updateUserExpandAssociated(userId, authorityIds.stream().map(id -> id.toString()).collect(Collectors.toList()), UserExpandEnum.USER_AUTHORITY, createBy);
    }
}
