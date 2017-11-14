package com.xujl.rxlibrary;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by xujl on 2017/9/4.
 * 标准发射器
 */

public abstract class BaseObservable<T> implements ObservableOnSubscribe<T> {
    private BaseObservableEmitter<T> mEmitter;

    @Override
    public void subscribe (@NonNull ObservableEmitter<T> e) throws Exception {
        if (mEmitter == null) {
            //重新包装emitter
            mEmitter = new BaseObservableEmitter<>(e);
        }
        emitAction(mEmitter);
    }

    /**
     * 事件源
     *
     * @param e
     * @throws Exception
     */
    public abstract void emitAction (BaseObservableEmitter<T> e) throws Exception;
}
