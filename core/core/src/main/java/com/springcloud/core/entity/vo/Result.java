package com.springcloud.core.entity.vo;

import java.time.Instant;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.springcloud.core.exception.BaseException;
import com.springcloud.core.exception.ErrorType;
import com.springcloud.core.exception.SystemErrorType;

/**
 * rest请求的返回模型，所有rest正常都返回该类的对象
 * @author liangjinquan
 *
 * @param <T>
 */
public class Result<T> {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESG = "处理成功";

    /**
     * 处理结果code
     */
    private String code;
    /**
     * 处理结果描述信息
     */
    private String msg;
    /**
     * 请求结果生成时间戳
     */
    private Instant time;
    /**
     * 处理结果数据信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public T getData() {
        return data;
    }

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     */
    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMesg();
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     * @param data
     */
    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param mesg
     * @param data
     */
    private Result(String code, String mesg, T data) {
        this.code = code;
        this.msg = mesg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }
    public static <T> Result<T> fail(T data) {
        return new Result<T>(SystemErrorType.SYSTEM_ERROR, data);
    }

    public static <T> Result<T> alert(String msg) {
        return new Result<T>(SUCCESSFUL_CODE, msg, null);
    }


    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result<Object> success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result<Object> fail() {
        return new Result<Object>(SystemErrorType.SYSTEM_ERROR);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException
     * @return Result
     */
    public static Result<Object> fail(BaseException baseException) {
        return fail(baseException, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result<Object> fail(BaseException baseException, Object data) {
        return new Result<>(baseException.getErrorType(), data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static Result<Object> fail(ErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static Result<Object> fail(ErrorType errorType) {
        return Result.fail(errorType, null);
    }

    /**
     * 系统异常类并返回异常消息
     *
     * @param msg
     * @return Result
     */
    public static Result<Object> fail(String msg) {
        return new Result<>(SystemErrorType.SYSTEM_ERROR.getCode(), msg, null);
    }


    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public void setData(T data) {
		this.data = data;
	}
    
}
