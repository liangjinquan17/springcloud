package com.springcloud.authorizationremote.remote;

import com.springcloud.authorizationremote.entity.DTO.AuthUserDetailDTO;

public interface ICheckTokenServiceRemote {

	/**
	 * 校验token是否有效
	 * @param token
	 * @return
	 */
	AuthUserDetailDTO checkToken(String token);
}
