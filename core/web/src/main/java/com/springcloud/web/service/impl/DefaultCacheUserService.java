package com.springcloud.web.service.impl;

import java.util.Optional;
import java.util.function.Consumer;

import com.springcloud.web.entity.DTO.UserDTO;
import com.springcloud.web.service.ICacheUserStrategy;

/**
 * threadlocal缓存用户信息
 * @author liangjinquan
 *
 */
public class DefaultCacheUserService implements ICacheUserStrategy{
	
	private static ThreadLocal<UserDTO> threadLocal = new ThreadLocal<UserDTO>();

	@Override
	public void setUser(UserDTO user) {
		threadLocal.set(user);
	}
	
	@Override
	public Optional<UserDTO> getUser() {
		return Optional.ofNullable(threadLocal.get());
	}

	@Override
	public void userIsNotExistsExpand(Consumer<UserDTO> consumer) {
		consumer.accept(threadLocal.get());
	}
}
