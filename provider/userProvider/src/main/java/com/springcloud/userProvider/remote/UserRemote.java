package com.springcloud.userProvider.remote;

import java.util.Date;

import com.alibaba.dubbo.config.annotation.Service;
import com.springcloud.userRemote.IUserRemote;

@Service(group = "user", version = "1.0.1")
public class UserRemote implements IUserRemote{

	@Override
	public String getUserNameById(Long id) {
		// TODO Auto-generated method stub
		return "hello user provider"+new Date();
	}

}
