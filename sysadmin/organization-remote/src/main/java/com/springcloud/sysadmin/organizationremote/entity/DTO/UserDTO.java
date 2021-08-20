package com.springcloud.sysadmin.organizationremote.entity.DTO;

import lombok.Data;

/**
 * 用户
 * @author liangjinquan
 *
 */
@Data
public class UserDTO implements java.io.Serializable {

	private static final long serialVersionUID = 7029715758843441189L;

	/**
	 * 用户id
	 */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码密文
     */
    private String password;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * oauth2权限
     */
    private String authorities;

    /**
     * 是否有效用户
     */
    private Boolean enabled;

    /**
     * 账号是否未过期
     */
    private Boolean accountNonExpired;

    /**
     * 密码是否未过期
     */
    private Boolean credentialsNonExpired;

    /**
     * 是否未锁定
     */
    private Boolean accountNonLocked;
}
