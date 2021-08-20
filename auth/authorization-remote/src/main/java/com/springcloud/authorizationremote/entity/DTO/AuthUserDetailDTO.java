package com.springcloud.authorizationremote.entity.DTO;

import lombok.Data;

@Data
public class AuthUserDetailDTO implements java.io.Serializable{

	private static final long serialVersionUID = 9212283096915259243L;
	/**
	 * 用户名
	 */
	private String userName;
}
