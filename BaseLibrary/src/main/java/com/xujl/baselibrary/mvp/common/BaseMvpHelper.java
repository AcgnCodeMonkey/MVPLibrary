package com.xujl.baselibrary.mvp.common;

import android.util.SparseArray;

import com.xujl.baselibrary.mvp.port.HelperType;

/**
 * Created by xujl on 2017/9/6.
 * model,view,presenter，helper基类
 */

 class BaseMvpHelper {
    private final SparseArray<BaseHelper> mViewHelpers = new SparseArray<>();

    /**
     * 添加一个helper
     *
     * @param type       helper类型值必须传入指定类型的枚举值
     * @param helper
     */
    public <T extends BaseHelper> void addHelper (@HelperType.HelperTypeEnum int type, T helper) {
        mViewHelpers.put(type, helper);
    }

    /**
     * 获取指定类型的viewHelper
     *
     * @param type helper类型值必须传入指定类型的枚举值
     * @param <T>
     * @return
     */
    public <T extends BaseHelper> T getHelper (@HelperType.HelperTypeEnum int type) {
        return (T) mViewHelpers.get(type);
    }

    /**
     * @param type helper类型值必须传入指定类型的枚举值
     */
    public void removeHelper (@HelperType.HelperTypeEnum int type) {
        mViewHelpers.remove(type);
    }

    /**
     * 清空viewHelper集合
     */
    public void clearHelpers () {
        mViewHelpers.clear();
    }
}
