package com.qianduner.utils.core.exception;

import lombok.Data;

/**
 * 自定义异常
 *
 * @author Laver
 */
public class UException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private int code = 500;

    public UException(String message) {
        super(message);
        this.message = message;
    }

    public UException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public UException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public UException(String message, int code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }

    public UException(Throwable e) {
        super(e);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
