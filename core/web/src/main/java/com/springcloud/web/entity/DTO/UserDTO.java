package com.springcloud.web.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	
	public UserDTO() {}
	
	/**
	 * 用户名
	 */
	private String username;
}
