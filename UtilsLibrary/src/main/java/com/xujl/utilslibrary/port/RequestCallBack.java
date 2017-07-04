package com.xujl.utilslibrary.port;


import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 网络请求，数据回调接口
 */
public interface RequestCallBack {
    //无网络连接
    int NO_INTERNET = 0;
    //登录失效
    int LOGIN_FAILURE = 1;
    //服务器无响应
    int SERVER_ERROR = 2;
    //未知错误
    int UNKNOW_ERROR = 3;

    /**
     * 请求成功回调
     * @param json 请求成功获取的json串
     */
    void notice (String json);

    /**
     * 请求错误回调
     * @param error 错误码
     * @param json  错误json或错误信息
     */
    void error (@JsonICode int error, @Nullable String json);

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NO_INTERNET, LOGIN_FAILURE, SERVER_ERROR, UNKNOW_ERROR})
    public @interface JsonICode {
    }
}
