package com.springcloud.core.exception;

public enum SystemErrorType implements ErrorType {

    SYSTEM_ERROR("-1", "系统异常"),
    INVALID_TOKEN("-2", "未登录");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    SystemErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getMesg() {
		// TODO Auto-generated method stub
		return this.mesg;
	}

}
