package com.springcloud.core.exception;

/**
 * Created by zhoutaoo on 2018/6/2.
 */
public class ServiceException extends BaseException {

	private static final long serialVersionUID = 7588938153588635354L;

	//TODO 对业务异常的返回码进行校验，规范到一定范围内
    public ServiceException(ErrorType errorType) {
        super(errorType);
    }

}
