package com.springcloud.auth.authorization.oauth.password;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码校验
 * @author liangjinquan
 *
 */
public class PasswordAuthenticationProvider implements AuthenticationProvider{
	
	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;
	
	public PasswordAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		UserDetails userDetail = userDetailsService.loadUserByUsername((String)authentication.getPrincipal());
		if(passwordEncoder.matches((String)authentication.getCredentials(), userDetail.getPassword())) {
			authentication.setAuthenticated(true);
			// 填充信息，这个信息可被破解。
			Map<String, String> map =new HashMap<String,String>();
			map.put("name", "神烦阿噗");
			
			((PasswordAuthentication)authentication).setDetails(map);
			return authentication;
		}else {
			// 密码错误
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return PasswordAuthentication.class.isAssignableFrom(authentication);
	}

}
