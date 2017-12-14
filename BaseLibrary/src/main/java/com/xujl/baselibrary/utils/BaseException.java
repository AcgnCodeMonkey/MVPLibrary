package com.xujl.baselibrary.utils;

/**
 * Created by Administrator on 2016/9/7.
 * 自定义异常基类
 */
public class BaseException extends Exception {
    public BaseException () {
    }

    public BaseException (String message) {
        super(message);
    }

    @Override
    public Throwable fillInStackTrace () {
        return this;
    }
}
