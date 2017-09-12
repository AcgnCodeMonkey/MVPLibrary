package com.xujl.rxlibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujl on 2017/9/4.
 * rxjava生命周期控制类，用于辅助rxHelper类绑定activity和fragment的生命周期
 */

public class RxLife {
    private List<BaseObserver> mObservers;

    public RxLife () {
        mObservers = new ArrayList<>();
    }
    public void register(BaseObserver observer){
        mObservers.add(observer);
    }
    public void destroyAll(){
        for (BaseObserver observer : mObservers) {
            observer.onDestroy();
        }
        mObservers.clear();
    }
}
