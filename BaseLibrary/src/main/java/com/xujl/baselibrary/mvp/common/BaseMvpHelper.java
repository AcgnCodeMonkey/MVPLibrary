package com.xujl.baselibrary.mvp.common;

import com.xujl.baselibrary.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xujl on 2017/9/6.
 * model,view,presenter，helper基类
 */

class BaseMvpHelper {
    private static final String TAG = "BaseMvpHelper------------->";
    private final Map<Class, BaseHelper> mHelpers = new HashMap<>();

    /**
     * 添加一个helper
     *
     * @param helper
     */
    public <T extends BaseHelper> void addHelper (T helper) {
        mHelpers.put(helper.getClass(), helper);
        log("add", helper.getClass().getSimpleName(), helper);
    }

    /**
     * 获取指定类型的Helper
     *
     * @param cls helper类型值必须传入指定类型
     * @param <T>
     * @return
     */
    public <T extends BaseHelper> T getHelper (Class cls) {
        final BaseHelper helper = mHelpers.get(cls);
        log("get", helper.getClass().getSimpleName(), helper);
        return (T) helper;
    }

    /**
     * @param cls helper类型值
     */
    public void removeHelper (Class cls) {
        log("remove", cls.getSimpleName(), mHelpers.get(cls));
        mHelpers.remove(cls);
    }

    /**
     * 清空Helper集合
     */
    public void clearHelpers () {
        mHelpers.clear();
    }

    protected <T extends BaseHelper> void log (String action, String type, T helper) {
        Logger.e(TAG, "action：" + action + "type：" + type + "| helper：" + helper.getClass().getSimpleName());
    }
}
