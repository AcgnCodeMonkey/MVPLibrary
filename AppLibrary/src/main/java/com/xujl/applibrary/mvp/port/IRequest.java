package com.xujl.applibrary.mvp.port;

public interface IRequest<T> {
    void onNext (T t);
}
