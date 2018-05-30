package com.rogercw.exception;

/**
 * Created by 1 on 2018/5/30.
 * 自定义异常类
 */
public class CustomException extends Exception{
    //异常信息
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
    }
}
