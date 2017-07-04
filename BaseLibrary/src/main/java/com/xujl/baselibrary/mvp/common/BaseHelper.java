package com.xujl.baselibrary.mvp.common;

import android.util.Log;
import android.util.SparseArray;

import com.xujl.baselibrary.mvp.port.HelperType;

/**
 * Created by xujl on 2017/6/7.
 * 所有mvp helper类的基类，内涵添加其他helper进入自己的方法，除了get方法可以获取当前helper对象外，其他
 * 所有操作对本身helper对象无效，也就是说无法对自己进行增删改查
 */

public class BaseHelper {
    private final SparseArray<Object> mViewHelpers = new SparseArray<>();

    /**
     * 添加一个helper
     *
     * @param type       helper类型值必须传入指定类型的枚举值
     * @param helper
     */
    public <T> void addHelper (@HelperType.HelperTypeEnum int type, T helper) {
        if (helper == this) {
            Log.e("BaseHelper-->", "不要添加自己到自己内部");
            return;
        }
        mViewHelpers.put(type, helper);
    }

    /**
     * 获取指定类型的viewHelper
     *
     * @param type helper类型值必须传入指定类型的枚举值
     * @param <T>
     * @return
     */
    public <T> T getHelper (@HelperType.HelperTypeEnum int type) {
        if (type == HelperType.TYPE_DEFAULT) {
            return (T) this;
        }
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
