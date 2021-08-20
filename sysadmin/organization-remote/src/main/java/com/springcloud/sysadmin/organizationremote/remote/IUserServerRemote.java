package com.springcloud.sysadmin.organizationremote.remote;

import com.springcloud.sysadmin.organizationremote.entity.DTO.UserDTO;

public interface IUserServerRemote {

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	UserDTO getByUsername(String username);
}
