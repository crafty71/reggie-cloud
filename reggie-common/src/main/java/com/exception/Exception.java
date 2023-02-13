package com.exception;

/**
 * 描述：     统一异常
 */
public class Exception extends RuntimeException {

    private final Integer code;
    private final String message;

    public Exception(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Exception(ExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
