package com.xujl.baselibrary.mvp.port;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by xujl on 2017/6/7.
 * 用于helper基类的枚举
 */

public class HelperType {
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    public static final int TYPE_FOUR = 4;
    public static final int TYPE_FIVE = 5;

    @IntDef({TYPE_DEFAULT, TYPE_ONE, TYPE_TWO, TYPE_THREE, TYPE_FOUR, TYPE_FIVE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HelperTypeEnum {

    }
}
