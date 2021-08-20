package com.springcloud.auth.authorization.service;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springcloud.sysadmin.organizationremote.entity.DTO.UserDTO;
import com.springcloud.sysadmin.organizationremote.remote.IUserServerRemote;

@Service("authUserDetailService")
public class AuthUserDetailService implements UserDetailsService {

	@DubboReference(version = "1.0.1")
	private IUserServerRemote userServerRemoteImpl;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (null == username) {
			throw new BadCredentialsException("用户名不能为空");
		}
		UserDTO userDTO = userServerRemoteImpl.getByUsername(username);

		if (null == userDTO) {
			throw new BadCredentialsException("用户名["+username+"]错误");
		}

		// conver
		String authoritie = userDTO.getAuthorities();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (null != authoritie) {
			String[] authoritiestrs = authoritie.split(",");
			for (String role : authoritiestrs) {
				authorities.add(new SimpleGrantedAuthority(role));
			}
		}
		User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEnabled(),
				userDTO.getAccountNonExpired(), userDTO.getCredentialsNonExpired(), userDTO.getAccountNonLocked(),
				authorities);
		return user;
	}

}
