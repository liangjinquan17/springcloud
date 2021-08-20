package com.springcloud.gateway.gatewayweb.test;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.core.entity.vo.Result;
import com.springcloud.core.exception.SystemErrorType;

public class Test {

	public static void main(String[] args) {
		
		System.out.println(JSONObject.toJSONString(Result.fail(SystemErrorType.INVALID_TOKEN)));
	}
}
