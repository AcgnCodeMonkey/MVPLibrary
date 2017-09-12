package com.xujl.rxlibrary;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xujl on 2017/9/1.
 * rxjava帮助类
 */

public class RxHelper {
    private Observable mObservable;
    private RxLife mRxOptions;


    private RxHelper (Observable observable, RxLife options) {
        mObservable = observable;
        mRxOptions = options;
    }

    private RxHelper (RxLife options) {
        mRxOptions = options;
    }

    private RxHelper () {
    }

    /**
     * 绑定生命周期
     * @param rxOptions
     * @return
     */
    public static RxHelper onCreat (RxLife rxOptions) {
        return new RxHelper(rxOptions);
    }

    /**
     * 不绑定生命周期
     * @return
     */
    public static RxHelper onCreat () {
        return new RxHelper();
    }
    /**
     * 标准发射器
     *
     * @param source
     * @param <T>
     * @return
     */
    public <T> RxHelper creatNormal (BaseObservable<T> source) {
        setObservable(Observable.create(source));
        return this;
    }

    /**
     * 简单发射器
     *
     * @param source
     * @param <T>
     * @return
     */
    public <T> RxHelper creatSimple (T... source) {
        setObservable(Observable.fromArray(source));
        return this;
    }

    /**
     * 创建一个循环的发射器
     *
     * @param delay 第一次触发的延迟时间
     * @param space 循环周期 单位都是毫秒
     * @return
     */
    public RxHelper creatCircle (long delay, long space) {
        setObservable(Observable.interval(delay, space, TimeUnit.MILLISECONDS));
        return this;
    }
    /**
     * 定时发射器
     *
     * @param delay
     * @return
     */
    public  RxHelper createTimer (long delay) {
        setObservable(Observable.timer(delay,TimeUnit.MILLISECONDS));
        return this;
    }
    private void setObservable (Observable observable) {
        mObservable = observable;
    }


    //==============================================================================================================================================

    /**
     * 设置发射器在主线程
     *
     * @return
     */
    public RxHelper senderMainThread () {
        mObservable = mObservable.subscribeOn(AndroidSchedulers.mainThread());
        return this;
    }

    /**
     * 设置发射器在子线程
     *
     * @return
     */
    public RxHelper senderNewThread () {
        mObservable = mObservable.subscribeOn(Schedulers.newThread());
        return this;
    }

    /**
     * 设置接收器在主线程
     *
     * @return
     */
    public RxHelper receiverMainThread () {
        mObservable = mObservable.observeOn(AndroidSchedulers.mainThread());
        return this;
    }

    /**
     * 设置接收器在子线程
     *
     * @return
     */
    public RxHelper receiverNewThread () {
        mObservable = mObservable.observeOn(Schedulers.newThread());
        return this;
    }

    /**
     * 设置事件发生在主线程，接收在子线程
     *
     * @return
     */
    public RxHelper mainThreadToNew () {
        //线程配置后需要重新为observable赋值
        mObservable = mObservable.observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread());
        return this;
    }

    /**
     * 设置事件发生在io线程，接收在主线程
     *
     * @return
     */
    public RxHelper ioThreadToMain () {
        //线程配置后需要重新为observable赋值
        mObservable = mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        return this;
    }

    /**
     * 设置事件发生在子线程，接收在主线程
     *
     * @return
     */
    public RxHelper newThreadToMain () {
        //线程配置后需要重新为observable赋值
        mObservable = mObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        return this;
    }

    /**
     * 事件订阅者执行
     *
     * @param observer
     * @param <T>
     * @return
     */
    public <T> RxHelper run (BaseObserver<T> observer) {
        if(mRxOptions!=null){
            mRxOptions.register(observer);
        }
        mObservable.subscribe(observer);
        return this;
    }

    /**
     * 事件订阅者执行
     *
     * @param consumer
     * @param <T>
     * @return
     */
    public <T> RxHelper run (BaseConsumer<T> consumer) {
        mObservable.subscribe(consumer);
        return this;
    }

    /**
     * 开启下一个事件流
     *
     * @return
     */
    public RxHelper next () {
        return new RxHelper(mRxOptions);
    }
}
