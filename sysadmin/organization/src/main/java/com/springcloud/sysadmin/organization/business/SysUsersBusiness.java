package com.springcloud.sysadmin.organization.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springcloud.start.user.entity.po.SysUsers;
import com.springcloud.start.user.storage.UserStorageService;
import com.springcloud.sysadmin.organization.entity.dto.UserDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户仓储层
 */
@Service
public class SysUsersBusiness{

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserStorageService userStorageService;

    /**
     * 新增用户
     * @param users     用户对象
     * @return
     */
    public boolean saveUser(SysUsers users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userStorageService.save(users);
    }

    /**
     * 修改用户密码
     * @param username      用户名
     * @param password      新密码
     * @param oldPassword   旧密码（非空则校验，否则不校验）
     * @return
     */
    public boolean updatePassword( String username, String password, String oldPassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return userStorageService.updatePassword(username, passwordEncoder.encode(password));
    }

    /**
     * 查询用户详情
     * @param username              用户名
     * @return
     */
    public UserDetailDTO getUserDetail(String username){
        Optional<SysUsers> optionalSysUsers = userStorageService.getByUserName(username);
        if(optionalSysUsers.isPresent()){
            UserDetailDTO detailDTO = new UserDetailDTO();
            SysUsers users = optionalSysUsers.get();
            detailDTO.setUsers(users);
            detailDTO.setRoles(userStorageService.getSysRoles(users.getId()));
            detailDTO.setAuthoritys(userStorageService.getSysAuthoritys(users.getId()));
            return detailDTO;
        }
        return null;
    }


}
