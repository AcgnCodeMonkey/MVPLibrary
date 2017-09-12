package com.xujl.rxlibrary;

import io.reactivex.ObservableEmitter;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;

/**
 * Created by xujl on 2017/9/4.
 */

public  class BaseObservableEmitter<T> implements ObservableEmitter<T> {
    private ObservableEmitter<T> mEmitter;

    public BaseObservableEmitter (ObservableEmitter<T> emitter) {
        mEmitter = emitter;
    }

    @Override
    public void setDisposable (@Nullable Disposable d) {
        mEmitter.setDisposable(d);
    }

    @Override
    public void setCancellable (@Nullable Cancellable c) {
        mEmitter.setCancellable(c);
    }

    @Override
    public boolean isDisposed () {
        return mEmitter.isDisposed();
    }

    @Override
    public ObservableEmitter<T> serialize () {
        return mEmitter.serialize();
    }

    @Override
    public boolean tryOnError (@NonNull Throwable t) {
        return mEmitter.tryOnError(t);
    }

    @Override
    public void onNext (@NonNull T value) {
        mEmitter.onNext(value);
    }

    @Override
    public void onError (@NonNull Throwable error) {
        mEmitter.onError(error);
    }

    @Override
    public void onComplete () {
        mEmitter.onComplete();
    }
}
