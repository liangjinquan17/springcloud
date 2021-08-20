package com.springcloud.sysadmin.organization.remote;

import com.springcloud.start.user.entity.po.SysUsers;
import com.springcloud.start.user.storage.UserStorageService;
import com.springcloud.sysadmin.organizationremote.entity.DTO.UserDTO;
import com.springcloud.sysadmin.organizationremote.remote.IUserServerRemote;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@DubboService(version = "1.0.1")
public class UserServerRemoteImpl implements IUserServerRemote{
	
	@Autowired
	private UserStorageService userStorageService;

	@Override
	public UserDTO getByUsername(String username) {
		if(null != username && !"".equals(username)) {
			Optional<SysUsers> optional = userStorageService.getByUserName(username);
			if(optional.isPresent()) {
				UserDTO userDTO = new UserDTO();
				SysUsers users = optional.get();
				userDTO.setId(users.getId());
				userDTO.setUsername(users.getUsername());
				userDTO.setPassword(users.getPassword());
				userDTO.setName(users.getName());
				userDTO.setAuthorities(users.getAuthorities());
				userDTO.setEnabled(users.getEnabled());
				userDTO.setAccountNonExpired(users.getAccountNonExpired());
				userDTO.setCredentialsNonExpired(users.getCredentialsNonExpired());
				userDTO.setAccountNonLocked(users.getAccountNonLocked());
				return userDTO;
			}
		}
		return null;
	}

}
