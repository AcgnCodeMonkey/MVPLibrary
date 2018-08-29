package com.xujl.baselibrary;

import com.xujl.task.RxExecutor;

/**
 * 核心库初始化
 */
public class MvpConfig {
    public static void init () {
        //多线程线程池初始化
        RxExecutor.getInstance().init();
    }
}
