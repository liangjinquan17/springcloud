package com.springcloud.sysadmin.organization.entity.param;

import lombok.Data;

@Data
public class UserAddParam {
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 权限
	 */
	private String authorities;
}
