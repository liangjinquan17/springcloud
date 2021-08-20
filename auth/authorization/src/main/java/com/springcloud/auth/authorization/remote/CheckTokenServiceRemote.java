package com.springcloud.auth.authorization.remote;

import java.util.Map;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;

import com.springcloud.authorizationremote.entity.DTO.AuthUserDetailDTO;
import com.springcloud.authorizationremote.remote.ICheckTokenServiceRemote;

@DubboService(version = "1.0.1")
public class CheckTokenServiceRemote implements ICheckTokenServiceRemote {

	@Autowired
	private CheckTokenEndpoint checkTokenEndpoint;

	@Override
	public AuthUserDetailDTO checkToken(String token) {
		try {
			AuthUserDetailDTO dto = null;
			Map<String, ?> map = checkTokenEndpoint.checkToken(token);
			if (!map.isEmpty()) {
				dto = new AuthUserDetailDTO();
				dto.setUserName((String) map.get("user_name"));
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
