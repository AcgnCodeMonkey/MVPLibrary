package com.xujl.rxlibrary;

import android.util.Log;

import io.reactivex.functions.Consumer;

/**
 * Created by xujl on 2017/9/4.
 * 简单接收器，用于处理单次接收事件
 */

public abstract class BaseConsumer<T> implements Consumer<T>{
    @Override
    public void accept (T t) throws Exception {
        Log.e("BaseConsumer-->",t.toString());
    }
}
