package com.xujl.baselibrary.mvp.port;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Created by xujl on 2017/5/26.
 * 生命周期回调接口
 */

public interface LifeCycleCallback {
    /**
     * onStart
     */
    void onStartLife ();

    /**
     * onResume
     */
    void onResumeLife ();

    /**
     * onCreate
     */
    void onCreateLife (@Nullable Bundle savedInstanceState);

    /**
     * onPause
     */
    void onPauseLife ();

    /**
     * onStop
     */
    void onStopLife ();

    /**
     * onDestroy
     */
    void onDestroyLife ();
}
