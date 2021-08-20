package com.springcloud.web.service;

import java.util.Optional;
import java.util.function.Consumer;

import com.springcloud.web.entity.DTO.UserDTO;
import com.springcloud.web.service.impl.DefaultCacheUserService;

/**
 * 缓存用户信息
 * @author liangjinquan
 *
 */
public interface ICacheUserStrategy {
	
	/**
	 * 缓存用户信息
	 * @param user
	 */
	void setUser(UserDTO user);
	
	/**
	 *  获取用户信息
	 * @return
	 */
	Optional<UserDTO> getUser();

	/**
	 * 用户非空的时候拓展操作
	 * @param consumer
	 */
	void userIsNotExistsExpand(Consumer<UserDTO> consumer);
	
	/**
	 * 默认缓存用户策略
	 * @return
	 */
	static ICacheUserStrategy getDefaultStrategy() {
		return new DefaultCacheUserService();
	}
	
}
