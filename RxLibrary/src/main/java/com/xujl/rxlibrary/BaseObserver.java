package com.xujl.rxlibrary;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xujl on 2017/8/31.
 * 标准接收器，可以接收多次事件
 */

public abstract class BaseObserver<T> implements Observer<T>, BindingCallback {
    private String tag = getClass().getName();
    private Disposable mDisposable;

    @Override
    public void onSubscribe (@NonNull Disposable d) {
        Log.e(tag, "onSubscribe");
        mDisposable = d;
    }

    @Override
    public void onError (@NonNull Throwable e) {
        Log.e(tag, e.getMessage());
    }

    @Override
    public void onNext (@NonNull T t) {
        Log.e(tag, "onNext");
    }

    @Override
    public void onComplete () {
        Log.e(tag, "onComplete");
    }

    @Override
    public void onDestroy () {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
