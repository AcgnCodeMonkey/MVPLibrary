package com.xujl.baselibrary.mvp.port;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by xujl on 2017/5/26.
 * 生命周期回调接口
 */

public interface LifeCycleCallback {
    void onStartLife();
    void onResumeLife ();
    void onCreateLife (@Nullable Bundle savedInstanceState);
    void onPauseLife ();
    void onStopLife ();
    void onDestroyLife ();
}
