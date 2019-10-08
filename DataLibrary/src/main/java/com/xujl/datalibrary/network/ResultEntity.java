package com.xujl.datalibrary.network;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * Created by xujl on 2017/9/26.
 * 网络请求结果封装实体类
 */

public class ResultEntity {
    private String resultJson;
    private @ResultCode int errorCode;
    private String errorString;

    public ResultEntity (String resultJson) {
        this.resultJson = resultJson;
    }

    public ResultEntity (String resultJson, @ResultCode int errorCode) {
        this.resultJson = resultJson;
        this.errorCode = errorCode;
    }

    public ResultEntity (@ResultCode int errorCode, String errorString) {
        this.errorCode = errorCode;
        this.errorString = errorString;
    }

    public void setResultJson (String resultJson) {
        this.resultJson = resultJson;
    }

    public void setErrorCode (@ResultCode int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorString (String errorString) {
        this.errorString = errorString;
    }

    public String getResultJson () {
        return resultJson;
    }

    public int getErrorCode () {
        return errorCode;
    }

    public String getErrorString () {
        return errorString;
    }
    //无网络连接
    public static final int NO_INTERNET = 1;
    //登录失效
    public static final int LOGIN_FAILURE = 2;
    //服务器无响应
    public static final int SERVER_ERROR = 3;
    //未知错误
    public static final int UNKNOW_ERROR = 4;
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NO_INTERNET, LOGIN_FAILURE, SERVER_ERROR, UNKNOW_ERROR})
    public @interface ResultCode {
    }
}
